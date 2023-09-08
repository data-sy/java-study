package com.jwt.jwtredis.jwt;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TokenDto {
    private final String grantType;
    private final String authorizationType;
    private final String accessToken;
    private final String refreshToken;
    private final Long accessTokenExpiresIn;
}