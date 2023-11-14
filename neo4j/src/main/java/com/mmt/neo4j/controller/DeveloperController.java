package com.mmt.neo4j.controller;

import com.mmt.neo4j.entity.Developer;
import com.mmt.neo4j.service.DeveloperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeveloperController {

    @Autowired
    private DeveloperService developerService;

    @GetMapping("/")
    public Developer findAmud() {
        return developerService.findAmud();
    }
}
