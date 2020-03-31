package com.design.platform.resourceplatform.transfer;

import com.design.platform.resourceplatform.entities.Resource;
import com.design.platform.resourceplatform.services.CategoryService;
import com.design.platform.resourceplatform.services.FileService;
import com.design.platform.resourceplatform.services.UserService;

import java.util.List;
import java.util.stream.Collectors;

public class ResourceDefiner {

    public String title;
    public int category;

    public String summary = null;

    public List<Integer> files = List.of();
}
