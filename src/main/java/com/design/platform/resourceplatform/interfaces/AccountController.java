package com.design.platform.resourceplatform.interfaces;

import com.design.platform.resourceplatform.services.AccountService;
import com.design.platform.resourceplatform.transfer.AccountBooth;
import com.design.platform.resourceplatform.transfer.AccountDefiner;
import com.design.platform.resourceplatform.transfer.AccountRecorder;
import com.design.platform.resourceplatform.transfer.params.PageQuery;
import com.design.platform.resourceplatform.transfer.results.PageHolder;
import org.springframework.web.bind.annotation.*;

// account/** 基础账号管理
//      GET /     账号列表     用户权限
//      GET /{id} 单个账号信息 用户权限
//      POST /    更新账号     主人权限
//      PUT /     新建账号     无权限
//      DELETE /  注销账号     主人权限

@RestController
@RequestMapping("/api/account")
public class AccountController {

    // Autowired
    // ===============================================

    private final AccountService service;

    public AccountController(AccountService service) {
        this.service = service;
    }

    // Methods
    // ===============================================

    @GetMapping
    public PageHolder<AccountBooth> GetAccountList(PageQuery query) {
        return service.GetAccountBoothList(query.toRequest());
    }

    @GetMapping("/{id}")
    public AccountBooth GetAccount(@PathVariable int id) {
        return service.GetAccountBooth(id);
    }

    @PostMapping
    public void UpdateAccount(@RequestBody AccountRecorder recorder) {
        service.UpdateAccount(recorder);
    }

    @PutMapping
    public void CreateAccount(@RequestBody AccountDefiner definer) {
        service.CreateAccount(definer);
    }

    @DeleteMapping
    public void DestroyAccount(@RequestBody int id) {
        service.DestroyAccount(id);
    }
}
