package com.design.platform.resourceplatform.services

import com.design.platform.resourceplatform.entities.Account
import com.design.platform.resourceplatform.repositories.AccountRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class AccountService {

    @Autowired
    lateinit var repo: AccountRepository

    fun findAll() = repo.findAll()

    fun findAll(username: String): MutableList<Account> = repo.findAll { root, _, builder ->
            builder.equal(root.get<String>("username"), username)
        }

    fun findOne(id: Int) = repo.findById(id).orElseThrow()

}
