package com.globant.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.globant.ProductStoreRequest;
import com.globant.StoreRequest;
import java.util.LinkedHashMap;

public class ParserUtil {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static StoreRequest parseRequest(String message) throws JsonProcessingException {
        return mapper.readValue(message, ProductStoreRequest.class);
    }

    public static String generateResponse(int responseCode, String description) throws JsonProcessingException {
        LinkedHashMap<String, Object> responseMap = new LinkedHashMap<>();
        responseMap.put("responseCode", responseCode);
        responseMap.put("description", description);
        return mapper.writeValueAsString(responseMap);
    }
}
