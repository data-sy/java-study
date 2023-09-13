package com.jwt.jwttutorial.jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@ToString
public class JwtToken {

    private String grantType;
    private String accessToken;
    private String refreshToken;

}
