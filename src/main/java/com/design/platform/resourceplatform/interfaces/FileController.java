package com.design.platform.resourceplatform.interfaces;

import com.design.platform.resourceplatform.entities.File;
import com.design.platform.resourceplatform.services.FileRawService;
import com.design.platform.resourceplatform.services.FileService;
import com.design.platform.resourceplatform.transfer.FileBooth;
import com.design.platform.resourceplatform.transfer.FileDefiner;
import com.design.platform.resourceplatform.transfer.ResourceBooth;
import com.design.platform.resourceplatform.utils.PageHolder;
import com.design.platform.resourceplatform.utils.PageParam;
import com.design.platform.resourceplatform.utils.PageUtilsKt;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

//      GET /                   文件列表     无权限
//      GET /{id}               单个文件信息 开放权限
//      GET /{id}/contained-by  文件包含于   开放权限
//      GET /{id}/download      下载文件     开放权限
//      PUT /                   上传新文件   用户权限
//      DELETE /                删除文件     主人权限

@RestController
@RequestMapping("/api/file")
public class FileController {

    // Autowired
    // ===============================================

    private final FileService service;
    private final FileRawService rawService;

    public FileController(FileService service, FileRawService rawService) {
        this.service = service;
        this.rawService = rawService;
    }

    // Methods
    // ===============================================

    @GetMapping
    @PreAuthorize("expression.isAdmin(principal)")
    public PageHolder<FileBooth> GetFileList(PageParam param) {
        return service.GetFileBoothList(PageUtilsKt.getAuto(param));
    }

    @GetMapping("/{id}")
    @PreAuthorize("permitAll()")
    public FileBooth GetFile(@PathVariable int id) {
        return service.GetFileBooth(id);
    }

    @GetMapping("/{id}/contained-by")
    @PreAuthorize("permitAll()")
    public PageHolder<ResourceBooth> GetFileContainedList(@PathVariable int id, PageParam param) {
        return service.GetFileContainedByBoothList(id, PageUtilsKt.getAuto(param));
    }

    @GetMapping(value = "/{id}/download", consumes = "*/*")
    @PreAuthorize("permitAll()")
    public Resource DownloadFile(@PathVariable int id, HttpServletResponse response) {
        HttpHeaders headers = new HttpHeaders();
        File file = service.GetFile(id);
        Resource resource = rawService.GetFile(file.path);
        response.setHeader("Content-Disposition", "attachment;filename=" + resource.getFilename());
        return resource;
    }

    @PutMapping
    @PreAuthorize("expression.isUser(principal)")
    public void UploadFile(@RequestPart FileDefiner definer, @RequestPart MultipartFile file) {
        service.UploadFile(definer, file);
    }

    @DeleteMapping
    @PreAuthorize("expression.isUserMasterFile(principal,id)")
    public void DeleteFile(@RequestBody int id) {
        service.DestroyFile(id);
    }
}
