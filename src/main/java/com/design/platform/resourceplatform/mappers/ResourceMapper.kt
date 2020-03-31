package com.design.platform.resourceplatform.mappers

import com.design.platform.resourceplatform.configuration.ApplicationContextHolder
import com.design.platform.resourceplatform.entities.Resource
import com.design.platform.resourceplatform.entities.User
import com.design.platform.resourceplatform.repositories.CategoryRepository
import com.design.platform.resourceplatform.repositories.FileRepository
import com.design.platform.resourceplatform.repositories.ResourceRepository
import com.design.platform.resourceplatform.transfer.ResourceBooth
import com.design.platform.resourceplatform.transfer.ResourceDefiner
import com.design.platform.resourceplatform.transfer.ResourceRecorder
import org.springframework.context.ApplicationContext
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

//@Component
//class ResourceMapper(
//    val repository: ResourceRepository,
//    val category: CategoryRepository,
//    val file: FileRepository
//) {
//
//    fun map(definer: ResourceDefiner): Resource {
//        val resource = Resource()
//
//        resource.author = SecurityContextHolder.getContext().authentication.principal as User
//        resource.title = definer.title
//        resource.category = category.findById(definer.category).orElseThrow()
//        resource.summary = definer.summary
//        resource.files = file.findAllById(definer.files)
//        return resource
//    }
//
//    fun map(recorder: ResourceRecorder): Resource {
//        val resource: Resource = repository.findById(recorder.id).orElseThrow()
//
//        resource.title = recorder.title
//        resource.category = category.findById(recorder.category).orElseThrow()
//        resource.summary = recorder.summary
//        resource.files = file.findAllById(recorder.files)
//        return resource
//    }
//}

fun Resource.map(): ResourceBooth = ResourceBooth(
    id, title, category.map(), author.map(), summary, created, updated
)

fun ResourceDefiner.map(): Resource {
    val categoryRepo = ApplicationContextHolder.getBean(CategoryRepository::class.java)
    val fileRepo = ApplicationContextHolder.getBean(FileRepository::class.java)

    val resource = Resource()

    resource.author = SecurityContextHolder.getContext().authentication.principal as User
    resource.title = title
    resource.category = categoryRepo.findById(category).orElseThrow()
    resource.summary = summary
    resource.files = fileRepo.findAllById(files)
    return resource
}

fun ResourceRecorder.map(): Resource {
    val repo = ApplicationContextHolder.getBean(ResourceRepository::class.java)
    val categoryRepo = ApplicationContextHolder.getBean(CategoryRepository::class.java)
    val fileRepo = ApplicationContextHolder.getBean(FileRepository::class.java)

    val resource: Resource = repo.findById(id).orElseThrow()

    resource.title = title
    resource.category = categoryRepo.findById(category).orElseThrow()
    resource.summary = summary
    resource.files = fileRepo.findAllById(files)
    return resource
}
