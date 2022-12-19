package com.globant.requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.globant.Actions;
import com.globant.pojos.StockOperation;

@JsonTypeName("RETRIEVE_PRODUCTS")
public class StockOperationStoreRequest extends StoreRequest<StockOperation[]> {
    @JsonCreator
    public StockOperationStoreRequest(@JsonProperty("action") Actions action, @JsonProperty("body") StockOperation[] body) {
        super(action, body);
    }
}
