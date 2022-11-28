package com.globant.requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.globant.Actions;
import com.globant.pojos.StockWithdrawal;

@JsonTypeName("RETRIEVE_PRODUCTS")
public class StockWithdrawalStoreRequest extends StoreRequest<StockWithdrawal> {
    @JsonCreator
    public StockWithdrawalStoreRequest(@JsonProperty("action") Actions action, @JsonProperty("body") StockWithdrawal body) {
        super(action, body);
    }
}
