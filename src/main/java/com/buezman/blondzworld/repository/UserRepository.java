package com.buezman.blondzworld.repository;

import com.buezman.blondzworld.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUsername(String username);

    Optional<User> findByUsernameOrEmail(String usernameOrEmail, String usernameOrEmail1);
}
