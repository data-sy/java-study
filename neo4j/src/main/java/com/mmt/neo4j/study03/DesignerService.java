package com.mmt.neo4j.study03;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class DesignerService {
    @Autowired
    private DesignerRepository designerRepository;

    public Designer findFriend(String name){
        return designerRepository.findOneByName(name).block();
    }


}
