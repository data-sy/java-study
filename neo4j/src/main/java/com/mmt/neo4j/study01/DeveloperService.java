package com.mmt.neo4j.study01;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeveloperService {
    @Autowired
    private DeveloperRepository developerRepository;

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private SectionJdbcRepository sectionJdbcRepository;

    public Developer findAmud(){
        return developerRepository.findOneByName("amud").block();
    }

    public Developer findCindyFriend(){
        return developerRepository.findOneByName("cindy").block();
    }

    public List<Sections> findSections(){
        return sectionRepository.findAll();
    }

    public List<String> findByJdbc(){
        return sectionJdbcRepository.findAllSection();
    }

}
