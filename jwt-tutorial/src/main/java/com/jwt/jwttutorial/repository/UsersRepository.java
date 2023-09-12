package com.jwt.jwttutorial.repository;

import com.jwt.jwttutorial.entity.Users;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {
    @EntityGraph(attributePaths = "userAuthoritySet")
    Optional<Users> findOneWithAuthoritiesByUserEmail(String userEmail);
//
//    Optional<Users> findByEmail(String userEmail);
}
