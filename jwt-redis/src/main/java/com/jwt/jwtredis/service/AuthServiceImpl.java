package com.jwt.jwtredis.service;

import com.jwt.jwtredis.config.AES128Config;
import com.jwt.jwtredis.jwt.CustomUserDetails;
import com.jwt.jwtredis.jwt.JwtTokenProvider;
import com.jwt.jwtredis.jwt.TokenDto;
import com.jwt.jwtredis.redis.RedisService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final JwtTokenProvider jwtTokenProvider;
    private final AES128Config aes128Config;
    private final RedisService redisService;
    private final MemberRepository memberRepository;

    @Override
    public String reissueAccessToken(String encryptedRefreshToken) {
        this.verifiedRefreshToken(encryptedRefreshToken);
        String refreshToken = aes128Config.decryptAes(encryptedRefreshToken);
        Claims claims = jwtTokenProvider.parseClaims(refreshToken);
        String email = claims.getSubject();
        String redisRefreshToken = redisService.getValues(email);

        if (redisService.checkExistsValue(redisRefreshToken) && refreshToken.equals(redisRefreshToken)) {
            Member findMember = this.findMemberByEmail(email);
            CustomUserDetails userDetails = CustomUserDetails.of(findMember);
            TokenDto tokenDto = jwtTokenProvider.generateTokenDto(userDetails);
            String newAccessToken = tokenDto.getAccessToken();
            long refreshTokenExpirationMillis = jwtTokenProvider.getRefreshTokenExpirationMillis();

            return newAccessToken;
        } else throw new BusinessLogicException(ExceptionCode.TOKEN_IS_NOT_SAME);
    }

    @Override
    public void logout(String encryptedRefreshToken, String accessToken) {
        this.verifiedRefreshToken(encryptedRefreshToken);
        String refreshToken = aes128Config.decryptAes(encryptedRefreshToken);
        Claims claims = jwtTokenProvider.parseClaims(refreshToken);
        String email = claims.getSubject();
        String redisRefreshToken = redisService.getValues(email);
        if (redisService.checkExistsValue(redisRefreshToken)) {
            redisService.deleteValues(email);

            // 로그아웃 시 Access Token Redis 저장 ( key = Access Token / value = "logout" )
            long accessTokenExpirationMillis = jwtTokenProvider.getAccessTokenExpirationMillis();
            redisService.setValues(accessToken, "logout", Duration.ofMillis(accessTokenExpirationMillis));
        }
    }

    private void verifiedRefreshToken(String encryptedRefreshToken) {
        if (encryptedRefreshToken == null) {
            throw new BusinessLogicException(ExceptionCode.HEADER_REFRESH_TOKEN_NOT_EXISTS);
        }
    }

    private Member findMemberByEmail(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
    }
}