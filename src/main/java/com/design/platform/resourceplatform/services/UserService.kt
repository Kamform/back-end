package com.design.platform.resourceplatform.services

import com.design.platform.resourceplatform.entities.File
import com.design.platform.resourceplatform.entities.Resource
import com.design.platform.resourceplatform.entities.User
import com.design.platform.resourceplatform.mappers.auto
import com.design.platform.resourceplatform.repositories.FileRepository
import com.design.platform.resourceplatform.repositories.ResourceRepository
import com.design.platform.resourceplatform.repositories.UserRepository
import com.design.platform.resourceplatform.transfer.UserDefiner
import com.design.platform.resourceplatform.transfer.UserRecorder
import com.design.platform.resourceplatform.utils.PageHolder
import com.design.platform.resourceplatform.utils.auto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class UserService {

    @Autowired
    lateinit var repo: UserRepository

    @Autowired
    lateinit var resourceRepo: ResourceRepository

    @Autowired
    lateinit var fileRepo: FileRepository


    // ================================
    // find all
    // ================================
    fun findAll(request: PageRequest): PageHolder<User> {
        return repo.findAll(request).auto
    }

    fun findAllIdols(id: Int, request: PageRequest): PageHolder<User> {
        return repo.findAllByFansId(id, request).auto
    }

    fun findAllFans(id: Int, request: PageRequest): PageHolder<User> {
        return repo.findAllByIdolsId(id, request).auto
    }

    fun findAllResources(id: Int, request: PageRequest): PageHolder<Resource> {
        return resourceRepo.findAllByAuthorId(id, request).auto
    }

    fun findAllFavorites(id: Int, request: PageRequest): PageHolder<Resource> {
        return resourceRepo.findAllByFavoredId(id, request).auto
    }

    fun findAllFiles(id: Int, request: PageRequest): PageHolder<File> {
        return fileRepo.findAllByContainedId(id, request).auto
    }

    // ================================
    // find one
    // ================================
    fun findOne(id: Int): User? {
        return repo.findById(id).orElseThrow()
    }

    // ================================
    // modifies
    // ================================
    fun update(recorder: UserRecorder) {
        val result = repo.save(recorder.auto)
        println(result)
    }

    fun create(definer: UserDefiner) {
        val result = repo.save(definer.auto)
        println(result)
    }

    fun delete(id: Int) {
        repo.deleteById(id)
    }
}
