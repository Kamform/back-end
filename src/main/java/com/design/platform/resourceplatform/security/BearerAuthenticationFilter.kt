package com.design.platform.resourceplatform.security

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.design.platform.resourceplatform.repositories.AccountRepository
import com.design.platform.resourceplatform.utils.ApplicationContextHolder
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.util.matcher.RequestHeaderRequestMatcher
import org.springframework.web.filter.OncePerRequestFilter
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*
import javax.security.auth.login.AccountNotFoundException
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class BearerAuthenticationFilter : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        chain: FilterChain) {
        if (RequestHeaderRequestMatcher("Authorization").matches(request)) {

            // get string
            val string = request.getHeader("Authorization").substring("Bearer ".length)

            // verify token
            val token = JWT.require(
                Algorithm.HMAC256("resource-platform-secret")
            ).withClaim("from", "resource-platform").build()
                .verify(string)

            val expr = LocalDateTime.ofEpochSecond(token.getClaim("expr").asLong(), 0, ZoneOffset.UTC)
            if (LocalDateTime.now().isAfter(expr)) {
                response.status = HttpServletResponse.SC_UNAUTHORIZED
                response.writer.print("token expired")
                return
            }
            val dead = LocalDateTime.ofEpochSecond(token.getClaim("dead").asLong(), 0, ZoneOffset.UTC)
            if (LocalDateTime.now().isAfter(dead)) {
                response.status = HttpServletResponse.SC_UNAUTHORIZED
                response.writer.print("token invalid")
                return
            }

            // get account
            val repo: AccountRepository = ApplicationContextHolder.getBean(AccountRepository::class.java)
            val master = repo.findOne { root, _, builder ->
                builder.equal(root.get<String>("username"), token.getClaim("user").asString())
            }.run {
                try {
                    orElseThrow(::AccountNotFoundException)
                } catch (ex: AccountNotFoundException) {
                    response.status = HttpServletResponse.SC_UNAUTHORIZED
                    response.writer.print("no such account")
                    return
                }
            }

            // save to security context
            SecurityContextHolder.getContext().authentication = JwtAuthToken(master, token)
        }
        chain.doFilter(request, response)
    }
}
