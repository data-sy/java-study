package com.mmt.neo4j.study03;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DesignerController {

    @Autowired
    private DesignerService designerService;
    @GetMapping("/cycle")
    public Designer findFriend(@RequestParam String name){
        return  designerService.findFriend(name);
    }

}
