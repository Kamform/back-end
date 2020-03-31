package com.design.platform.resourceplatform.transfer

import java.sql.Timestamp

data class UserBooth(
    var id: Int,
    var username: String,
    var isAdmin: Boolean,
    var isEnable: Boolean,
    var isLock: Boolean,
    var created: Timestamp,
    var updated: Timestamp,
    var email: String,
    var phone: String
)
