package com.design.platform.resourceplatform.services;

import com.design.platform.resourceplatform.entities.User;
import com.design.platform.resourceplatform.repositories.UserRepository;
import com.design.platform.resourceplatform.transfer.*;
import com.design.platform.resourceplatform.transfer.patch.UserFollow;
import com.design.platform.resourceplatform.transfer.results.PageHolder;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface UserService {

    UserRepository repository();

    // Methods Get List
    // ===============================================

    PageHolder<UserBooth> GetUserBoothList(PageRequest request);

    PageHolder<UserBooth> GetUserFollowersBoothList(int id, PageRequest request);
    PageHolder<UserBooth> GetUserFollowedBoothList(int id, PageRequest request);
    PageHolder<ResourceBooth> GetUserPublishedBoothList(int id, PageRequest request);
    PageHolder<ResourceBooth> GetUserFavoritesBoothList(int id, PageRequest request);
    PageHolder<FileBooth> GetUserFilesBoothList(int id, PageRequest request);

    // Methods Get Item
    // ===============================================

    UserBooth GetUserBooth(int id);
    UserBooth GetUserBooth(User user);
    User GetUser(int id);
    User GetUser(UserDefiner definer);
    User GetUser(UserRecorder recorder);

    // Methods Modify
    // ===============================================

    User UpdateUser(UserRecorder recorder);

    User CreateUser(UserDefiner definer);

    void DestroyUser(int id);

    // Methods Patch
    // ===============================================

    void FollowUser(UserFollow follow);
}
