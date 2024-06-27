package com.example.user_project.repository;

import com.example.user_project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {


    boolean existsByEmail(String email);
}
