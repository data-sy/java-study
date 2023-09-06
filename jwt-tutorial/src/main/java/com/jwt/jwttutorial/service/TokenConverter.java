package com.jwt.jwttutorial.service;

import com.jwt.jwttutorial.dto.TokenDTO;
import com.jwt.jwttutorial.jwt.JwtToken;

public class TokenConverter {

    public static TokenDTO convertToDTO(JwtToken jwtToken) {
        TokenDTO tokenDTO = new TokenDTO();
        tokenDTO.setGrantType(jwtToken.getGrantType());
        tokenDTO.setAccessToken(jwtToken.getAccessToken());
        tokenDTO.setRefreshToken(jwtToken.getRefreshToken());
        return tokenDTO;
    }
}
