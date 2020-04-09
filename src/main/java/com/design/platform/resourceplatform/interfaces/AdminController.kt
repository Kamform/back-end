package com.design.platform.resourceplatform.interfaces

import com.design.platform.resourceplatform.security.Expression
import com.design.platform.resourceplatform.services.AdminService
import com.design.platform.resourceplatform.transfer.AdminDefiner
import com.design.platform.resourceplatform.transfer.AdminRecorder
import com.design.platform.resourceplatform.utils.PageParam
import com.design.platform.resourceplatform.utils.auto
import org.springframework.security.access.prepost.PostAuthorize
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/admin")
open class AdminController(
    open val service: AdminService
) {

    // Methods GET
    // ===============================================
    @GetMapping
    @PreAuthorize("@expression.isAdmin(principal)")
    open fun findAll(param: PageParam) = service.findAll(param.auto)

    @GetMapping("/{id}")
    @PreAuthorize("@expression.isAdmin(principal)")
    fun findOne(@PathVariable id: Int) = service.findOne(id)

    // Methods POST
    // ===============================================
    @PostMapping
    @PreAuthorize("@expression.isAdmin(principal)")
    fun update(@Validated @RequestBody recorder: AdminRecorder) {
        println(recorder)
        service.update(recorder)
    }

    @PutMapping
    @PreAuthorize("@expression.isAdmin(principal)")
    fun create(@Validated @RequestBody definer: AdminDefiner) {
        service.create(definer)
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@expression.isAdmin(principal)")
    fun delete(@PathVariable id: Int) {
        service.delete(id)
    }
}
