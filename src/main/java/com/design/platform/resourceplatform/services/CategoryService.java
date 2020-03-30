package com.design.platform.resourceplatform.services;

import com.design.platform.resourceplatform.entities.Category;
import com.design.platform.resourceplatform.repositories.CategoryRepository;
import com.design.platform.resourceplatform.transfer.CategoryBooth;
import com.design.platform.resourceplatform.transfer.CategoryDefiner;
import com.design.platform.resourceplatform.transfer.CategoryRecorder;
import com.design.platform.resourceplatform.transfer.ResourceBooth;
import com.design.platform.resourceplatform.transfer.results.PageHolder;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface CategoryService {

    CategoryRepository repository();

    // Methods Get List
    // ===============================================

    PageHolder<CategoryBooth> GetCategoryBoothList(PageRequest request);

    PageHolder<ResourceBooth> GetCategoryResourceBoothList(int id, PageRequest request);

    // Methods Get Item
    // ===============================================

    CategoryBooth GetCategoryBooth(int id);
    Category GetCategory(int id);


    // Methods modify
    // ===============================================

    Category UpdateCategory(CategoryRecorder recorder);

    Category CreateCategory(CategoryDefiner definer);

    void DeleteCategory(int id);
}
