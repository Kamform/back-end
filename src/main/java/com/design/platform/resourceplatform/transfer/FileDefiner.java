package com.design.platform.resourceplatform.transfer;

import com.design.platform.resourceplatform.entities.File;
import com.design.platform.resourceplatform.services.UserService;

public class FileDefiner {
    public String name = null;
    public int author;

    public File ToFile(UserService userService){
        File file = new File();
        file.name = name;
        file.author = userService.GetUser(author);
        return file;
    }
}
