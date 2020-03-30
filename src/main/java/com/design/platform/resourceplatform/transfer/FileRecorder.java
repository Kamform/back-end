package com.design.platform.resourceplatform.transfer;

import com.design.platform.resourceplatform.entities.File;
import com.design.platform.resourceplatform.services.FileService;

public class FileRecorder {
    public int id;
    public String name;

    public File ToFile(FileService service) {
        File file = service.GetFile(id);
        file.name = name;
        return file;
    }
}
