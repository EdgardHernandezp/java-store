package com.globant.communication;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

public class Response<T> implements Serializable {
    private int responseCode;
    private String description;
    private T payload;

    @JsonCreator
    public Response(@JsonProperty("responseCode") int responseCode, @JsonProperty("description") String description,
            @JsonProperty("payload") T payload) {
        this.responseCode = responseCode;
        this.description = description;
        this.payload = payload;

    }

    public T getPayload() {
        return payload;
    }

    public String getDescription() {
        return description;
    }

    public int getResponseCode() {
        return responseCode;
    }
}
