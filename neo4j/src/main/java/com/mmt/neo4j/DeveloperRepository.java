package com.mmt.neo4j;

import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;
import reactor.core.publisher.Mono;

public interface DeveloperRepository extends ReactiveNeo4jRepository<Developer, String> {
    Mono<Developer> findOneByName(String name);
}
