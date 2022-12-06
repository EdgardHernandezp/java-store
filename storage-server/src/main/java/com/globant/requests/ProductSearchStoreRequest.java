package com.globant.requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.globant.Actions;

public class ProductSearchStoreRequest extends StoreRequest<String> {

    @JsonCreator
    public ProductSearchStoreRequest(@JsonProperty("action") Actions action, @JsonProperty("body") String body) {
        super(action, body);
    }
}
