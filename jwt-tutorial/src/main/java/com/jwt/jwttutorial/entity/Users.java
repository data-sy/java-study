package com.jwt.jwttutorial.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jwt.jwttutorial.dto.OAuth2UserInfo;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Users {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String userEmail;

    @JsonIgnore
    private String userPassword;

    private String userName;

    private String userPhone;

    // 활셩화 여부
    @JsonIgnore
    private boolean activated;

    // oauth 하면서 계속 추가해 나가자
    private String provider;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserAuthority> userAuthoritySet = new HashSet<>();

//    public Object update(OAuth2UserInfo oAuth2UserInfo) {
//    }
}
