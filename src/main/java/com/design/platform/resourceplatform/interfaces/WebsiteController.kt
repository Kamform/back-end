package com.design.platform.resourceplatform.interfaces

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.design.platform.resourceplatform.entities.Account
import com.design.platform.resourceplatform.entities.Admin
import com.design.platform.resourceplatform.entities.User
import com.design.platform.resourceplatform.mappers.auto
import com.design.platform.resourceplatform.repositories.UserRepository
import com.design.platform.resourceplatform.services.AccountService
import com.design.platform.resourceplatform.services.AdminService
import com.design.platform.resourceplatform.transfer.AdminDefiner
import com.design.platform.resourceplatform.transfer.params.Authenticator
import com.design.platform.resourceplatform.transfer.results.AuthResult
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.InternalAuthenticationServiceException
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.nio.file.Files
import java.nio.file.Paths
import java.time.LocalDateTime
import java.time.ZoneOffset
import javax.security.auth.login.AccountNotFoundException
import javax.servlet.http.HttpServletResponse

@RestController
class WebsiteController(
    private val accounts: AccountService,
    private val admins: AdminService,
    private val users: UserRepository,
    private val encoder: PasswordEncoder
) {

    @GetMapping("/init")
    fun init(): String {
        val lock = Paths.get("init.lock")
        if (Files.exists(lock)) return "Done"

        val root = admins.create(AdminDefiner(
            username = "root",
            password = "root"
        )).also {
            println(it)
        }

        Files.createFile(lock)
        return "Created and done"
    }

    @GetMapping("/api/master")
    fun test(@AuthenticationPrincipal master: Account): Any {
        return when (master) {
            is Admin -> master.auto
            is User -> master.auto()
            else -> master
        }
    }

    @PostMapping("/api/authenticate")
    fun authenticate(
        @Validated @RequestBody auth: Authenticator
    ): AuthResult {

        // check username
        val list = accounts.findAll(auth.username)
        list += users.findAll { root, _, builder ->
            builder.or(
                builder.equal(root.get<String>("email"), auth.username),
                builder.equal(root.get<String>("phone"), auth.username)
            )
        }

        if (list.isEmpty())
            throw AccountNotFoundException("account ont found")
        if (list.size > 1)
            throw InternalAuthenticationServiceException("internal exception")
        if (!encoder.matches(auth.password, list.first().password))
            throw BadCredentialsException("wrong password")

        // return token
        return AuthResult(
            JWT.create()
                .withClaim("user", auth.username)
                .withClaim("from", "resource-platform")
                .withClaim("expr", LocalDateTime.now().plusMinutes(30).toEpochSecond(ZoneOffset.UTC))
                .withClaim("dead", LocalDateTime.now().plusMonths(1).toEpochSecond(ZoneOffset.UTC))
                .sign(Algorithm.HMAC256("resource-platform-secret")),
            list.first()
        )
    }

    @PostMapping("/api/refresh-token")
    fun refreshToken(@RequestBody string: String): AuthResult {
        val token = JWT.require(
            Algorithm.HMAC256("resource-platform-secret")
        ).withClaim("from", "resource-platform").build()
            .verify(string)

        val dead = LocalDateTime.ofEpochSecond(token.getClaim("dead").asLong(), 0, ZoneOffset.UTC)
        if (LocalDateTime.now().isAfter(dead)) {
            throw BadCredentialsException("token is invalid")
        }

        val username = token.getClaim("user").asString();
        // check username
        val list = accounts.findAll(username)
        list += users.findAll { root, _, builder ->
            builder.or(
                builder.equal(root.get<String>("email"), username),
                builder.equal(root.get<String>("phone"), username)
            )
        }

        if (list.isEmpty())
            throw AccountNotFoundException("account ont found")
        if (list.size > 1)
            throw InternalAuthenticationServiceException("internal exception")

        // return token
        return AuthResult(
            JWT.create()
                .withClaim("user", username)
                .withClaim("from", "resource-platform")
                .withClaim("expr", LocalDateTime.now().plusMinutes(30).toEpochSecond(ZoneOffset.UTC))
                .withClaim("dead", token.getClaim("dead").asLong())
                .sign(Algorithm.HMAC256("resource-platform-secret")),
            list.first()
        )
    }
}
