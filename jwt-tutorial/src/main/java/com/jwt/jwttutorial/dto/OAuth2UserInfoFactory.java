package com.jwt.jwttutorial.dto;

import java.security.AuthProvider;
import java.util.Map;

import static org.springframework.security.config.oauth2.client.CommonOAuth2Provider.GOOGLE;

public class OAuth2UserInfoFactory {

    public static OAuth2UserInfo getOAuth2UserInfo(AuthProvider authProvider, Map<String, Object> attributes) {
        // 우선 구글 하나라서 switch말고 if
        if (authProvider.equals(GOOGLE)) {
            return new GoogleOAuth2User(attributes);
        }
        throw new IllegalArgumentException("Invalid Provider Type.");

//        switch (authProvider) {
//            case GOOGLE: return new GoogleOAuth2User(attributes);
//            case NAVER: return new NaverOAuth2User(attributes);
//            case KAKAO: return new KakaoOAuth2User(attributes);
//
//            default: throw new IllegalArgumentException("Invalid Provider Type.");
//        }

    }
}
