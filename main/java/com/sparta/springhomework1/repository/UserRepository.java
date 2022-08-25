package com.sparta.springhomework1.repository;

//import com.sparta.springcore.model.User;
import com.sparta.springhomework1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}