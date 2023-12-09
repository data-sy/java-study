package com.mmt.neo4j.study03;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
public class DeveloperController {

    @Autowired
    private DeveloperService developerService;

    @GetMapping("/amud")
    public Developer findAmud(){
        return  developerService.findAmud();
    }

    @GetMapping("/robin")
    public Developer findRobin(){
        return  developerService.findRobin();
    }

    @GetMapping("/")
    public Developer findFriend(@RequestParam String name){
        return  developerService.findFriend(name);
    }

    // 원하는 깊이까지의 노드들 찾을 수 있음
    @GetMapping("/node")
    public Flux<Developer> findNodes(@RequestParam String name){
        return  developerService.findNodes(name);
    }

//    @GetMapping("/relation")
//    public Flux<Developer> findRelation(@RequestParam String name){
//        return  developerService.findRelation(name);
//
//    @GetMapping("/object")
//    public Flux<PathResult> findObject(@RequestParam String name){
//        return  developerService.findObject(name);
//    }

    @GetMapping("/names")
    public Flux<String> findNames(){
        return  developerService.findNames();
    }

}
