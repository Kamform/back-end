package com.design.platform.resourceplatform.transfer

data class UserRecorder(
    val id: Int,
    val password: String = "",
    val nickname: String = "",
    val email: String = "",
    val phone: String = "",
    val isEnable: Boolean = true,
    val isLock: Boolean = false
)
