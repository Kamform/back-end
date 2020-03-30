package com.design.platform.resourceplatform.repositories;

import com.design.platform.resourceplatform.entities.Category;
import com.design.platform.resourceplatform.entities.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

//    Optional<Category> findByName(String name);

    Optional<Category> findByResources(Resource resource);
}
