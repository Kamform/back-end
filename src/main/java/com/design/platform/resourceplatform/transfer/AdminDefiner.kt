package com.design.platform.resourceplatform.transfer

data class AdminDefiner(
    val username: String,
    val password: String,
    val isEnable: Boolean = true,
    val isLock: Boolean = false
)
