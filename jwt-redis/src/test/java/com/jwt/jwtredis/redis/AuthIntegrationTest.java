package com.jwt.jwtredis.redis;

import com.jwt.jwtredis.dto.LoginDto;
import com.jwt.jwtredis.jwt.CustomUserDetails;
import com.jwt.jwtredis.jwt.JwtTokenProvider;
import com.jwt.jwtredis.jwt.TokenDto;
import com.jwt.jwtredis.util.ObjectMapperUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.Duration;

import static com.jwt.jwtredis.ApiDocumentUtils.getRequestPreProcessor;
import static com.jwt.jwtredis.ApiDocumentUtils.getResponsePreProcessor;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.config.http.MatcherType.mvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AuthIntegrationTest extends BaseIntegrationTest {
    private final String BASE_URL = "/auth";
    private final String EMAIL = "email@gmail.com";
    @Autowired
    private MemberService memberService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AES128Config aes128Config;

    @BeforeEach
    void beforeEach() {
        MemberDto.SignUp signUpDto = StubData.MockMember.getSignUpDto();
        memberService.signUp(signUpDto);
    }

    @AfterEach
    void afterEach() {
        memberService.deleteMember(EMAIL);
    }

    @Test
    @DisplayName("로그인 성공")
    void loginSuccessTest() throws Exception {
        // given
        LoginDto loginSuccessDto = StubData.MockMember.getLoginSuccessDto();
        LoginResponse expectedResponseDto = StubData.MockMember.getLoginResponseDto();

        // when
        String uri = UriComponentsBuilder.newInstance().path(BASE_URL + "/login")
                .build().toUri().toString();
        String json = ObjectMapperUtils.asJsonString(loginSuccessDto);
        ResultActions actions = ResultActionsUtils.getRequest(mvc, uri, json);

        // then
        LoginResponse responseDto = ObjectMapperUtils.actionsSingleResponseToLoginDto(actions);
        assertThat(expectedResponseDto.getEmail()).isEqualTo(responseDto.getEmail());
        assertThat(expectedResponseDto.getNickname()).isEqualTo(responseDto.getNickname());
        assertThat(expectedResponseDto.getRole()).isEqualTo(responseDto.getRole());
        actions
                .andExpect(status().isOk())
                .andDo(document("login-success",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        getLoginSnippet(),
                        getLonginSuccessResponseSnippet()));
    }

    private Object getLonginSuccessResponseSnippet() {
    }

    private Object getLoginSnippet() {
    }

    @Test
    @DisplayName("로그인 실패")
    void loginFailTest() throws Exception {
        // given
        LoginDto loginFailDto = StubData.MockMember.getLoginFailDto();

        // when
        String uri = UriComponentsBuilder.newInstance().path(BASE_URL + "/login")
                .build().toUri().toString();
        String json = ObjectMapperUtils.asJsonString(loginFailDto);
        ResultActions actions = ResultActionsUtils.getRequest(mvc, uri, json);

        // then
        actions
                .andExpect(status().isUnauthorized())
                .andDo(document("login-fail",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        getLoginSnippet(),
                        getFieldErrorSnippets()));
    }

    @Test
    @DisplayName("Access token 재발급 성공")
    void accessTokenReissrueSuccessTest() throws Exception {
        // given
        CustomUserDetails userDetails = StubData.MockMember.getUserDetails();
        TokenDto tokenDto = jwtTokenProvider.generateTokenDto(userDetails);
        String refreshToken = tokenDto.getRefreshToken();
        redisService.setValues(EMAIL, refreshToken, Duration.ofMillis(10000));
        String encryptedRefreshToken = aes128Config.encryptAes(refreshToken);

        // when
        String uri = UriComponentsBuilder.newInstance().path(BASE_URL + "/reissue")
                .build().toUri().toString();
        ResultActions actions = ResultActionsUtils.patchRequest(mvc, uri, encryptedRefreshToken);

        // then
        actions
                .andExpect(status().isOk())
                .andDo(document("access-token-reissue-success"));
    }

    @Test
    @DisplayName("Refresh token 불일치로 Access token 재발급 실패")
    void accessTokenReissrueFailTest() throws Exception {
        // given
        CustomUserDetails userDetails = StubData.MockMember.getUserDetails();
        TokenDto tokenDto = jwtTokenProvider.generateTokenDto(userDetails);
        String refreshToken = tokenDto.getRefreshToken();
        String failRefreshToken = refreshToken + "fail";
        redisService.setValues("email@gmail.com", failRefreshToken, Duration.ofMillis(10000));
        String encryptedRefreshToken = aes128Config.encryptAes(refreshToken);

        // when
        String uri = UriComponentsBuilder.newInstance().path(BASE_URL + "/reissue")
                .build().toUri().toString();
        ResultActions actions = ResultActionsUtils.patchRequest(mvc, uri, encryptedRefreshToken);

        // then
        actions
                .andExpect(status().is(404))
                .andDo(document("reissue-fail-by-token-not-same",
                        getResponsePreProcessor(),
                        getFieldErrorSnippetsLong()));
    }

    @Test
    @DisplayName("Header에 Refresh token이 존재하지 않으면 Access token 재발급 실패")
    void accessTokenReissrueFailTest2() throws Exception {
        // when
        String uri = UriComponentsBuilder.newInstance().path(BASE_URL + "/reissue")
                .build().toUri().toString();
        ResultActions actions = ResultActionsUtils.patchRequest(mvc, uri);

        // then
        actions
                .andExpect(status().is(404))
                .andDo(document("reissue-fail-by-no-refresh-token-in-header",
                        getResponsePreProcessor(),
                        getFieldErrorSnippetsLong()));
    }

    @Test
    @DisplayName("로그아웃")
    void logoutTest() throws Exception {
        // given
        CustomUserDetails userDetails = StubData.MockMember.getUserDetails();
        TokenDto tokenDto = jwtTokenProvider.generateTokenDto(userDetails);
        String accessToken = tokenDto.getAccessToken();
        String refreshToken = tokenDto.getRefreshToken();
        String encryptedRefreshToken = aes128Config.encryptAes(refreshToken);
        redisService.setValues(EMAIL, refreshToken, Duration.ofMillis(10000));

        // when
        String uri = UriComponentsBuilder.newInstance().path(BASE_URL + "/logout")
                .build().toUri().toString();
        ResultActions actions = ResultActionsUtils.patchRequestWithToken(mvc, uri, accessToken, encryptedRefreshToken);

        // then
        String redisRefreshToken = redisService.getValues(EMAIL);
        String logout = redisService.getValues(accessToken);
        assertThat(redisRefreshToken).isEqualTo("false");
        assertThat(logout).isEqualTo("logout");
        actions
                .andExpect(status().isNoContent())
                .andDo(document("logout"));
    }

}
