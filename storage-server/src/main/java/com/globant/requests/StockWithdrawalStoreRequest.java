package com.globant.requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.globant.pojos.StockWithdrawal;

@JsonTypeName("retrieve")
public class StockWithdrawalStoreRequest extends StoreRequest<StockWithdrawal> {
    @JsonCreator
    public StockWithdrawalStoreRequest(@JsonProperty("action") String action, @JsonProperty("body") StockWithdrawal body) {
        super(action, body);
    }
}
