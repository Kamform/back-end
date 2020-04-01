package com.design.platform.resourceplatform.transfer

import javax.validation.constraints.NotBlank

data class AdminDefiner(
    @NotBlank val username: String,
    @NotBlank val password: String,
    @NotBlank val nickname: String = "new admin",
    val isEnable: Boolean = true,
    val isLock: Boolean = false
)
