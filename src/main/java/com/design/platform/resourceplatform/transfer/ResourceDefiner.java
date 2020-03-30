package com.design.platform.resourceplatform.transfer;

import com.design.platform.resourceplatform.entities.Resource;
import com.design.platform.resourceplatform.services.CategoryService;
import com.design.platform.resourceplatform.services.FileService;
import com.design.platform.resourceplatform.services.UserService;

import java.util.List;
import java.util.stream.Collectors;

public class ResourceDefiner {
    public String title;
    public String summary = null;

    public int author;
    public int category;
    public List<Integer> files = List.of();

    public Resource ToResource(UserService userService, CategoryService categoryService, FileService fileService){
        Resource resource = new Resource();
        resource.author = userService.GetUser(author);
        resource.category = categoryService.GetCategory(category);
        resource.title = title;
        resource.summary = summary;
        resource.files = files.stream().map(fileService::GetFile).collect(Collectors.toList());
        return resource;
    }
}
