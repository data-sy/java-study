package com.mmt.neo4j;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Sections {

    @Id
    private int sectionId;

    private String sectionName;
}
