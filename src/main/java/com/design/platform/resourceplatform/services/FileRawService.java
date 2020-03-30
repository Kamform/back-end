package com.design.platform.resourceplatform.services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileRawService {

    Resource GetFile(String path);
    void ReplaceFile(String path, MultipartFile file);
    String AddFile(MultipartFile file);
    void RemoveFile(String path);
}
