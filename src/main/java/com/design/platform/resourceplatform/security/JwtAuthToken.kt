package com.design.platform.resourceplatform.security

import com.auth0.jwt.interfaces.DecodedJWT
import com.design.platform.resourceplatform.entities.Account
import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.core.authority.AuthorityUtils

class JwtAuthToken(
    private val account: Account,
    private val token: DecodedJWT
) : AbstractAuthenticationToken(
    AuthorityUtils.NO_AUTHORITIES
) {
    init {
        isAuthenticated = true
    }

    override fun getPrincipal(): Account = account

    override fun getCredentials(): DecodedJWT = token
}
