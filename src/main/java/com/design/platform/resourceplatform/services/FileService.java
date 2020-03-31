package com.design.platform.resourceplatform.services;

import com.design.platform.resourceplatform.entities.File;
import com.design.platform.resourceplatform.entities.Resource;
import com.design.platform.resourceplatform.repositories.FileRepository;
import com.design.platform.resourceplatform.transfer.*;
import com.design.platform.resourceplatform.utils.PageHolder;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    FileRepository repository();

    // Methods Get List
    // ===============================================

    PageHolder<FileBooth> GetFileBoothList(PageRequest request);

    PageHolder<ResourceBooth> GetFileContainedByBoothList(int id, PageRequest request);

    // Methods Get Item
    // ===============================================

    FileBooth GetFileBooth(int id);
    File GetFile(int id);

    UserBooth GetFileAuthorBooth(int id);

    // Methods Modify
    // ===============================================

    File UpdateFile(FileRecorder recorder, MultipartFile file);

    File UploadFile(FileDefiner definer, MultipartFile file);

    void DestroyFile(int id);
}
