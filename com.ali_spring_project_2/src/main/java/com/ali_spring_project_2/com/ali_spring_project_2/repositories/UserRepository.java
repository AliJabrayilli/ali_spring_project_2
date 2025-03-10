package com.ali_spring_project_2.com.ali_spring_project_2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ali_spring_project_2.com.ali_spring_project_2.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    boolean existsByUsername(String username);
}