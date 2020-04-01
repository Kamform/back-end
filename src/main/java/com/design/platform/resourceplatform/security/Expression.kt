package com.design.platform.resourceplatform.security

import com.design.platform.resourceplatform.entities.Account
import com.design.platform.resourceplatform.entities.Admin
import com.design.platform.resourceplatform.entities.User
import org.springframework.security.core.annotation.AuthenticationPrincipal

class Expression {

    fun isAdmin(@AuthenticationPrincipal account: Account): Boolean {
        println(account)
        return account is Admin
    }

    fun isUser(@AuthenticationPrincipal account: Account): Boolean {
        println(account)
        return account is User
    }
}
