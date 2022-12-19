package com.globant.requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.globant.Actions;
import com.globant.pojos.Product;

@JsonTypeName("addProduct") //TODO: is this necessary?
public class ProductStoreRequest extends StoreRequest<Product> {
    @JsonCreator
    public ProductStoreRequest(@JsonProperty("action") Actions action, @JsonProperty("body") Product body) {
        super(action, body);
    }
}
