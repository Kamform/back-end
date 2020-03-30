package com.design.platform.resourceplatform.transfer;

import com.design.platform.resourceplatform.entities.Category;
import com.design.platform.resourceplatform.services.CategoryService;

public class CategoryRecorder {
    public int id;
    public String name;
    public int sort;

    public Category ToCategory(CategoryService service){
        Category category = service.GetCategory(id);
        category.name = name;
        category.sort = sort;
        return category;
    }
}
