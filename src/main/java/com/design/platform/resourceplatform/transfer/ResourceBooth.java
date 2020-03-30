package com.design.platform.resourceplatform.transfer;

import com.design.platform.resourceplatform.entities.Resource;

import java.sql.Timestamp;

public class ResourceBooth {
    public int id;
    public String title;
    public String summary;
    public Timestamp created;
    public Timestamp updated;

    public int category;
    public int author;

    public static ResourceBooth FromResource(Resource resource){
        ResourceBooth booth = new ResourceBooth();
        booth.id = resource.id;
        booth.author = resource.author.id;
        booth.category = resource.category.id;
        booth.title = resource.title;
        booth.summary = resource.summary;
        booth.created = resource.created;
        booth.updated = resource.updated;
        return booth;
    }
}
