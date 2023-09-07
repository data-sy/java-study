package com.jwt.jwttutorial.repository;

import com.jwt.jwttutorial.entity.UserAuthority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAuthorityRepository extends JpaRepository<UserAuthority, Long> {

}
