//package com.mmt.neo4j.study01;
//
//import com.mmt.neo4j.study02.PersonEntity;
//import lombok.Getter;
//import org.springframework.data.neo4j.core.schema.Id;
//import org.springframework.data.neo4j.core.schema.Node;
//import org.springframework.data.neo4j.core.schema.Relationship;
//
//import java.util.HashSet;
//import java.util.Set;
//
//import static org.springframework.data.neo4j.core.schema.Relationship.Direction.INCOMING;
//
//@Getter
//@Node("Developer")
//public class Developer {
//
//    @Id
//    private String name;
//    private String country;
//    private int age;
//    @Relationship(type = "IS_FRIEND", direction = INCOMING)
//    private Set<Developer> friends = new HashSet<>();
//
//    public Developer(String name, String country, int age){
//        this.name = name;
//        this.country = country;
//        this.age = age;
//    }
//}
