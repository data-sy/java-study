//package com.mmt.neo4j.study01;
//
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public class SectionJdbcRepository {
//
//    private final JdbcTemplate jdbcTemplate;
//
//
//    public SectionJdbcRepository(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    public List<String> findAllSection(){
//        String sql = "SELECT section_name FROM sections";
//        return jdbcTemplate.queryForList(sql, String.class);
//    }
//
//}
