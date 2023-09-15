package com.jwt.jwttutorial.config;

import com.jwt.jwttutorial.handler.OAuth2AuthenticationFailureHandler;
import com.jwt.jwttutorial.handler.OAuth2AuthenticationSuccessHandler;
import com.jwt.jwttutorial.jwt.JwtAccessDeniedHandler;
import com.jwt.jwttutorial.jwt.JwtAuthenticationEntryPoint;
import com.jwt.jwttutorial.jwt.JwtFilter;
import com.jwt.jwttutorial.jwt.TokenProvider;
import com.jwt.jwttutorial.repository.CookieAuthorizationRequestRepository;
import com.jwt.jwttutorial.service.CustomOAuth2UserService;
import com.jwt.jwttutorial.util.RedisUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@EnableWebSecurity
@EnableMethodSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class SecurityConfig {

    private final TokenProvider tokenProvider;
    private final RedisUtil redisUtil;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;
    private final OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;
    private final CustomOAuth2UserService customOAuth2UserService;
    private final CookieAuthorizationRequestRepository cookieAuthorizationRequestRepository;

    // 커스텀오어스2유저서비스
    // 쿠키오쏘리제이션리퀘스트레파지토리
    // 석세스핸들러, 페일 핸들러
    // 원래 있던 건 jwt토큰프로바이더밖에 없네...

    public SecurityConfig(TokenProvider tokenProvider, RedisUtil redisUtil, JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint, JwtAccessDeniedHandler jwtAccessDeniedHandler, OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler, OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler, CustomOAuth2UserService customOAuth2UserService, CookieAuthorizationRequestRepository cookieAuthorizationRequestRepository) {
        this.tokenProvider = tokenProvider;
        this.redisUtil = redisUtil;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
        this.oAuth2AuthenticationSuccessHandler = oAuth2AuthenticationSuccessHandler;
        this.oAuth2AuthenticationFailureHandler = oAuth2AuthenticationFailureHandler;

        this.customOAuth2UserService = customOAuth2UserService;
        this.cookieAuthorizationRequestRepository = cookieAuthorizationRequestRepository;
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    // 오어쓰 하면서 한 번 싹 정리
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //사용하지 않는 것들 비활성화
        http
                .csrf(csrf -> csrf.disable())       // rest api, token 사용하므로
                .formLogin().disable()
                .httpBasic().disable()
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                // cors 설정
                .cors().configurationSource(corsConfigurationSource())
                .and()
        // 요청들 접근 제한
                .authorizeHttpRequests()
                .antMatchers("/", "/oauth2/**", "/api/hello", "/login.html").permitAll()
            // /api/hello, /api/signup, /api/authenticate, /api/reissue, /api/user/** , api/v1/**
//                .antMatchers("/api/v1/**").hasRole("ROLE_USER이런거?")
                .anyRequest().authenticated();
        // OAuth2 로그인
        http
                .oauth2Login()
                .successHandler(oAuth2AuthenticationSuccessHandler)
                .failureHandler(oAuth2AuthenticationFailureHandler)
//                .authorizationEndpoint().baseUri("/oauth2/authorize")
//                .authorizationRequestRepository(cookieAuthorizationRequestRepository) //인증 요청을 cookie에 저장
//                .and()
                .userInfoEndpoint().userService(customOAuth2UserService);
        // 로그아웃
        http
                .logout()
                .logoutSuccessUrl("/")
                .clearAuthentication(true)
                .deleteCookies("JSESSIONID");
        // 필터 적용
        http.addFilterBefore(new JwtFilter(tokenProvider, redisUtil), UsernamePasswordAuthenticationFilter.class);
        // 잘못된 접근에 대한 예외처리
        http
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .accessDeniedHandler(jwtAccessDeniedHandler)
                        .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                );
        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.addAllowedOrigin("http://localhost:3000"); // 뷰
        configuration.addAllowedOrigin("http://localhost:5000"); // 플라스크
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/api/**", configuration);
        return source;
    }



//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                //사용하지 않는 것들 비활성화
//                .formLogin().disable()
//                .httpBasic().disable()
//                .csrf(csrf -> csrf.disable())       // rest api, token 사용하므로
//                .sessionManagement(sessionManagement ->
//                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                )
//                // 잘못된 접근에 대한 예외처리
//                .exceptionHandling(exceptionHandling -> exceptionHandling
//                    .accessDeniedHandler(jwtAccessDeniedHandler)
//                    .authenticationEntryPoint(jwtAuthenticationEntryPoint)
////                    .authenticationEntryPoint((request, response, authException) -> {
////                        // "/api/reissue" 특정 엔드포인트에 대한 예외 처리
////                        if (request.getRequestURI().equals("/api/reissue")) {
////                            response.setStatus(HttpServletResponse.SC_OK);
////                            // 로직 추가
////                        } else {
////                            // 그 외의 엔드포인트에 대한 예외 처리
////                            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
////                        }
////                            })
//                )
//                // 요청들 접근 제한
//                .authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
//                        // 해당 요청 접근 허용
//                    .requestMatchers(new AntPathRequestMatcher("/api/hello")).permitAll()
//                    .requestMatchers(new AntPathRequestMatcher("/api/signup")).permitAll()
//                    .requestMatchers(new AntPathRequestMatcher("/api/authenticate")).permitAll()
//                    .requestMatchers(new AntPathRequestMatcher("/api/reissue")).permitAll()
//                    .requestMatchers(new AntPathRequestMatcher("/api/user/**")).permitAll()
//                    .requestMatchers(new AntPathRequestMatcher("/api/v1/**")).permitAll()
//                    .requestMatchers(new AntPathRequestMatcher("/oauth2/**")).permitAll()
//
//                        // 나머지 요청은 모두 인증
//                    .anyRequest().authenticated()
//                )
//                // OAuth2 로그인
//                .oauth2Login()
//                .successHandler(oAuth2AuthenticationSuccessHandler)
//                .failureHandler(oAuth2AuthenticationFailureHandler)
//                    // 로그인 성공 시 후속 조치 진행
//                .userInfoEndpoint().userService(customOAuth2UserService);
//        // 우리가 만들어둔 필터들 시큐리티 로직에 적용필터 적용
//        http.addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class);
//                // 로그아웃 필더 애드필터애프터로 추가
//        http.addFilterBefore(new JwtFilter(tokenProvider, redisUtil), UsernamePasswordAuthenticationFilter.class);
////                // 로그아웃 성공시 "/" 주소로 이동
////                .logout()
////                    .logoutSuccessUrl("/")
////                .and()
////                // OAuth2 로그인
////                    // OAuth2 로그인 기능에 대한 여러 설정의 진입점
////                .oauth2Login()
////                    // OAuth2 로그인 성공 이후 사용자 정보를 가져올 때의 설정들을 담당
////                    .userInfoEndpoint()
////                // 소셜 로그인 성공 시 후속 조치를 진행
////                        .userService(customOAuth2UserService)
//        return http.build();
//    }

}


