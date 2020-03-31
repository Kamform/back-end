package com.design.platform.resourceplatform.services.impl;

import com.design.platform.resourceplatform.entities.Account;
import com.design.platform.resourceplatform.repositories.AccountRepository;
import com.design.platform.resourceplatform.services.AccountService;
import com.design.platform.resourceplatform.transfer.AccountBooth;
import com.design.platform.resourceplatform.transfer.AccountDefiner;
import com.design.platform.resourceplatform.transfer.AccountRecorder;
import com.design.platform.resourceplatform.utils.PageHolder;
import com.design.platform.resourceplatform.utils.PageHolderKt;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    // Autowired
    // ===============================================

    public final AccountRepository repository;

    private final PasswordEncoder encoder;

    public AccountServiceImpl(
            PasswordEncoder encoder,
            AccountRepository repository) {
        this.encoder = encoder;
        this.repository = repository;
    }

    @Override
    public AccountRepository repository() {
        return repository;
    }

    // Methods Get List
    // ===============================================

    @Override
    public PageHolder<AccountBooth> GetAccountBoothList(PageRequest request) {
        return PageHolderKt.auto(repository.findAll(request).map(this::GetAccountBooth));
    }

    // Methods Get Item
    // ===============================================

    @Override
    public AccountBooth GetAccountBooth(int id) {
        return GetAccountBooth(GetAccount(id));
    }

    @Override
    public AccountBooth GetAccountBooth(Account account) {
        AccountBooth booth = new AccountBooth();
        booth.id = account.id;
        booth.username = account.username;
        booth.isAdmin = account.admin;
        booth.isEnable = account.enable;
        booth.isLock = account.lock;
        booth.created = account.created;
        booth.updated = account.updated;
        return booth;
    }

    @Override
    public Account GetAccount(int id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public Account GetAccount(AccountDefiner definer) {
        Account account = new Account();
        account.username = definer.username;
        account.password = encoder.encode(definer.password);
        account.admin = definer.isAdmin;
        account.enable = definer.isEnable;
        account.lock = definer.isLock;
        return account;
    }

    @Override
    public Account GetAccount(AccountRecorder recorder) {
        Account account = GetAccount(recorder.id);
        account.username = recorder.username;
        if (recorder.password != null) account.password = encoder.encode(recorder.password);
        account.admin = recorder.isAdmin;
        account.enable = recorder.isEnable;
        account.lock = recorder.isLock;
        return account;
    }


    // Methods Modify
    // ===============================================

    @Override
    public Account UpdateAccount(AccountRecorder recorder) {
        return repository.save(GetAccount(recorder));
    }

    @Override
    public Account CreateAccount(AccountDefiner definer) {
        return repository.save(GetAccount(definer));
    }

    @Override
    public void DestroyAccount(int id) {
        repository.deleteById(id);
    }

    // Methods Implement
    // ===============================================

    @Override
    public UserDetails loadUserByUsername(String username) {
        return repository.findByUsername(username).orElseThrow();
    }
}
