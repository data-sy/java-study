package com.mmt.neo4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DeveloperController {

    @Autowired
    private DeveloperService developerService;


    @GetMapping("/")
    public Developer findAmud(){
        return  developerService.findAmud();
    }

    @GetMapping("/sections")
    public List<Sections> findSections(){
        return developerService.findSections();
    }

    @GetMapping("/jdbc")
    public List<String> findJdbcSections(){
        return developerService.findByJdbc();
    }

}
