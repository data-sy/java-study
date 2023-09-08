package com.jwt.jwtredis.dto;

import lombok.Getter;

@Getter
public class LoginDto {
    private String email;
    private String password;
}
