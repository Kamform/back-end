package com.design.platform.resourceplatform.repositories;

import com.design.platform.resourceplatform.entities.File;
import com.design.platform.resourceplatform.entities.Resource;
import com.design.platform.resourceplatform.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<File, Integer> {

    Page<File> findAllByAuthor(User author, Pageable pageable);

    Page<File> findAllByContainedBy(Resource resource, Pageable pageable);
}
