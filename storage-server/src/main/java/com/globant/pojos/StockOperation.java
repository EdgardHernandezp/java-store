package com.globant.pojos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class StockOperation {
    private int productCode;
    private int quantity;

    @JsonCreator
    public StockOperation(@JsonProperty("productCode") int productCode, @JsonProperty("requestedQuantity") int quantity) {
        this.productCode = productCode;
        this.quantity = quantity;
    }

    public int getProductCode() {
        return productCode;
    }

    public int getQuantity() {
        return quantity;
    }
}
