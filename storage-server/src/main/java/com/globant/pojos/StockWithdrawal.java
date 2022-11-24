package com.globant.pojos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class StockWithdrawal {
    private int productCode;
    private int requestedQuantity;

    @JsonCreator
    public StockWithdrawal(@JsonProperty("productCode") int productCode, @JsonProperty("requestedQuantity") int requestedQuantity) {
        this.productCode = productCode;
        this.requestedQuantity = requestedQuantity;
    }

    public int getProductCode() {
        return productCode;
    }

    public int getRequestedQuantity() {
        return requestedQuantity;
    }
}
