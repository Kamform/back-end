package com.design.platform.resourceplatform.repositories;

import com.design.platform.resourceplatform.entities.Category;
import com.design.platform.resourceplatform.entities.File;
import com.design.platform.resourceplatform.entities.Resource;
import com.design.platform.resourceplatform.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, Integer>, JpaSpecificationExecutor<Resource> {

    Page<Resource> findAllByCategory(Category category, Pageable pageable);

    Page<Resource> findAllByAuthor(User author, Pageable pageable);

    Page<Resource> findAllByFavored(User favoriteBy, Pageable pageable);

    Page<Resource> findAllByFiles(File file, Pageable pageable);
}
