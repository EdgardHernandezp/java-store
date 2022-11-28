package com.globant.requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.globant.Actions;
import com.globant.pojos.ProductType;

@JsonTypeName("addProductType")
public class ProductTypeStoreRequest extends StoreRequest<ProductType> {
    @JsonCreator
    public ProductTypeStoreRequest(@JsonProperty("action") Actions action, @JsonProperty("body") ProductType body) {
        super(action, body);
    }
}
