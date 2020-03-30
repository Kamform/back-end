package com.design.platform.resourceplatform.services;

import com.design.platform.resourceplatform.entities.Resource;
import com.design.platform.resourceplatform.repositories.ResourceRepository;
import com.design.platform.resourceplatform.transfer.*;
import com.design.platform.resourceplatform.transfer.patch.ResourceFavorite;
import com.design.platform.resourceplatform.transfer.results.PageHolder;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface ResourceService {

    ResourceRepository repository();

    // Methods Get List
    // ===============================================

    PageHolder<ResourceBooth> GetResourceBoothList(PageRequest request);

    PageHolder<UserBooth> GetResourceFavoriteByBoothList(int id, PageRequest request);
    PageHolder<FileBooth> GetResourceFilesBoothList(int id, PageRequest request);

    // Methods Get Item
    // ===============================================

    ResourceBooth GetResourceBooth(int id);
    Resource GetResource(int id);

    CategoryBooth GetResourceCategory(int id);
    UserBooth GetResourceAuthor(int id);

    // Methods Modify
    // ===============================================

    Resource UpdateResource(ResourceRecorder recorder);

    Resource CreateResource(ResourceDefiner definer);

    void DestroyResource(int id);

    // Methods Patch
    // ===============================================

    void FavoriteResource(ResourceFavorite favorite);
}
