package com.design.platform.resourceplatform.services.impl;

import com.design.platform.resourceplatform.entities.File;
import com.design.platform.resourceplatform.repositories.FileRepository;
import com.design.platform.resourceplatform.repositories.ResourceRepository;
import com.design.platform.resourceplatform.services.FileRawService;
import com.design.platform.resourceplatform.services.FileService;
import com.design.platform.resourceplatform.services.UserService;
import com.design.platform.resourceplatform.transfer.*;
import com.design.platform.resourceplatform.transfer.results.PageHolder;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
        return new PageHolder<ResourceBooth>(
                resourceRepository.findAllByFiles(GetFile(id), request)
                               .map(ResourceBooth::FromResource));
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
    public File AddNewFile(FileDefiner definer, MultipartFile fileRaw) {
        File file = definer.ToFile(userService);
        file.path = fileRawService.AddFile(fileRaw);
        return repository.save(file);
    }

    @Override
    public void DestroyFile(int id) {
        File file = GetFile(id);
        fileRawService.RemoveFile(file.path);
        repository.delete(file);
    }
}
