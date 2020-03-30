package com.design.platform.resourceplatform.services;

import com.design.platform.resourceplatform.entities.Account;
import com.design.platform.resourceplatform.repositories.AccountRepository;
import com.design.platform.resourceplatform.transfer.AccountBooth;
import com.design.platform.resourceplatform.transfer.AccountDefiner;
import com.design.platform.resourceplatform.transfer.AccountRecorder;
import com.design.platform.resourceplatform.transfer.results.PageHolder;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface AccountService extends UserDetailsService {

    AccountRepository repository();

    // Methods Get List
    // ===============================================

    PageHolder<AccountBooth> GetAccountBoothList(PageRequest request);

    // Methods Get Item
    // ===============================================

    AccountBooth GetAccountBooth(int id);
    AccountBooth GetAccountBooth(Account account);
    Account GetAccount(int id);
    Account GetAccount(AccountDefiner definer);
    Account GetAccount(AccountRecorder recorder);

    // Methods Modify
    // ===============================================

    Account UpdateAccount(AccountRecorder recorder);
    Account CreateAccount(AccountDefiner definer);
    void DestroyAccount(int id);
}
