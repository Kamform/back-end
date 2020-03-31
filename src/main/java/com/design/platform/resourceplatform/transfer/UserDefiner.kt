package com.design.platform.resourceplatform.transfer

data class UserDefiner(
    val username: String,
    val password: String,
    val nickname: String = "new user",
    val email: String = "",
    val phone: String = "",
    val isEnable: Boolean = true,
    val isLock: Boolean = false
)
