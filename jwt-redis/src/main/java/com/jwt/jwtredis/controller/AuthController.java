package com.jwt.jwtredis.controller;

import com.jwt.jwtredis.jwt.JwtTokenProvider;
import com.jwt.jwtredis.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final JwtTokenProvider jwtTokenProvider;

    @PatchMapping("/reissue")
    public ResponseEntity reissue(HttpServletRequest request,
                                  HttpServletResponse response) {
        String encryptedRefreshToken = jwtTokenProvider.resolveRefreshToken(request);
        String newAccessToken = authService.reissueAccessToken(encryptedRefreshToken);
        jwtTokenProvider.accessTokenSetHeader(newAccessToken, response);

        return new ResponseEntity<>(new SingleResponseDto<>(
                "The access token was successfully reissued"), HttpStatus.OK);
    }

    @PatchMapping("/logout")
    public ResponseEntity logout(HttpServletRequest request) {
        String encryptedRefreshToken = jwtTokenProvider.resolveRefreshToken(request);
        String accessToken = jwtTokenProvider.resolveAccessToken(request);
        authService.logout(encryptedRefreshToken, accessToken);

        return new ResponseEntity<>(new SingleResponseDto<>("Logged out successfully"), HttpStatus.NO_CONTENT);
    }
}