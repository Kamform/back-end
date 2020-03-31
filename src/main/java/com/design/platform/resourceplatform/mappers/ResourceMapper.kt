package com.design.platform.resourceplatform.mappers

import com.design.platform.resourceplatform.utils.ApplicationContextHolder
import com.design.platform.resourceplatform.entities.Resource
import com.design.platform.resourceplatform.entities.User
import com.design.platform.resourceplatform.repositories.CategoryRepository
import com.design.platform.resourceplatform.repositories.FileRepository
import com.design.platform.resourceplatform.repositories.ResourceRepository
import com.design.platform.resourceplatform.transfer.ResourceBooth
import com.design.platform.resourceplatform.transfer.ResourceDefiner
import com.design.platform.resourceplatform.transfer.ResourceRecorder
import org.springframework.security.core.context.SecurityContextHolder

fun Resource.auto(): ResourceBooth = ResourceBooth(
    id, title, category.auto(), author.auto(), summary, created, updated
)

fun ResourceDefiner.auto(): Resource {
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

fun ResourceRecorder.auto(): Resource {
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
