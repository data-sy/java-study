package com.jwt.jwttutorial.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

public class UserAuthority {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userAuthoId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "authority_name")
    private Authority authority;

}
