package com.mmt.neo4j.study03;

import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface DeveloperRepository extends ReactiveNeo4jRepository<Developer, String> {
    Mono<Developer> findOneByName(String name);

    @Query("MATCH path = (start_node)-[*1..4]->(n {name: $name}) RETURN nodes(path)")
    Flux<Developer> findNodes(@Param("name") String name);

//    @Query("MATCH path = (start_node)-[*1..4]->(n {name: $name}) RETURN nodes(path), relationships(path)")
//    Flux<PathResult> findNodesAndRelationships(@Param("name") String name);
    @Query("MATCH (n:Developer) RETURN [name IN n.name] AS names")
    Flux<String> findNames();

}
