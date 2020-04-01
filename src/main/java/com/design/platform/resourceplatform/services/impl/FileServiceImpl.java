package com.design.platform.resourceplatform.services.impl;

import com.design.platform.resourceplatform.entities.File;
import com.design.platform.resourceplatform.entities.Resource;
import com.design.platform.resourceplatform.mappers.ResourceMapperKt;
import com.design.platform.resourceplatform.repositories.FileRepository;
import com.design.platform.resourceplatform.repositories.ResourceRepository;
import com.design.platform.resourceplatform.services.FileRawService;
import com.design.platform.resourceplatform.services.FileService;
import com.design.platform.resourceplatform.services.UserService;
import com.design.platform.resourceplatform.transfer.*;
import com.design.platform.resourceplatform.utils.PageHolder;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

@Service
public class FileServiceImpl implements FileService {

    // Autowired
    // ===============================================

    public final FileRepository repository;

    private final ResourceRepository resourceRepository;
    private final UserService userService;
    private final FileRawService fileRawService;

    public FileServiceImpl(
            FileRepository repository,
            ResourceRepository resourceRepository, UserService userService,
            FileRawService fileRawService) {
        this.repository = repository;
        this.resourceRepository = resourceRepository;
        this.userService = userService;
        this.fileRawService = fileRawService;
    }

    @Override
    public FileRepository repository() {
        return repository;
    }

    // Methods Get List
    // ===============================================

    @Override
    public PageHolder<FileBooth> GetFileBoothList(PageRequest request) {
        return new PageHolder<>(
                repository.findAll(request)
                          .map(FileBooth::FromFile));
    }

    @Override
    public PageHolder<ResourceBooth> GetFileContainedByBoothList(int id, PageRequest request) {
        return new PageHolder<>(
                resourceRepository.findAllByFiles(GetFile(id), request)
                                  .map(ResourceMapperKt::auto));
    }

    @Override
    public FileBooth GetFileBooth(int id) {
        return FileBooth.FromFile(GetFile(id));
    }

    @Override
    public File GetFile(int id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public UserBooth GetFileAuthorBooth(int id) {
        return null;
    }

    @Override
    public File UpdateFile(FileRecorder recorder, MultipartFile fileRaw) {
        File file = recorder.ToFile(this);
        fileRawService.RemoveFile(file.path);
        fileRawService.ReplaceFile(file.path, fileRaw);
        return repository.save(file);
    }

    @Override
    public File UploadFile(FileDefiner definer, MultipartFile fileRaw) {
//        File file = definer.ToFile(userService);
//        file.path = fileRawService.AddFile(fileRaw);
        try {
            SaveFile(definer.author, definer.name, fileRaw);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        return repository.save(file);
        return null;
    }

    @Override
    public void DestroyFile(int id) {
        File file = GetFile(id);
        fileRawService.RemoveFile(file.path);
        repository.delete(file);
    }

    private Resource GetFile(String path) {
        return null;
    }

    private String SaveFile(int author, String name, MultipartFile file) throws IOException {
        if (file.isEmpty()) return null;
        if (file.getOriginalFilename() == null) return null;

        // choose user upload folder
        Path folder = Paths.get(
                "uploads",
                String.valueOf(author),
                String.valueOf(LocalDateTime.now().getYear()),
                String.valueOf(LocalDateTime.now().getMonth()));
        if (!Files.exists(folder)) Files.createDirectories(folder);

        // (optional) rename file
        String dataName = file.getOriginalFilename();
        if (name != null) {
            int index = dataName.lastIndexOf(".");
            String extension = dataName.substring(index);
            dataName = name + extension;
        }

        // save file in folder
        Path data = Paths.get(folder.toString(), dataName);
        file.transferTo(data);

        // return file tag
        return data.toString();
    }

    private String RemoveFile(String path) {
        return null;
    }
}
