package com.project.manage.repository;

import com.project.manage.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByName(String username);
    boolean existsByName(String username);
    boolean existsByEmail(String email);
}
