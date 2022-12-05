package com.globant.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.globant.communication.Response;
import com.globant.communication.requests.Request;

public final class ParseUtil {
    private static final ObjectMapper mapper = new ObjectMapper();

    private ParseUtil() {
    }

    public static <T> String parseRequest(Request<T> request) {
        try {
            return mapper.writeValueAsString(request);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> Response<T> parseResponse(String response, TypeReference<Response<T>> type) {
        try {
            return mapper.readValue(response, type);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
