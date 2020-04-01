package com.design.platform.resourceplatform.interfaces

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.design.platform.resourceplatform.entities.Account
import com.design.platform.resourceplatform.repositories.UserRepository
import com.design.platform.resourceplatform.services.AccountService
import com.design.platform.resourceplatform.services.AdminService
import com.design.platform.resourceplatform.transfer.AdminDefiner
import com.design.platform.resourceplatform.transfer.params.Authenticator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.nio.file.Files
import java.nio.file.Paths
import java.time.LocalDateTime
import java.time.ZoneOffset
import javax.security.auth.login.AccountNotFoundException

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

    @GetMapping("/test")
    fun test(@AuthenticationPrincipal master: Account): Account {
        return master
    }

    @GetMapping("/api/authenticate")
    fun authenticate(
        @Validated @RequestBody auth: Authenticator
    ): String {

        // check username
        val list = accounts.findAll(auth.username)
        list += users.findAll { root, _, builder ->
            builder.or(
                builder.equal(root.get<String>("email"), auth.username),
                builder.equal(root.get<String>("phone"), auth.username)
            )
        }

        if (list.isEmpty()) return "account ont found"
        if (list.size > 1) return "internal exception"
        if (!encoder.matches(auth.password, list.first().password))
            return "wrong password"

        // return token
        return JWT.create()
            .withClaim("user", auth.username)
            .withClaim("from", "resource-platform")
            .withClaim("expr", LocalDateTime.now().plusMinutes(30).toEpochSecond(ZoneOffset.UTC))
            .withClaim("dead", LocalDateTime.now().plusMonths(1).toEpochSecond(ZoneOffset.UTC))
            .sign(Algorithm.HMAC256("resource-platform-secret"))
    }
}
