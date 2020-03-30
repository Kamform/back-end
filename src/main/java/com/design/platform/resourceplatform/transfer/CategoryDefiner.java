package com.design.platform.resourceplatform.transfer;

import com.design.platform.resourceplatform.entities.Category;

public class CategoryDefiner {

    public String name;
    public int sort = 0;

    public Category ToCategory(){
        Category category = new Category();
        category.name = name;
        category.sort = sort;
        return category;
    }
}
