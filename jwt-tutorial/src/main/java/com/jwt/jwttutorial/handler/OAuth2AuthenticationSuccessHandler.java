package com.jwt.jwttutorial.handler;

import com.jwt.jwttutorial.jwt.JwtToken;
import com.jwt.jwttutorial.jwt.TokenProvider;
import com.jwt.jwttutorial.repository.CookieAuthorizationRequestRepository;
import com.jwt.jwttutorial.util.CookieUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.Optional;

import static com.jwt.jwttutorial.repository.CookieAuthorizationRequestRepository.REDIRECT_URI_PARAM_COOKIE_NAME;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    // value 임포트 맞는지 확인
//    @Value("${oauth2.authorizedRedirectUri}")
//    private String redirectUri;
    // 우선 구글로 테스트
//    private String redirectUri = "http://localhost:8080/afterlogin.html";
//    private String redirectUri = "http://localhost:8080/login/oauth2/code/google";
    private String redirectUri = "http://localhost:8080/login/oauth2/code/naver";
//    private String redirectUri = "http://localhost:8080/login/oauth2/code/kakao";
    // 여기다 뭘 적든 잘 가... 뭔가 시큐어 컨피그에서 우선적으로 선점하는 게 있는 듯??
    private final TokenProvider tokenProvider;
    private final CookieAuthorizationRequestRepository cookieAuthorizationRequestRepository;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        log.info("OAuth2AuthenticationSuccessHandler.onAuthenticationSuccess() 실행 - 성공핸들러 - onAuthenticationSuccess 진입");
        String targetUrl = determineTargetUrl(request, response, authentication);

        if (response.isCommitted()) {
            log.debug("Response has already been committed.");
            return;
        }
        clearAuthenticationAttributes(request, response);
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        log.info("OAuth2AuthenticationSuccessHandler.determineTargetUrl() 실행 - 성공핸들러 - determineTargetUrl 진입");
        Optional<String> redirectUri = CookieUtils.getCookie(request, REDIRECT_URI_PARAM_COOKIE_NAME)
                .map(Cookie::getValue);

        if (redirectUri.isPresent() && !isAuthorizedRedirectUri(redirectUri.get())) {
            throw new RuntimeException("redirect URIs are not matched.");
        }
        String targetUrl = redirectUri.orElse(getDefaultTargetUrl());

        //JWT 생성
        JwtToken token = tokenProvider.generateToken(authentication);
        System.out.println("핸들러에서 토큰 잘 생성 됐는지 : " + token.toString());
        // 원래 있던 코드
//        UserResponseDto.TokenInfo tokenInfo = tokenProvider.generateToken(authentication);
        // 아.. 리턴을 보니까 액세스 토큰만 빼서 쿼리파라미터로 주네
        // 이 부분을 리팩토링 하면 돼! 일반 로그인 컨트롤러에서처럼 리스펀스로 토큰 전체 담아주고, 헤더에도 토큰 담아주는!
        return UriComponentsBuilder.fromUriString(targetUrl)
                .queryParam("token", token.getAccessToken())
                .build().toUriString();
    }

    protected void clearAuthenticationAttributes(HttpServletRequest request, HttpServletResponse response) {
        super.clearAuthenticationAttributes(request);
        cookieAuthorizationRequestRepository.removeAuthorizationRequestCookies(request, response);
    }

    private boolean isAuthorizedRedirectUri(String uri) {
        URI clientRedirectUri = URI.create(uri);
        URI authorizedUri = URI.create(redirectUri);

        if (authorizedUri.getHost().equalsIgnoreCase(clientRedirectUri.getHost())
                && authorizedUri.getPort() == clientRedirectUri.getPort()) {
            return true;
        }
        return false;
    }
}