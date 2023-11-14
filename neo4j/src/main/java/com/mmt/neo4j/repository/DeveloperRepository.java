package com.mmt.neo4j.repository;

import com.mmt.neo4j.entity.Developer;
import org.neo4j.driver.internal.shaded.reactor.core.publisher.Mono;
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;

public interface DeveloperRepository extends ReactiveNeo4jRepository<Developer, String> {
    Mono<Developer> findOneByName(String name);
}
