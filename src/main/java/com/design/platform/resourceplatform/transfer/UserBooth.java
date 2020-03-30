package com.design.platform.resourceplatform.transfer;

import com.design.platform.resourceplatform.entities.Account;
import com.design.platform.resourceplatform.entities.User;

import java.sql.Timestamp;

public class UserBooth {
    public int id;
    public String username;
    public boolean isAdmin;
    public boolean isEnable;
    public boolean isLock;
    public Timestamp created;
    public Timestamp updated;
    public String email;
    public String phone;
}
