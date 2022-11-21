package com.globant;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class StoreRequest<T> {
    private final int actionCode;
    private final T body;

    @JsonCreator
    public StoreRequest(@JsonProperty("actionCode") Integer actionCode, @JsonProperty("body") T body) {
        this.actionCode = actionCode;
        this.body = body;
    }

    public int getActionCode() {
        return actionCode;
    }

    public T getBody() {
        return body;
    }
}
