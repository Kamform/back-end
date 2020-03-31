package com.design.platform.resourceplatform.repositories

import com.design.platform.resourceplatform.entities.Admin
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

@Repository
interface AdminRepository : JpaRepository<Admin, Int>, JpaSpecificationExecutor<Admin>
