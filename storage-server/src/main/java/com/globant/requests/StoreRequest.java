package com.globant.requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.globant.Actions;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "action", visible = true)
@JsonSubTypes({ @JsonSubTypes.Type(value = ProductStoreRequest.class, name = "ADD_PRODUCT"),
        @JsonSubTypes.Type(value = ProductTypeStoreRequest.class, name = "ADD_PRODUCT_TYPE"),
        @JsonSubTypes.Type(value = DeleteStoreRequest.class, name = "DELETE_RESOURCE"),
        @JsonSubTypes.Type(value = StockOperationStoreRequest.class, name = "RETRIEVE_PRODUCTS"),
        @JsonSubTypes.Type(value = ProductExistenceRequest.class, name = "CHECK_PRODUCT_EXISTENCE"),
        @JsonSubTypes.Type(value = ProductSearchStoreRequest.class, name = "SEARCH_PRODUCT"),
        @JsonSubTypes.Type(value = FindAvailableProductsStoreRequest.class, name = "FIND_AVAILABLE_PRODUCTS")})
public class StoreRequest<T> {
    private final Actions action;
    private final T body;

    @JsonCreator
    public StoreRequest(@JsonProperty("action") Actions action, @JsonProperty("body") T body) {
        this.action = action;
        this.body = body;
    }

    public Actions getAction() {
        return action;
    }

    public T getBody() {
        return body;
    }
}
