package com.design.platform.resourceplatform.transfer;

public class UserRecorder {
    public int id;
    public String username;
    public String password = null;
    public boolean isAdmin;
    public boolean isEnable;
    public boolean isLock;
    public String email;
    public String phone;

    public AccountRecorder account() {
        AccountRecorder recorder = new AccountRecorder();
        recorder.id = id;
        recorder.username = username;
        recorder.password = password;
        recorder.isAdmin = isAdmin;
        recorder.isEnable = isEnable;
        recorder.isLock = isLock;
        return recorder;
    }
}
