package com.design.platform.resourceplatform.repositories;

import com.design.platform.resourceplatform.entities.File;
import com.design.platform.resourceplatform.entities.Resource;
import com.design.platform.resourceplatform.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Page<User> findAllByFollowed(User followed, Pageable pageable);

    Page<User> findAllByFollowers(User followers, Pageable pageable);

    Optional<User> findByPublished(Resource published);

    Page<User> findAllByFavorites(Resource favorite, Pageable pageable);

    Page<User> findAllByFavoritesId(int id, Pageable pageable);

    Optional<User> findByFiles(File file);
}
