package com.globant.requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.globant.Actions;

public class FindAvailableProductsStoreRequest extends StoreRequest<Void> {
    @JsonCreator
    public FindAvailableProductsStoreRequest(@JsonProperty("action") Actions action, @JsonProperty("body") Void body) {
        super(action, body);
    }
}
