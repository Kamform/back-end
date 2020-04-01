package com.design.platform.resourceplatform.transfer

import javax.validation.constraints.Positive

data class UserRecorder(
    @Positive val id: Int,
    val password: String = "",
    val nickname: String = "",
    val email: String = "",
    val phone: String = "",
    val isEnable: Boolean?,
    val isLock: Boolean?
)
