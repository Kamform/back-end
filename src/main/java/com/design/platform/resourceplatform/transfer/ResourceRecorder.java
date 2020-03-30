package com.design.platform.resourceplatform.transfer;

import com.design.platform.resourceplatform.entities.Resource;
import com.design.platform.resourceplatform.services.CategoryService;
import com.design.platform.resourceplatform.services.FileService;
import com.design.platform.resourceplatform.services.ResourceService;

import java.util.List;
import java.util.stream.Collectors;

public class ResourceRecorder {
    public int id;
    public String title;
    public String summary;

    public int category;
    public List<Integer> files;

    public Resource ToResource(ResourceService service, CategoryService categoryService, FileService fileService) {
        Resource resource = service.GetResource(id);
        resource.category = categoryService.GetCategory(category);
        resource.title = title;
        resource.summary = summary;
        resource.files = files.stream().map(fileService::GetFile).collect(Collectors.toList());
        return resource;
    }
}
