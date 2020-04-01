package com.design.platform.resourceplatform.transfer.params

import javax.validation.constraints.NotBlank

data class Authenticator(
    @NotBlank val username: String,
    @NotBlank val password: String
)
