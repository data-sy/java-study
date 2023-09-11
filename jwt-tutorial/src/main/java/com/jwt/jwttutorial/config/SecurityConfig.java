package com.jwt.jwttutorial.config;

import com.jwt.jwttutorial.jwt.JwtAccessDeniedHandler;
import com.jwt.jwttutorial.jwt.JwtAuthenticationEntryPoint;
import com.jwt.jwttutorial.jwt.JwtFilter;
import com.jwt.jwttutorial.jwt.TokenProvider;
import com.jwt.jwttutorial.redis.RedisUtil;
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
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletResponse;

@EnableWebSecurity
@EnableMethodSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class SecurityConfig {

    private final TokenProvider tokenProvider;
    private final RedisUtil redisUtil;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    public SecurityConfig(TokenProvider tokenProvider, RedisUtil redisUtil, JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint, JwtAccessDeniedHandler jwtAccessDeniedHandler) {
        this.tokenProvider = tokenProvider;
        this.redisUtil = redisUtil;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())       // rest api, token 사용하므로

                // 우리가 만들어둔 필터들 시큐리티 로직에 적용필터 적용
                .addFilterBefore(new JwtFilter(tokenProvider, redisUtil), UsernamePasswordAuthenticationFilter.class)
//                .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
//                // 필터 2개 안 되면 jwt는 따로 만들어서 apply
//                .apply(new JwtSecurityConfig(tokenProvider));

                // 잘못된 접근에 대한 예외처리
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .accessDeniedHandler(jwtAccessDeniedHandler)
                        .authenticationEntryPoint(jwtAuthenticationEntryPoint)
//                        .authenticationEntryPoint((request, response, authException) -> {
//                            // "/api/reissue" 특정 엔드포인트에 대한 예외 처리
//                            if (request.getRequestURI().equals("/api/reissue")) {
//                                response.setStatus(HttpServletResponse.SC_OK);
//                                // 로직 추가
//                            } else {
//                                // 그 외의 엔드포인트에 대한 예외 처리
//                                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
//                            }
//                                })
                )

                // 요청들 접근 제한
                .authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
                        // 해당 요청 접근 허용
                        .requestMatchers(new AntPathRequestMatcher("/api/hello")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/api/signup")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/api/authenticate")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/api/reissue")).permitAll()
//                        .requestMatchers(new AntPathRequestMatcher("/api/user/**")).permitAll()
                        // 나머지 요청은 모두 인증
                        .anyRequest().authenticated()
                )

                // 사용하지 않는 것들 비활성화
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .formLogin().disable()
                .httpBasic().disable();

        return http.build();
    }

}


