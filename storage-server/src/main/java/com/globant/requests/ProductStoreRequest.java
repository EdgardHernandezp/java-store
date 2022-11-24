package com.globant.requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.globant.pojos.Product;

@JsonTypeName("addProduct")
public class ProductStoreRequest extends StoreRequest<Product> {
    @JsonCreator
    public ProductStoreRequest(@JsonProperty("action") String action, @JsonProperty("body") Product body) {
        super(action, body);
    }
}
