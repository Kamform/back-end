package com.design.platform.resourceplatform.interfaces

import com.design.platform.resourceplatform.mappers.auto
import com.design.platform.resourceplatform.repositories.AdminRepository
import com.design.platform.resourceplatform.transfer.AdminDefiner
import com.design.platform.resourceplatform.transfer.params.Authenticator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.nio.file.Files
import java.nio.file.Paths

@RestController
class WebsiteController {

    @Autowired
    lateinit var adminRepo: AdminRepository

    @GetMapping("/init")
    fun init(): String {
        val lock = Paths.get("init.lock")
        if (Files.exists(lock)) return "Done"

        val root = adminRepo.save(AdminDefiner(
            username = "root",
            password = "root"
        ).auto)

        println(root)
        Files.createFile(lock)
        return "Created and done"
    }

    @GetMapping("/api/authenticate")
    fun authenticate(auth: Authenticator) {
    }
}
