package com.jwt.jwttutorial.controller;

import com.jwt.jwttutorial.dto.LoginDTO;
import com.jwt.jwttutorial.dto.TokenDTO;
import com.jwt.jwttutorial.jwt.JwtFilter;
import com.jwt.jwttutorial.jwt.JwtToken;
import com.jwt.jwttutorial.service.AuthService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<TokenDTO> login(@Valid @RequestBody LoginDTO loginDTO) {
        JwtToken token = authService.authorize(loginDTO.getUserEmail(), loginDTO.getUserPassword());

        // 토큰을 Response Header에도 넣어주자
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + token);

        return new ResponseEntity<>(TokenDTO.from(token), httpHeaders, HttpStatus.OK);
    }

    /**
     * AccessToken이 만료되었을 때 토큰(AccessToken , RefreshToken)재발급
     */
    @PostMapping("/reissue")
    public ResponseEntity<TokenDTO> reissue(@Valid @RequestBody TokenDTO request) {
        JwtToken token = authService.reissue(request.getAccessToken(), request.getRefreshToken());

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + token);

        return new ResponseEntity<>(TokenDTO.from(token), httpHeaders, HttpStatus.OK);

    }

    /**
     * 로그아웃 했을 때 토큰을 받아 BlackList에 저장
     */
    @DeleteMapping("/authenticate")
    public ResponseEntity<Void> logout(@RequestBody @Valid TokenDTO request) {
        authService.logout(request.getAccessToken(), request.getRefreshToken());
        return ResponseEntity.ok().build();
    }

}
