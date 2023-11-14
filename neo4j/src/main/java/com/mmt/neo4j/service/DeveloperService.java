package com.mmt.neo4j.service;

import com.mmt.neo4j.entity.Developer;
import com.mmt.neo4j.repository.DeveloperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeveloperService {

    @Autowired
    private DeveloperRepository developerRepository;

    public Developer findAmud() {
        return developerRepository.findOneByName("amud").block();
    }
}

