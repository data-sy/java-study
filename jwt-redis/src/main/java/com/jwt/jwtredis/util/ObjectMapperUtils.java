package com.jwt.jwtredis.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.catalina.connector.Response;

public class ObjectMapperUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String asJsonString(final Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Response actionsSingleResponseToMemberDto(ResultActions actions) throws Exception {
        String response = actions.andReturn().getResponse().getContentAsString();
        return objectMapper.registerModule(new JavaTimeModule()).readValue(response, Response.class);
    }

}
