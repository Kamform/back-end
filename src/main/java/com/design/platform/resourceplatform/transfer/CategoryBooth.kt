package com.design.platform.resourceplatform.transfer;


import com.design.platform.resourceplatform.entities.Category;

public class CategoryBooth {
    public int id;
    public String name;
    public int sort;

    public static CategoryBooth FromCategory(Category category){
        CategoryBooth booth = new CategoryBooth();
        booth.id = category.id;
        booth.name = category.name;
        booth.sort = category.sort;
        return booth;
    }
}
