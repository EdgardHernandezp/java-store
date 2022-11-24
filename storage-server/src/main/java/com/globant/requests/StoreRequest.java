package com.globant.requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "action", visible = true)
@JsonSubTypes({ @JsonSubTypes.Type(value = ProductStoreRequest.class, name = "addProduct"),
        @JsonSubTypes.Type(value = ProductTypeStoreRequest.class, name = "addProductType"),
        @JsonSubTypes.Type(value = DeleteStoreRequest.class, name = "deleteResource") })
public class StoreRequest<T> {
    private final String action;
    private final T body;

    @JsonCreator
    public StoreRequest(@JsonProperty("action") String action, @JsonProperty("body") T body) {
        this.action = action;
        this.body = body;
    }

    public String getAction() {
        return action;
    }

    public T getBody() {
        return body;
    }
}
