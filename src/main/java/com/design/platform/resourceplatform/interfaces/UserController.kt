package com.design.platform.resourceplatform.interfaces

import com.design.platform.resourceplatform.entities.Account
import com.design.platform.resourceplatform.entities.User
import com.design.platform.resourceplatform.services.UserService
import com.design.platform.resourceplatform.transfer.*
import com.design.platform.resourceplatform.transfer.patch.Patch
import com.design.platform.resourceplatform.utils.PageParam
import com.design.platform.resourceplatform.utils.auto
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

//      GET /               用户列表       无权限
//      GET /{id}           单个用户信息   开放权限
//      GET /{id}/followers 用户的关注者   开放权限
//      GET /{id}/followed  用户关注的人   开放权限
//      GET /{id}/published 用户发布的资源 开放权限
//      GET /{id}/favorites 用户收藏的资源 开放权限
//      GET /{id}/files     用户上传的文件 主人权限
//      POST /              更新用户信息   主人权限
//      PUT /               注册用户       开放权限
//      PATCH /follow       关注用户       用户权限
//      DELETE /            注销用户       主人权限

@RestController
@RequestMapping("/api/user")
open class UserController(
    open val service: UserService
) {

    // Methods GET
    // ===============================================
    @GetMapping
    @PreAuthorize("permitAll()")
    fun findAll(param: PageParam) = service.findAll(param.auto)

    @GetMapping("/{id}/fans")
    @PreAuthorize("permitAll()")
    fun findAllFans(@PathVariable id: Int, param: PageParam) = service.findAllFans(id, param.auto)

    @GetMapping("/{id}/idols")
    @PreAuthorize("permitAll()")
    fun findAllIdols(@PathVariable id: Int, param: PageParam) = service.findAllIdols(id, param.auto)

    @GetMapping("/{id}/resources")
    @PreAuthorize("permitAll()")
    fun findAllResources(@PathVariable id: Int, param: PageParam) = service.findAllResources(id, param.auto)

    @GetMapping("/{id}/favorites")
    @PreAuthorize("permitAll()")
    fun findAllFavorites(@PathVariable id: Int, param: PageParam) = service.findAllFavorites(id, param.auto)

    @GetMapping("/{id}/files")
    @PreAuthorize("expression.isUserMaster(principal, id)")
    fun findAllFiles(@PathVariable id: Int, param: PageParam) = service.findAllFiles(id, param.auto)

    @GetMapping("/{id}")
    @PreAuthorize("permitAll()")
    fun findOne(@PathVariable id: Int) = service.findOne(id)

    // Methods POST
    // ===============================================
    @PostMapping
    @PreAuthorize("expression.isUserMaster(principal, recorder.id) or expression.isAdmin(principal)")
    fun update(@Validated @RequestBody recorder: UserRecorder) = service.update(recorder)

    @PutMapping
    @PreAuthorize("permitAll()")
    fun create(@Validated @RequestBody definer: UserDefiner) = service.create(definer)

    @PatchMapping("/follow")
    @PreAuthorize("expression.isUser(principal) or expression.isAdmin(principal)")
    fun follow(patch: Patch, @AuthenticationPrincipal account: Account) {
        service.follow(account as User, patch)
    }

    @PatchMapping("/lock")
    @PreAuthorize("expression.isAdmin(principal)")
    fun lock(patch: Patch){

    }

    @PatchMapping("/enable")
    @PreAuthorize("expression.isAdmin(principal)")
    fun enable(patch: Patch){

    }

    @DeleteMapping("/{id}")
    @PreAuthorize("expression.isUserMaster(principal, id) or expression.isAdmin(principal)")
    fun delete(@PathVariable id: Int) = service.delete(id)
}
