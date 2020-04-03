package com.design.platform.resourceplatform.transfer.results

import com.design.platform.resourceplatform.entities.Account
import com.design.platform.resourceplatform.transfer.UserBooth

data class AuthResult(
    val token: String,
    val info: Account
)
