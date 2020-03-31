package com.design.platform.resourceplatform.interfaces

import com.design.platform.resourceplatform.entities.Account
import com.design.platform.resourceplatform.entities.User
import com.design.platform.resourceplatform.services.UserService
import com.design.platform.resourceplatform.transfer.*
import com.design.platform.resourceplatform.transfer.patch.Follow
import com.design.platform.resourceplatform.utils.PageHolder
import com.design.platform.resourceplatform.utils.PageParam
import com.design.platform.resourceplatform.utils.auto
import org.springframework.security.core.annotation.AuthenticationPrincipal
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
//      PATCH /follow  关注用户       用户权限
//      DELETE /            注销用户       主人权限
@RestController
@RequestMapping("/api/user")
class UserController(
    val service: UserService
) {

    // Methods GET
    // ===============================================
    @GetMapping
    fun findAll(param: PageParam) = service.findAll(param.auto)

    @GetMapping("/{id}/fans")
    fun findAllFans(@PathVariable id: Int, param: PageParam) = service.findAllFans(id, param.auto)

    @GetMapping("/{id}/idols")
    fun findAllIdols(@PathVariable id: Int, param: PageParam) = service.findAllIdols(id, param.auto)

    @GetMapping("/{id}/resources")
    fun findAllResources(@PathVariable id: Int, param: PageParam) = service.findAllResources(id, param.auto)

    @GetMapping("/{id}/favorites")
    fun findAllFavorites(@PathVariable id: Int, param: PageParam) = service.findAllFavorites(id, param.auto)

    @GetMapping("/{id}/files")
    fun findAllFiles(@PathVariable id: Int, param: PageParam) = service.findAllFiles(id, param.auto)

    @GetMapping("/{id}")
    fun findOne(@PathVariable id: Int) = service.findOne(id)

    // Methods POST
    // ===============================================
    @PostMapping
    fun update(@RequestBody recorder: UserRecorder) = service.update(recorder)

    @PutMapping
    fun create(@RequestBody definer: UserDefiner) = service.create(definer)

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int) = service.delete(id)
}
