package com.globant.pojos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class StockOperation {
    private Product product;
    private int quantity;

    @JsonCreator
    public StockOperation(@JsonProperty("productCode") Product product, @JsonProperty("requestedQuantity") int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }
}
