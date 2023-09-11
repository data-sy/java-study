package com.jwt.jwtredis.redis;

import com.jwt.jwtredis.jwt.CustomUserDetails;
import com.jwt.jwtredis.jwt.JwtTokenProvider;
import com.jwt.jwtredis.jwt.TokenDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.util.UriComponentsBuilder;

import static org.springframework.security.config.http.MatcherType.mvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MemberIntegrationTest extends BaseIntegrationTest {

    private final String BASE_URL = "/members";
    private final String EMAIL = "email@gmail.com";
    @Autowired
    private MemberService memberService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AES128Config aes128Config;

    @BeforeEach
    void befroeEach() {
        MemberDto.SignUp signUpDto = StubData.MockMember.getSignUpDto();
        memberService.signUp(signUpDto);
    }

    @AfterEach
    void afterEach() {
        memberService.deleteMember(EMAIL);
    }

    @Test
    @DisplayName("회원 조회")
    void getMemberTest() throws Exception {
        // given
        CustomUserDetails userDetails = StubData.MockMember.getUserDetails();
        TokenDto tokenDto = jwtTokenProvider.generateTokenDto(userDetails);
        String accessToken = tokenDto.getAccessToken();
        String refreshToken = tokenDto.getRefreshToken();
        String encryptedRefreshToken = aes128Config.encryptAes(refreshToken);

        // when
        String uri = UriComponentsBuilder.newInstance().path(BASE_URL)
                .build().toUri().toString();
        ResultActions actions = ResultActionsUtils.getRequestWithToken(mvc, uri, accessToken, encryptedRefreshToken);

        // then
        actions
                .andExpect(status().isOk())
                .andDo(document("get-member",
                        getResponsePreProcessor(),
                        MemberResponseSnippet.getMemberResponseSnippet()));
    }
}
