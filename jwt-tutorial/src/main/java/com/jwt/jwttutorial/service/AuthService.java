package com.jwt.jwttutorial.service;

import com.jwt.jwttutorial.redis.UnauthorizedException;
import com.jwt.jwttutorial.jwt.JwtToken;
import com.jwt.jwttutorial.jwt.TokenProvider;
import com.jwt.jwttutorial.redis.RedisUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final RedisUtil redisUtil;

    public AuthService(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder, RedisUtil redisUtil) {
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.redisUtil = redisUtil;
    }

    /**
     * authorize : (로그인 요청이 들어오면) 유저 정보로 인증 과정을 진행
     * Authentication Token 객체를 생성해 검증 과정을 진행. 그 검증된 인증 정보로 JWT 토큰을 생성
     */
    public JwtToken authorize(String email, String password) {
        // Authentication 객체 생성
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        // 검증된 인증 정보로 JWT 토큰 생성
        JwtToken token = tokenProvider.generateToken(authentication);
        return token;
    }

    /**
     * reissue : 토큰 재발급
     */
    public JwtToken reissue(String accessToken, String refreshToken) {
        if (!tokenProvider.validateToken(refreshToken)) {
            throw new UnauthorizedException("유효하지 않은 RefreshToken 입니다");
        }
        Authentication authentication = tokenProvider.getAuthentication(refreshToken);

//        // 이 과정 다른데에 있는 거 같은데 확인
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        String authorities = getAuthorities(authentication);

        // 권한을 만들어서 토큰한테 넘겨줘
        JwtToken token = tokenProvider.generateToken(authentication);

        return token;

    }

//    // 이거는 다른데에 있는 거 같아서 우선
//    // 권한 가져오기
//    public String getAuthorities(Authentication authentication) {
//        return authentication.getAuthorities().stream()
//                .map(GrantedAuthority::getAuthority)
//                .collect(Collectors.joining(","));
//    }

    public void logout(String accessToken, String refreshToken) {
        redisUtil.setBlackList(accessToken, "accessToken", 1800);
        redisUtil.setBlackList(refreshToken, "refreshToken", 60400);
    }

}
