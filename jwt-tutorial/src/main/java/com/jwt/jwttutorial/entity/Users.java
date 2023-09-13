package com.jwt.jwttutorial.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    // OAuth로 추가된 컬럼
    @JsonIgnore
    private String oauth2Id;

    @JsonIgnore
    @Enumerated(EnumType.STRING)
    private AuthProvider authProvider; // GOOGLE, NAVER, KAKAO

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserAuthority> userAuthoritySet = new HashSet<>();

//    public Object update(OAuth2UserInfo oAuth2UserInfo) {
    // 오우스서비스에서 사용될거야. 들어온 유저에게 인포 담아주는
//    }
}
