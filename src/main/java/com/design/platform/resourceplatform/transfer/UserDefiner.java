package com.design.platform.resourceplatform.transfer;

public class UserDefiner {

    public String username;
    public String password;
    public boolean isAdmin = false;
    public boolean isEnable = true;
    public boolean isLock = false;
    public String email;
    public String phone;

    public AccountDefiner account() {
        AccountDefiner definer = new AccountDefiner();
        definer.username = username;
        definer.password = password;
        definer.isAdmin = isAdmin;
        definer.isEnable = isEnable;
        definer.isLock = isLock;
        return definer;
    }
}
