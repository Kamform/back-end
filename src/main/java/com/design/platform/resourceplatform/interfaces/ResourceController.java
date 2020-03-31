package com.design.platform.resourceplatform.interfaces;


import com.design.platform.resourceplatform.entities.Resource;
import com.design.platform.resourceplatform.services.ResourceService;
import com.design.platform.resourceplatform.transfer.*;
import com.design.platform.resourceplatform.utils.PageParam;
import com.design.platform.resourceplatform.transfer.patch.ResourceFavorite;
import com.design.platform.resourceplatform.utils.PageHolder;
import com.design.platform.resourceplatform.utils.PageUtilsKt;
import org.springframework.web.bind.annotation.*;

//      GET /                 资源列表       无权限
//      GET /{id}             单个资源信息   开放权限
//      GET /{id}/favorite-by 资源被谁关注   开放权限
//      GET /{id}/files       资源引用的文件 开放权限
//      POST /                更新资源       主人权限
//      PUT /                 创建资源       用户权限
//      PATCH /favorite       收藏资源       用户权限
//      DELETE /              删除资源       主人权限

@RestController
@RequestMapping("/api/resource")
public class ResourceController {

    // Autowired
    // ===============================================

    private final ResourceService service;

    public ResourceController(ResourceService service) {
        this.service = service;
    }

    // Methods Get
    // ===============================================

    @GetMapping
    public PageHolder<ResourceBooth> GetResourceList(PageParam param) {
        return service.GetResourceBoothList(PageUtilsKt.auto(param));
    }

    @GetMapping("/{id}/favorite-by")
    public PageHolder<UserBooth> GetResourceFavoriteByList(@PathVariable int id, PageParam param) {
        return service.GetResourceFavoriteByBoothList(id, PageUtilsKt.auto(param));
    }

    @GetMapping("/{id}/files")
    public PageHolder<FileBooth> GetResourceFileList(@PathVariable int id, PageParam param) {
        return service.GetResourceFilesBoothList(id, PageUtilsKt.auto(param));
    }

    @GetMapping("/{id}")
    public ResourceBooth GetResource(@PathVariable int id) {
        return service.GetResourceBooth(id);
    }

    // Methods POST
    // ===============================================

    @PostMapping
    public void UpdateResource(@RequestBody ResourceRecorder recorder) {
        service.UpdateResource(recorder);
    }

    @PutMapping
    public void CreateResource(@RequestBody ResourceDefiner definer) {
        Resource resource = service.CreateResource(definer);
        System.out.println(resource);
    }

    @PatchMapping("/favorite")
    public void FavoriteResource(@RequestBody ResourceFavorite favorite) {
        service.FavoriteResource(favorite);
    }

    @DeleteMapping
    public void DeleteResource(@RequestBody int id) {
        service.DestroyResource(id);
    }
}
