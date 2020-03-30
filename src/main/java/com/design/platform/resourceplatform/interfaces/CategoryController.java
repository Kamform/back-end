package com.design.platform.resourceplatform.interfaces;


import com.design.platform.resourceplatform.services.CategoryService;
import com.design.platform.resourceplatform.services.ResourceService;
import com.design.platform.resourceplatform.transfer.CategoryBooth;
import com.design.platform.resourceplatform.transfer.CategoryDefiner;
import com.design.platform.resourceplatform.transfer.CategoryRecorder;
import com.design.platform.resourceplatform.transfer.ResourceBooth;
import com.design.platform.resourceplatform.transfer.params.PageQuery;
import com.design.platform.resourceplatform.transfer.results.PageHolder;
import org.springframework.web.bind.annotation.*;

//      GET /                   种类列表       开放权限
//      GET /{id}               种类信息       开放权限
//      GET /{name}/resources   种类下资源列表 开放权限
//      POST /                  更新种类信息   主人权限
//      PUT /                   新建资源种类   用户权限
//      DELETE /                删除资源种类   主人权限

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    // Autowired
    // ===============================================

    private final CategoryService service;

    public CategoryController(CategoryService service, ResourceService resourceService) {
        this.service = service;
    }

    // Methods
    // ===============================================

    @GetMapping
    public PageHolder<CategoryBooth> GetCategoryList(PageQuery query) {
        return service.GetCategoryBoothList(query.toRequest());
    }

    @GetMapping("/{id}/resources")
    public PageHolder<ResourceBooth> GetResourceListByCategory(@PathVariable int id, PageQuery query) {
        return service.GetCategoryResourceBoothList(id, query.toRequest());
    }

    @GetMapping("/{id}")
    public CategoryBooth GetCategory(@PathVariable int id) {
        return service.GetCategoryBooth(id);
    }

    @PostMapping
    public void UpdateCategory(@RequestBody CategoryRecorder recorder) {
        service.UpdateCategory(recorder);
    }

    @PutMapping
    public void CreateCategory(@RequestBody CategoryDefiner definer) {
        service.CreateCategory(definer);
    }

    @DeleteMapping("/{id}")
    public void DeleteCategory(@PathVariable int id) {
        service.DeleteCategory(id);
    }
}
