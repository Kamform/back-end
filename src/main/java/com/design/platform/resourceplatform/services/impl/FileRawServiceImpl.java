package com.design.platform.resourceplatform.services.impl;

import com.design.platform.resourceplatform.services.FileRawService;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Objects;

@Service
public class FileRawServiceImpl implements FileRawService {

    private static final String SAVE_PATH = "./upload/";

    @Override
    public Resource GetFile(String path) {
        return new PathResource(path);
    }

    @Override
    public void ReplaceFile(String path, MultipartFile file) {
        Path filePath = Path.of(path);
        try {
            file.transferTo(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String AddFile(MultipartFile file) {
        Path path = null;
        try {
            String name = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC) + "." + Objects.requireNonNull(file.getOriginalFilename()).split("\\.")[1];
            path = Path.of(SAVE_PATH, name);
            file.transferTo(Files.createFile(path));
        } catch (Exception e) {
            e.printStackTrace();
        }
        assert path != null;
        return path.toString();
    }

    @Override
    public void RemoveFile(String path) {
        try {
            Files.delete(Path.of(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
