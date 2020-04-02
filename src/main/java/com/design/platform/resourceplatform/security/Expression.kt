package com.design.platform.resourceplatform.security

import com.design.platform.resourceplatform.entities.*
import com.design.platform.resourceplatform.repositories.FileRepository
import com.design.platform.resourceplatform.repositories.ResourceRepository
import com.design.platform.resourceplatform.repositories.UserRepository
import org.springframework.stereotype.Component

@Component
class Expression(
    val users: UserRepository,
    val resources: ResourceRepository,
    val files: FileRepository
) {

    fun isAdmin(account: Account): Boolean {
        return account is Admin
    }

    fun isAdminMaster(account: Account): Boolean {
        return account is Admin
    }

    fun isUser(account: Account): Boolean {
        return account is User
    }

    fun isUserMaster(account: Account, id: Int): Boolean {
        return when (account) {
            is Admin -> false
            else -> {
                val target = users.getOne(id)
                account.id == target.id
            }
        }
    }

    fun isUserMasterResource(account: Account, id: Int): Boolean {
        return when (account) {
            is Admin -> false
            else -> {
                val user = account as User
                val target = resources.getOne(id)
                user.resources.contains(target)
            }
        }
    }

    fun isUserMasterFile(account: Account, id: Int): Boolean {
        return when (account) {
            is Admin -> false
            else -> {
                val user = account as User
                val target = files.getOne(id)
                user.files.contains(target)
            }
        }
    }
}
