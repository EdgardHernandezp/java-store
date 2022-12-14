package com.globant.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.globant.requests.StoreRequest;
import java.util.LinkedHashMap;

public class ParserUtil {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static StoreRequest parseRequest(String message) throws JsonProcessingException {
        return mapper.readValue(message, StoreRequest.class);
    }

    public static String generateResponse(int responseCode, String description, Object payload) {
        LinkedHashMap<String, Object> responseMap = new LinkedHashMap<>();
        responseMap.put("responseCode", responseCode);
        responseMap.put("description", description);
        responseMap.put("payload", payload);
        String response = PropertiesHolder.getProperty("json.server.error.msg");
        try {
            response= mapper.writeValueAsString(responseMap);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            response = String.format(response, e.getMessage());
        }
        return response;
    }
}
