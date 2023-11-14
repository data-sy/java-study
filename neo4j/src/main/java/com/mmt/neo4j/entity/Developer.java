package com.mmt.neo4j.entity;

import lombok.Getter;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Getter
@Node("Developer")
public class Developer {
    @Id
    private String name;
    private String country;
    private int age;

    public Developer(String name, String country, int age) {
        this.name = name;
        this.country = country;
        this.age = age;
    }
}