package com.design.platform.resourceplatform.services

import com.design.platform.resourceplatform.entities.Admin
import com.design.platform.resourceplatform.mappers.auto
import com.design.platform.resourceplatform.repositories.AdminRepository
import com.design.platform.resourceplatform.transfer.AdminBooth
import com.design.platform.resourceplatform.transfer.AdminDefiner
import com.design.platform.resourceplatform.transfer.AdminRecorder
import com.design.platform.resourceplatform.utils.PageHolder
import com.design.platform.resourceplatform.utils.PageParam
import com.design.platform.resourceplatform.utils.auto
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class AdminService(
    val repo: AdminRepository
) {

    fun findAll(request: PageRequest): PageHolder<AdminBooth> {
        return repo.findAll(request).map { it.auto }.auto
    }

    fun findOne(id: Int): AdminBooth {
        return repo.findById(id).orElseThrow().auto
    }

    fun update(recorder: AdminRecorder){
        val result = repo.save(recorder.auto)
        println(result)
    }

    fun create(definer: AdminDefiner){
        val result = repo.save(definer.auto)
        println(result)
    }

    fun delete(id: Int){
        repo.deleteById(id)
    }
}
