package com.globant.requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.globant.Actions;

public class ProductExistenceRequest<T> extends StoreRequest<Integer> {

    @JsonCreator
    public ProductExistenceRequest(@JsonProperty("action") Actions action, @JsonProperty("body") Integer body) {
        super(action, body);
    }
}
