package com.design.platform.resourceplatform.transfer

import javax.validation.constraints.Positive

data class AdminRecorder(
    val id: Int,
    val nickname: String = "",
    val password: String = "",
    val isEnable: Boolean?,
    val isLock: Boolean?
)
