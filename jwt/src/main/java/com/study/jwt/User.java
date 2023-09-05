package com.study.jwt;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "user")
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String userEmail;
    private String userPassword;
    private String userName;
    private String userPhone;

    private String provider;

    @Builder
    public User(Long userId, String userEmail, String userPassword, String userName, String userPhone, String provider) {
        this.userId = userId;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userName = userName;
        this.userPhone = userPhone;
        this.provider = provider;
    }

//    public Users update(String name, String provider) {
//        this.name = name;
//        this.provider = provider;
//        return this;
//    }

//    public String getRoleKey() {
//        return this.role.getKey();
//    }
}
