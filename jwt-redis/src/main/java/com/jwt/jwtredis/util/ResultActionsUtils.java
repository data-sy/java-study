package com.jwt.jwtredis.util;

import org.springframework.http.MediaType;

import static org.springframework.http.RequestEntity.patch;

public class ResultActionsUtils {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String REFRESH_HEADER = "Refresh";
    private static final String BEARER_PREFIX = "Bearer ";

    public static ResultActions patchRequestWithContentAndToken(MockMvc mockMvc,
                                                                String url,
                                                                String json,
                                                                String accessToken,
                                                                String encryptedRefreshToken) throws Exception {

        return mockMvc.perform(patch(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .header(AUTHORIZATION_HEADER, BEARER_PREFIX + accessToken)
                        .header(REFRESH_HEADER, encryptedRefreshToken))
                .andDo(print());
    }
}