package com.mmt.neo4j.study01;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SectionRepository extends JpaRepository<Sections, Integer> {
    List<Sections> findAll();
}
