package com.design.platform.resourceplatform.services.impl;

import com.design.platform.resourceplatform.entities.Account;
import com.design.platform.resourceplatform.repositories.AccountRepository;
import com.design.platform.resourceplatform.services.AccountService;
import com.design.platform.resourceplatform.services.AdminService;
import com.design.platform.resourceplatform.transfer.AdminBooth;
import com.design.platform.resourceplatform.transfer.AdminRecorder;
import com.design.platform.resourceplatform.utils.PageHolder;
import com.design.platform.resourceplatform.utils.PageHolderKt;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    private final PasswordEncoder encoder;
    private final AccountRepository repository;
    private final AccountService service;

    public AdminServiceImpl(PasswordEncoder encoder, AccountRepository repository, AccountService service) {
        this.encoder = encoder;
        this.repository = repository;
        this.service = service;
    }

    @Override
    public PageHolder<AdminBooth> GetAdminBoothList(PageRequest request) {
        return PageHolderKt.getAuto(repository.findAllByAdminIsTrue(request).map(this::GetAdminBooth));
    }

    @Override
    public AdminBooth GetAdminBooth(int id) {
        return GetAdminBooth(service.GetAccount(id));
    }

    @Override
    public AdminBooth GetAdminBooth(Account account) {
        AdminBooth booth = new AdminBooth();
        booth.id = account.id;
        booth.username = account.username;
        booth.isEnable = account.enable;
        booth.isLock = account.lock;
        booth.created = account.created;
        booth.updated = account.updated;
        return booth;
    }

    @Override
    public Account UpdateAdmin(AdminRecorder recorder) {
        Account account = service.GetAccount(recorder.id);
        account.username = recorder.username;
        if (recorder.password != null) account.password = encoder.encode(recorder.password);
        account.admin = true;
        account.enable = recorder.isEnable;
        account.lock = recorder.isLock;
        return repository.save(account);
    }

    @Override
    public Account CreateAdmin(AdminDefiner definer) {
        Account account = new Account();
        account.username = definer.username;
        account.password = encoder.encode(definer.password);
        account.admin = true;
        account.enable = definer.isEnable;
        account.lock = definer.isLock;
        return repository.save(account);
    }

    @Override
    public void DestroyAdmin(int id) {
        service.DestroyAccount(id);
    }
}
