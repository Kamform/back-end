package com.design.platform.resourceplatform.interfaces

import com.design.platform.resourceplatform.entities.Account
import com.design.platform.resourceplatform.mappers.auto
import com.design.platform.resourceplatform.repositories.AccountRepository
import com.design.platform.resourceplatform.repositories.AdminRepository
import com.design.platform.resourceplatform.repositories.UserRepository
import com.design.platform.resourceplatform.services.AccountService
import com.design.platform.resourceplatform.services.AdminService
import com.design.platform.resourceplatform.services.UserService
import com.design.platform.resourceplatform.transfer.AdminDefiner
import com.design.platform.resourceplatform.transfer.params.Authenticator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties
import org.springframework.security.core.Authentication
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.nio.file.Files
import java.nio.file.Paths

@RestController
class WebsiteController(
    var accounts: AccountService,
    var admins: AdminService,
    var users: UserService
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

    @GetMapping("/api/authenticate")
    fun authenticate(auth: Authenticator) {
        println(accounts.findAll())

        // check username

        // generate jwt

        // return token
    }
}
