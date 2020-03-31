package com.design.platform.resourceplatform.services.impl;

import com.design.platform.resourceplatform.entities.Resource;
import com.design.platform.resourceplatform.entities.User;
import com.design.platform.resourceplatform.mappers.CategoryMapperKt;
import com.design.platform.resourceplatform.mappers.ResourceMapperKt;
import com.design.platform.resourceplatform.mappers.UserMapperKt;
import com.design.platform.resourceplatform.repositories.FileRepository;
import com.design.platform.resourceplatform.repositories.ResourceRepository;
import com.design.platform.resourceplatform.repositories.UserRepository;
import com.design.platform.resourceplatform.services.FileService;
import com.design.platform.resourceplatform.services.ResourceService;
import com.design.platform.resourceplatform.services.UserService;
import com.design.platform.resourceplatform.transfer.*;
import com.design.platform.resourceplatform.transfer.patch.ResourceFavorite;
import com.design.platform.resourceplatform.utils.PageHolder;
import com.design.platform.resourceplatform.utils.PageHolderKt;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ResourceServiceImpl implements ResourceService {

    // Autowired
    // ===============================================

    private final ResourceRepository resources;
    private final UserRepository users;
    private final FileRepository files;

    public ResourceServiceImpl(
            ResourceRepository resources,
            UserRepository users,
            FileRepository files) {
        this.resources = resources;
        this.users = users;
        this.files = files;
    }

    // Methods Get List
    // ===============================================


    @Override
    public PageHolder<ResourceBooth> GetResourceBoothList(PageRequest request) {
        return new PageHolder<>(
                resources.findAll(request)
                         .map(ResourceMapperKt::auto));
    }

    @Override
    public PageHolder<UserBooth> GetResourceFavoriteByBoothList(int id, PageRequest request) {
        return PageHolderKt.auto(users.findAllByFavoritesId(id, request).map(UserMapperKt::auto));
    }

    @Override
    public PageHolder<FileBooth> GetResourceFilesBoothList(int id, PageRequest request) {
        return PageHolderKt.auto(files.findAllByContainedId(id, request).map(FileBooth::FromFile));
    }

    // Methods Get Item
    // ===============================================

    @Override
    public ResourceBooth GetResourceBooth(int id) {
        return ResourceMapperKt.auto(GetResource(id));
    }

    @Override
    public Resource GetResource(int id) {
        return resources.findById(id).orElseThrow();
    }

    @Override
    public CategoryBooth GetResourceCategory(int id) {
        return CategoryMapperKt.auto(GetResource(id).category);
    }

    @Override
    public UserBooth GetResourceAuthor(int id) {
        return UserMapperKt.auto(GetResource(id).author);
    }

    @Override
    public Resource UpdateResource(ResourceRecorder recorder) {
        return resources.save(ResourceMapperKt.auto(recorder));
    }

    @Override
    public Resource CreateResource(ResourceDefiner definer) {
        return resources.save(ResourceMapperKt.auto(definer));
    }

    // Methods Modify
    // ===============================================

    @Override
    public void DestroyResource(int id) {
        resources.deleteById(id);
    }

    // Methods Patch
    // ===============================================

    @Override
    public void FavoriteResource(ResourceFavorite favorite) {
        Resource resource = resources.findById(favorite.resource).orElseThrow();
        User user = users.findById(favorite.user).orElseThrow();
        if (favorite.cancel) resource.favored.remove(user);
        else resource.favored.add(user);
        resources.save(resource);
    }
}
