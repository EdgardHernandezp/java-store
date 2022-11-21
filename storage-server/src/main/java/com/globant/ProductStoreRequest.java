package com.globant;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductStoreRequest extends StoreRequest<Product> {
    @JsonCreator
    public ProductStoreRequest(@JsonProperty("actionCode") int actionCode, @JsonProperty("body") Product body) {
        super(actionCode, body);
    }
}
