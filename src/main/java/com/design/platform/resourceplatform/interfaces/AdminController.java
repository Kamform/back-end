package com.design.platform.resourceplatform.interfaces;


import com.design.platform.resourceplatform.entities.Account;
import com.design.platform.resourceplatform.services.AdminService;
import com.design.platform.resourceplatform.transfer.AdminBooth;
import com.design.platform.resourceplatform.transfer.AdminRecorder;
import com.design.platform.resourceplatform.utils.PageHolder;
import com.design.platform.resourceplatform.utils.PageParam;
import com.design.platform.resourceplatform.utils.PageUtilsKt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService service;

    public AdminController(AdminService service) {
        this.service = service;
    }

    // Methods GET
    // ===============================================

    @GetMapping
    public PageHolder<AdminBooth> GetList(PageParam param) {
        return service.GetAdminBoothList(PageUtilsKt.getAuto(param));
    }

    @GetMapping("/{id}")
    public AdminBooth GetAdmin(@PathVariable int id) {
        return service.GetAdminBooth(id);
    }

    // Methods POST
    // ===============================================

    @PostMapping
    public void UpdateAdmin(@RequestBody AdminRecorder recorder) {
        System.out.println(recorder);
        Account account =  service.UpdateAdmin(recorder);
        System.out.println(account);
    }

    @PutMapping
    public void CreateAdmin(@RequestBody AdminDefiner definer) {
        System.out.println(definer);
        Account account =  service.CreateAdmin(definer);
        System.out.println(account);
    }

    @DeleteMapping("/{id}")
    public void DeleteAdmin(@PathVariable int id){
        System.out.println(id);
        service.DestroyAdmin(id);
        System.out.println("done");
    }
}
