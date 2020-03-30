package com.design.platform.resourceplatform.transfer;

import com.design.platform.resourceplatform.entities.File;

import java.sql.Timestamp;

public class FileBooth {
    public int id;
    public String name;
    public String type;
    public Timestamp created;
    public Timestamp updated;

    public int author;

    public static FileBooth FromFile(File file){
        FileBooth booth = new FileBooth();
        booth.id = file.id;
        booth.name = file.name;
        booth.type = file.type;
        booth.created = file.created;
        booth.updated = file.updated;
        booth.author = file.author.id;
        return booth;
    }
}
