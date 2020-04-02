package com.design.platform.resourceplatform.services;

import com.design.platform.resourceplatform.entities.Resource;
import com.design.platform.resourceplatform.entities.User;
import com.design.platform.resourceplatform.transfer.*;
import com.design.platform.resourceplatform.transfer.patch.Patch;
import com.design.platform.resourceplatform.utils.PageHolder;
import org.springframework.data.domain.PageRequest;

public interface ResourceService {

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

    void FavoriteResource(User master, Patch patch);
}
