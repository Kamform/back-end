package com.design.platform.resourceplatform.interfaces

import com.design.platform.resourceplatform.entities.Account
import com.design.platform.resourceplatform.services.AdminService
import com.design.platform.resourceplatform.transfer.AdminBooth
import com.design.platform.resourceplatform.transfer.AdminDefiner
import com.design.platform.resourceplatform.transfer.AdminRecorder
import com.design.platform.resourceplatform.utils.PageHolder
import com.design.platform.resourceplatform.utils.PageParam
import com.design.platform.resourceplatform.utils.auto
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/admin")
class AdminController(
    val service: AdminService
) {

    // Methods GET
    // ===============================================
    @GetMapping
    fun findAll(param: PageParam) = service.findAll(param.auto)

    @GetMapping("/{id}")
    fun findOne(@PathVariable id: Int) = service.findOne(id)

    // Methods POST
    // ===============================================
    @PostMapping
    fun update(@RequestBody recorder: AdminRecorder) = service.update(recorder)

    @PutMapping
    fun create(@RequestBody definer: AdminDefiner) = service.create(definer)

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int) = service.delete(id)
}
