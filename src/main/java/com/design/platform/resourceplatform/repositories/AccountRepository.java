package com.design.platform.resourceplatform.repositories;

import com.design.platform.resourceplatform.entities.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    Page<Account> findAllByAdminIsTrue(Pageable request);
    Optional<Account> findByUsername(String username);
}
