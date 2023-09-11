package com.jwt.jwttutorial.jwt;

import com.jwt.jwttutorial.exception.UnauthorizedException;
import com.jwt.jwttutorial.redis.RedisUtil;
import io.jsonwebtoken.JwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class JwtFilter extends OncePerRequestFilter {
    /**
     * JWT 인증을 위해 생성되는 토큰
     * 요청이 들어오면 헤더에서 토큰을 추출한 뒤, 유효성 검사를 함
     */
    private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class);
    public static final String AUTHORIZATION_HEADER = "Authorization";
    private final TokenProvider tokenProvider;
    private final RedisUtil redisUtil;

    public JwtFilter(TokenProvider tokenProvider, RedisUtil redisUtil) {
        this.tokenProvider = tokenProvider;
        this.redisUtil = redisUtil;
    }

    // jwt토큰의 인증 정보를 현재 실행중인 security concext에 저장
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 첫 줄 필요 없을수도?
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = resolveToken(httpServletRequest);
        String requestURI = httpServletRequest.getRequestURI();
        if (StringUtils.hasText(token) && tokenProvider.validateToken(token)) {
            Authentication authentication = tokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            logger.debug("Security Context에 '{}' 인증 정보를 저장했습니다, uri: {}", authentication.getName(), requestURI);
        } else {
            logger.debug("JWT 토큰이 없습니다, uri: {}", requestURI);
        }
        filterChain.doFilter(request, response);
    }

    // 헤더에서 토큰정보 추출
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
            return bearerToken.substring(7);
        }
        return null;
    }


//    // jwt토큰의 인증 정보를 현재 실행중인 security concext에 저장
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        // 첫 줄 필요 없을수도?
//        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//        String token = resolveToken(httpServletRequest);
//        String requestURI = httpServletRequest.getRequestURI();
//        if (StringUtils.hasText(token) && tokenProvider.validateToken(token)) {
//            if (requestURI.equals("/api/reissue")){
//                // 리프레시 토큰이 아니라면 에러처리
//                String tokenType = tokenProvider.getTokenType(token);
//                if (!"refresh".equals(tokenType)) {
//                    throw new JwtException("토큰을 확인하세요.");
//                }
//            }
//            Authentication authentication = tokenProvider.getAuthentication(token);
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//            logger.debug("Security Context에 '{}' 인증 정보를 저장했습니다, uri: {}", authentication.getName(), requestURI);
//        } else {
//            logger.debug("JWT 토큰이 없습니다, uri: {}", requestURI);
//        }
//        filterChain.doFilter(request, response);
//    }
//
//    // 헤더에서 토큰정보 추출
//    private String resolveToken(HttpServletRequest request) {
//        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
//        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
//            return bearerToken.substring(7);
//        }
//        return null;
//    }
//
//    // extends GenericFilterBean의 doFilter 였을 때
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException, java.io.IOException {
//        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//        String token = resolveToken(httpServletRequest);
//        String requestURI = httpServletRequest.getRequestURI();
//        logger.debug("doFilter 들어옴");
//        // 토큰 유효성 검사
//        if (StringUtils.hasText(token) && tokenProvider.validateToken(token)) {
//            String tokenType = tokenProvider.getTokenType(token);
//            if ("refresh".equals(tokenType) && !requestURI.equals("/api/reissue")){
//                throw new JwtException("토큰을 확인하세요.");
//            } else if ("access".equals(tokenType)){
//                Authentication authentication = tokenProvider.getAuthentication(token);
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//                logger.debug("Security Context에 '{}' 인증 정보를 저장했습니다, uri: {}", authentication.getName(), requestURI);
//            }
//        } else {
//            logger.debug("유효한 JWT 토큰이 없습니다, uri: {}", requestURI);
//        }
//        chain.doFilter(request, response);
//    }
//
//    // 헤더에서 토큰정보 추출
//    private String resolveToken(HttpServletRequest request) {
//        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
//        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
//            return bearerToken.substring(7);
//        }
//        return null;
//    }

}


