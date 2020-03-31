package com.design.platform.resourceplatform.services;

import com.design.platform.resourceplatform.entities.Account;
import com.design.platform.resourceplatform.transfer.*;
import com.design.platform.resourceplatform.utils.PageHolder;
import org.springframework.data.domain.PageRequest;

public interface AdminService {

    // Methods Get List
    // ===============================================

    PageHolder<AdminBooth> GetAdminBoothList(PageRequest request);

    // Methods Get Item
    // ===============================================

    AdminBooth GetAdminBooth(int id);
    AdminBooth GetAdminBooth(Account account);

    // Methods Modify
    // ===============================================

    Account UpdateAdmin(AdminRecorder recorder);
    Account CreateAdmin(AdminDefiner definer);
    void DestroyAdmin(int id);
}
