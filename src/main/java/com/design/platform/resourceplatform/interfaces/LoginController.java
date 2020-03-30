package com.design.platform.resourceplatform.interfaces;


import com.design.platform.resourceplatform.entities.Account;
import com.design.platform.resourceplatform.services.AccountService;
import com.design.platform.resourceplatform.transfer.AccountBooth;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    private final AccountService service;

    public LoginController(AccountService service) {
        this.service = service;
    }

    @GetMapping
    public AccountBooth MockLogin(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Account account = (Account) authentication.getPrincipal();
        return service.GetAccountBooth(account);
    }
}
