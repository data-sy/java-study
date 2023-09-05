package com.study.jwt;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserEmailAndUserPassword(String email, String password);
    Optional<User> findByUserEmail(String email);
    boolean existsUsersByUserEmail(String email);
}
