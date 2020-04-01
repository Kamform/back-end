package com.design.platform.resourceplatform.security

import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class LoginFilter(
    val matcher: AntPathRequestMatcher = AntPathRequestMatcher("/api/authenticate", "POST")
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        chain: FilterChain) {

        if (matcher.matches(request)) {

            request.authenticate(response)

        } else {
            chain.doFilter(request, response)
        }
    }
}
