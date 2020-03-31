package com.design.platform.resourceplatform.mappers

import com.design.platform.resourceplatform.entities.Admin
import com.design.platform.resourceplatform.repositories.AdminRepository
import com.design.platform.resourceplatform.transfer.AdminBooth
import com.design.platform.resourceplatform.transfer.AdminDefiner
import com.design.platform.resourceplatform.transfer.AdminRecorder
import com.design.platform.resourceplatform.utils.ApplicationContextHolder
import org.springframework.security.crypto.password.PasswordEncoder

val Admin.auto
    get() = AdminBooth(
        id, nickname, username, enable, lock, created, updated
    )

val AdminDefiner.auto: Admin
    get() {
        val encoder = ApplicationContextHolder.getBean(PasswordEncoder::class.java)
        return Admin().also {
            it.username = this.username
            it.password = encoder.encode(this.password)
            it.enable = isEnable
            it.lock = isLock
        }
    }

val AdminRecorder.auto: Admin
    get() {
        val encoder = ApplicationContextHolder.getBean(PasswordEncoder::class.java)
        val repo = ApplicationContextHolder.getBean(AdminRepository::class.java)
        return repo.findById(id).orElseThrow().also {
            it.nickname = this.nickname
            if (this.password.isNotBlank())
                it.password = encoder.encode(this.password)
            it.enable = isEnable
            it.lock = isLock
        }
    }
