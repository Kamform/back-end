package com.design.platform.resourceplatform.services.impl;

import com.design.platform.resourceplatform.entities.User;
import com.design.platform.resourceplatform.repositories.FileRepository;
import com.design.platform.resourceplatform.repositories.ResourceRepository;
import com.design.platform.resourceplatform.repositories.UserRepository;
import com.design.platform.resourceplatform.services.AccountService;
import com.design.platform.resourceplatform.services.UserService;
import com.design.platform.resourceplatform.transfer.*;
import com.design.platform.resourceplatform.transfer.patch.UserFollow;
import com.design.platform.resourceplatform.transfer.results.PageHolder;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {


    // Autowired
    // ===============================================

    public final UserRepository repository;

    private final AccountService accountService;
    private final ResourceRepository resourceRepository;
    private final FileRepository fileRepository;

    public UserServiceImpl(
            UserRepository repository,
            AccountService accountService,
            ResourceRepository resourceRepository,
            FileRepository fileRepository) {
        this.repository = repository;
        this.accountService = accountService;
        this.resourceRepository = resourceRepository;
        this.fileRepository = fileRepository;
    }


    @Override
    public UserRepository repository() {
        return repository;
    }

    // Methods
    // ===============================================


    @Override
    public PageHolder<UserBooth> GetUserBoothList(PageRequest request) {
        return new PageHolder<>(repository.findAll(request).map(this::GetUserBooth));
    }

    @Override
    public PageHolder<UserBooth> GetUserFollowersBoothList(int id, PageRequest request) {
        return new PageHolder<>(
                repository.findAllByFollowed(GetUser(id), request)
                          .map(this::GetUserBooth)
        );
    }

    @Override
    public PageHolder<UserBooth> GetUserFollowedBoothList(int id, PageRequest request) {
        return new PageHolder<>(
                repository.findAllByFollowers(GetUser(id), request)
                          .map(this::GetUserBooth)
        );
    }

    @Override
    public PageHolder<ResourceBooth> GetUserPublishedBoothList(int id, PageRequest request) {
        return new PageHolder<>(
                resourceRepository.findAllByAuthor(GetUser(id), request)
                                  .map(ResourceBooth::FromResource)
        );
    }

    @Override
    public PageHolder<ResourceBooth> GetUserFavoritesBoothList(int id, PageRequest request) {
        return new PageHolder<>(
                resourceRepository.findAllByFavoriteBy(GetUser(id), request)
                                  .map(ResourceBooth::FromResource)
        );
    }

    @Override
    public PageHolder<FileBooth> GetUserFilesBoothList(int id, PageRequest request) {
        return new PageHolder<>(
                fileRepository.findAllByAuthor(GetUser(id), request)
                              .map(FileBooth::FromFile)
        );
    }

    @Override
    public UserBooth GetUserBooth(int id) {
        return GetUserBooth(GetUser(id));
    }

    @Override
    public UserBooth GetUserBooth(User user) {
        UserBooth booth = new UserBooth();
        booth.id = user.id;
        booth.username = user.account.username;
        booth.phone = user.phone;
        booth.email = user.email;
        booth.isAdmin = user.account.admin;
        booth.isEnable = user.account.enable;
        booth.isLock = user.account.lock;
        booth.created = user.account.created;
        booth.updated = user.account.updated;
        return booth;
    }

    @Override
    public User GetUser(int id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public User GetUser(UserDefiner definer) {
        User user = new User();
        user.account = accountService.CreateAccount(definer.account());
        user.email = definer.email;
        user.phone = definer.phone;
        return user;
    }

    @Override
    public User GetUser(UserRecorder recorder) {
        User user = GetUser(recorder.id);
        user.account = accountService.UpdateAccount(recorder.account());
        user.email = recorder.email;
        user.phone = recorder.phone;
        return user;
    }

    @Override
    public User UpdateUser(UserRecorder recorder) {
        User user = GetUser(recorder);
        return repository.save(user);
    }

    @Override
    public User CreateUser(UserDefiner definer) {
        return repository.save(GetUser(definer));
    }

    @Override
    public void DestroyUser(int id) {
        repository.deleteById(id);
    }

    // Methods Patch
    // ===============================================

    @Override
    public void FollowUser(UserFollow follow) {
        GetUser(follow.self).followed.add(GetUser(follow.target));
    }
}
