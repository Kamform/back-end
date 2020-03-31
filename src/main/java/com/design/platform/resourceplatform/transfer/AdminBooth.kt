package com.design.platform.resourceplatform.transfer

import java.sql.Timestamp

data class AdminBooth(
    val id: Int,
    val nickname: String,
    val username: String,
    val isEnable: Boolean,
    val isLock: Boolean,
    val created: Timestamp,
    val updated: Timestamp
)
