package com.design.platform.resourceplatform.mappers

import com.design.platform.resourceplatform.entities.User
import com.design.platform.resourceplatform.repositories.UserRepository
import com.design.platform.resourceplatform.transfer.UserBooth
import com.design.platform.resourceplatform.transfer.UserDefiner
import com.design.platform.resourceplatform.transfer.UserRecorder
import com.design.platform.resourceplatform.utils.ApplicationContextHolder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

fun User.auto(): UserBooth = UserBooth(
    id,
    username,
    enable,
    lock,
    created,
    updated,
    email,
    phone
)

val UserDefiner.auto: User
    get() {
        val encoder = ApplicationContextHolder.getBean(PasswordEncoder::class.java)

        return User().also {
            it.username = username
            it.password = encoder.encode(password)
            it.nickname = nickname
            it.email = email
            it.phone = phone
            it.enable = isEnable
            it.lock = isLock
        }
    }

val UserRecorder.auto: User
    get() {
        val encoder = ApplicationContextHolder.getBean(PasswordEncoder::class.java)
        val repo = ApplicationContextHolder.getBean(UserRepository::class.java)

        return repo.findById(id).orElseThrow().also {
            when {
                password.isNotBlank() -> it.password = encoder.encode(password)
                nickname.isNotBlank() -> it.nickname = nickname
                email.isNotBlank() -> it.email = email
                phone.isNotBlank() -> it.phone = phone
                isEnable != null -> it.enable = isEnable
                isLock != null -> it.lock = isLock
            }
        }
    }


