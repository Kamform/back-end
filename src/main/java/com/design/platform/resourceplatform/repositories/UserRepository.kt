package com.design.platform.resourceplatform.repositories

import com.design.platform.resourceplatform.entities.File
import com.design.platform.resourceplatform.entities.Resource
import com.design.platform.resourceplatform.entities.User
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<User, Int>, JpaSpecificationExecutor<User> {

    fun findAllByFans(followed: User?, pageable: Pageable?): Page<User?>?

    fun findAllByIdols(followers: User?, pageable: Pageable?): Page<User?>?

    fun findByResources(published: Resource?): Optional<User?>?

    fun findAllByFavorites(favorite: Resource?, pageable: Pageable?): Page<User?>?

    fun findByFiles(file: File?): Optional<User?>?

    fun findAllByFansId(id: Int, pageable: Pageable): Page<User>

    fun findAllByIdolsId(id: Int, pageable: Pageable): Page<User>

    fun findAllByFavoritesId(id: Int, pageable: Pageable?): Page<User?>

}
