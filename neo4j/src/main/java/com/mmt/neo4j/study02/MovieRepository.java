package com.mmt.neo4j.study02;

import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;
import reactor.core.publisher.Mono;

public interface MovieRepository extends ReactiveNeo4jRepository<MovieEntity, String> {

    // 0~1개 반환은 Mono
    // 0~n개 반환은 Flux

    Mono<MovieEntity> findOneByTitle(String title);



}
