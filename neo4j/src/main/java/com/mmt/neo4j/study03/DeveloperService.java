package com.mmt.neo4j.study03;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.neo4j.core.ReactiveNeo4jClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class DeveloperService {
    @Autowired
    private DeveloperRepository developerRepository;

    private final ReactiveNeo4jClient neo4jClient;

    public DeveloperService(ReactiveNeo4jClient neo4jClient) {
        this.neo4jClient = neo4jClient;
    }

    public Developer findAmud(){
        return developerRepository.findOneByName("amud").block();
    }

    public Developer findRobin(){
        return developerRepository.findOneByName("robin").block();
    }
    public Developer findFriend(String name){
        return developerRepository.findOneByName(name).block();
    }

    public Flux<Developer> findNodes(String name){
        return developerRepository.findNodes(name);
    }

//    public Flux<PathResult> findObject(String name){
//        return developerRepository.findNodesAndRelationships(name);
//    }

    public Flux<String> findNames(){
        return developerRepository.findNames();
    }


    }
