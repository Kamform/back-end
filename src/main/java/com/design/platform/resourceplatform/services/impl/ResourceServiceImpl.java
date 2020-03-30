package com.design.platform.resourceplatform.services.impl;

import com.design.platform.resourceplatform.entities.Resource;
import com.design.platform.resourceplatform.repositories.ResourceRepository;
import com.design.platform.resourceplatform.services.CategoryService;
import com.design.platform.resourceplatform.services.FileService;
import com.design.platform.resourceplatform.services.ResourceService;
import com.design.platform.resourceplatform.services.UserService;
import com.design.platform.resourceplatform.transfer.*;
import com.design.platform.resourceplatform.transfer.patch.ResourceFavorite;
import com.design.platform.resourceplatform.transfer.results.PageHolder;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ResourceServiceImpl implements ResourceService {

    // Autowired
    // ===============================================

    public final ResourceRepository repository;

    private final CategoryService categoryService;
    private final UserService userService;
    private final FileService fileService;

    public ResourceServiceImpl(
            ResourceRepository repository,
            CategoryService categoryService,
            UserService userService,
            FileService fileService) {
        this.repository = repository;
        this.categoryService = categoryService;
        this.userService = userService;
        this.fileService = fileService;
    }


    @Override
    public ResourceRepository repository() {
        return repository;
    }

    // Methods Get List
    // ===============================================


    @Override
    public PageHolder<ResourceBooth> GetResourceBoothList(PageRequest request) {
        return new PageHolder<>(
                repository.findAll(request)
                          .map(ResourceBooth::FromResource));
    }

    @Override
    public PageHolder<UserBooth> GetResourceFavoriteByBoothList(int id, PageRequest request) {
        return new PageHolder<>(
                userService.repository().findAllByFavorites(GetResource(id), request)
                           .map(userService::GetUserBooth));
    }

    @Override
    public PageHolder<FileBooth> GetResourceFilesBoothList(int id, PageRequest request) {
        return new PageHolder<>(
                fileService.repository().findAllByContainedBy(GetResource(id), request)
                           .map(FileBooth::FromFile)
        );
    }

    // Methods Get Item
    // ===============================================

    @Override
    public ResourceBooth GetResourceBooth(int id) {
        return ResourceBooth.FromResource(GetResource(id));
    }

    @Override
    public Resource GetResource(int id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public CategoryBooth GetResourceCategory(int id) {
        return null;
    }

    @Override
    public UserBooth GetResourceAuthor(int id) {
        return null;
    }

    // Methods Modify
    // ===============================================

    @Override
    public Resource UpdateResource(ResourceRecorder recorder) {
        return repository.save(recorder.ToResource(
                this, categoryService, fileService));
    }

    @Override
    public Resource CreateResource(ResourceDefiner definer) {
        return repository.save(definer.ToResource(
                userService, categoryService, fileService));
    }

    @Override
    public void DestroyResource(int id) {
        repository.deleteById(id);
    }

    // Methods Patch
    // ===============================================

    @Override
    public void FavoriteResource(ResourceFavorite favorite) {
        userService.GetUser(favorite.user).favorites.add(GetResource(favorite.resource));
    }
}
