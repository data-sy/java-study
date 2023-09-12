package com.jwt.jwttutorial.service;

import com.jwt.jwttutorial.exception.UnauthorizedException;
import com.jwt.jwttutorial.jwt.JwtToken;
import com.jwt.jwttutorial.jwt.TokenProvider;
import com.jwt.jwttutorial.util.RedisUtil;
import com.jwt.jwttutorial.util.SecurityUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public JwtToken reissue(String accessToken, String refreshToken) {
        if (!tokenProvider.validateToken(refreshToken)) {
            throw new UnauthorizedException("유효하지 않은 RefreshToken 입니다");
        }
        // accessToken에서 email 가져오기
        String userEmail = tokenProvider.getEmail(accessToken);
        System.out.println("재발급 검증 시 이메일 추출 : " + userEmail);

        // refreshToken 검증 후 새 토큰 발급
        if (redisUtil.hasKey(userEmail) && refreshToken.equals(redisUtil.get(userEmail))){
            // 기존 accessToken 블랙리스트에 넣고
            redisUtil.setBlackList(accessToken, "logout", tokenProvider.getExpiration(refreshToken));
            // 새 토큰 발급
            Authentication authentication = tokenProvider.getAuthentication(accessToken);
            JwtToken newToken = tokenProvider.generateToken(authentication);
            String newRefreshToken = newToken.getRefreshToken();
            // 새 토큰 RedisDB에 저장
            redisUtil.set(authentication.getName(), newRefreshToken, tokenProvider.getExpiration(newRefreshToken));

            return newToken;
        } else throw new UnauthorizedException("유효하지 않은 RefreshToken 입니다");

    }

    @Transactional
    public void logout(String accessToken, String refreshToken) {
//        // accessToken 유효성 검사
//        if (!tokenProvider.validateToken(accessToken)) {
//            throw new UnauthorizedException("유효하지 않은 AccessToken 입니다");
//        }
        // accessToken에서 email 가져오기
        String userEmail = SecurityUtil.getCurrentUserEmail().orElse("");
//        String userEmail = tokenProvider.getEmail(accessToken);
        System.out.println("로그아웃 시 이메일 추출 : " + userEmail);

        boolean isDelete = redisUtil.delete(userEmail);
        redisUtil.setBlackList(accessToken, "logout", tokenProvider.getExpiration(accessToken));

        System.out.println("로그아웃 완료 : " + isDelete);

    }
}
