package com.mmt.neo4j.study03;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class DeveloperService {
    @Autowired
    private DeveloperRepository developerRepository;

    public Developer findAmud(){
        return developerRepository.findOneByName("amud").block();
    }

    public Developer findRobin(){
        return developerRepository.findOneByName("robin").block();
    }

    public Flux<Developer> findNodes(String name){
        return developerRepository.findNodes(name);
    }

//    public Flux<PathResult> findObject(String name){
//        return developerRepository.findNodesAndRelationships(name);
//    }


}
