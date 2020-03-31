package com.design.platform.resourceplatform.mappers

import com.design.platform.resourceplatform.entities.User
import com.design.platform.resourceplatform.transfer.UserBooth
import com.design.platform.resourceplatform.transfer.UserDefiner
import com.design.platform.resourceplatform.transfer.UserRecorder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

fun User.auto(): UserBooth = UserBooth(
    id,
    account.username,
    account.admin,
    account.enable,
    account.lock,
    account.created,
    account.updated,
    email,
    phone
)
