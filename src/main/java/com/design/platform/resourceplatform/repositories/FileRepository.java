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
    Page<File> findAllByAuthorId(int id, Pageable pageable);

    Page<File> findAllByContained(Resource resource, Pageable pageable);
    Page<File> findAllByContainedId(int id, Pageable pageable);
}
