package com.design.platform.resourceplatform.interfaces;



import com.design.platform.resourceplatform.entities.File;
import com.design.platform.resourceplatform.services.FileRawService;
import com.design.platform.resourceplatform.services.FileService;
import com.design.platform.resourceplatform.transfer.FileBooth;
import com.design.platform.resourceplatform.transfer.FileDefiner;
import com.design.platform.resourceplatform.transfer.FileRecorder;
import com.design.platform.resourceplatform.transfer.ResourceBooth;
import com.design.platform.resourceplatform.transfer.params.PageQuery;
import com.design.platform.resourceplatform.transfer.results.PageHolder;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.net.http.HttpResponse;

//      GET /                   文件列表     无权限
//      GET /{id}               单个文件信息 开放权限
//      GET /{id}/contained-by 文件包含于   开放权限
//      GET /{id}/download      下载文件     开放权限
//      POST /                  更新文件     主人权限
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
    public PageHolder<FileBooth> GetFileList(PageQuery query) {
        return service.GetFileBoothList(query.toRequest());
    }

    @GetMapping("/{id}")
    public FileBooth GetFile(@PathVariable int id) {
        return service.GetFileBooth(id);
    }

    @GetMapping("/{id}/contained-by")
    public PageHolder<ResourceBooth> GetFileContainedList(@PathVariable int id, PageQuery query) {
        return service.GetFileContainedByBoothList(id, query.toRequest());
    }

    @GetMapping(value = "/{id}/download", consumes = "*/*")
    public Resource DownloadFile(@PathVariable int id, HttpServletResponse response) {
        HttpHeaders headers = new HttpHeaders();
        File file = service.GetFile(id);
        Resource resource = rawService.GetFile(file.path);
        response.setHeader("Content-Disposition", "attachment;filename=" + resource.getFilename());
        return resource;
    }

    @PostMapping
    public void UpdateFile(@RequestPart FileRecorder recorder, @RequestPart MultipartFile file) {
        service.UpdateFile(recorder, file);
    }

    @PutMapping
    public void CreateFile(@RequestPart FileDefiner definer, @RequestPart MultipartFile file) {
        service.AddNewFile(definer, file);
    }

    @DeleteMapping
    public void DeleteFile(@RequestBody int id) {
        service.DestroyFile(id);
    }
}
