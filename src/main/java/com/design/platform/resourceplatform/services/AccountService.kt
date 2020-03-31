package com.design.platform.resourceplatform.services

import com.design.platform.resourceplatform.repositories.AccountRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AccountService {

    @Autowired
    lateinit var repo: AccountRepository

    fun findAll() = repo.findAll()

    fun findOne(id: Int) = repo.findById(id).orElseThrow()
}
