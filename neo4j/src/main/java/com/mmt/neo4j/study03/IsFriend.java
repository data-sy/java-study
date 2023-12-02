//package com.mmt.neo4j.study03;
//
//import lombok.Data;
//import org.springframework.data.neo4j.core.schema.Id;
//import org.springframework.data.neo4j.core.schema.RelationshipProperties;
//import org.springframework.data.neo4j.core.schema.TargetNode;
//
// 관계도 엔티티로 만들어서 사용해보려 했는데 빈 생성에 실패함
// 어노테이션이 부족한건지, 엔티티와 neo4j 매핑에서 필드가 안 맞는지.. 모르겠음
//
//@Data
//@RelationshipProperties
//public class IsFriend {
//
//    @Id
//    private Long id;
//
//    @TargetNode
//    private final Developer developer;
//
//
//}
