package com.design.platform.resourceplatform.transfer

import javax.validation.constraints.NotBlank

data class UserDefiner(
    @NotBlank val username: String,
    @NotBlank val password: String,
    @NotBlank val nickname: String = "new user",
    @NotBlank val email: String = "",
    @NotBlank val phone: String = "",
    val isEnable: Boolean = true,
    val isLock: Boolean = false
)
