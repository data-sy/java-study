package com.jwt.jwtredis.service;

public interface AuthService {
    String reissueAccessToken(String encryptedRefreshToken);

    void logout(String encryptedRefreshToken, String accessToken);

}
