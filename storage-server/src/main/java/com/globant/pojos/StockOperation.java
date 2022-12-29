package com.globant.pojos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class StockOperation {
    private final Product product;
    private final int quantity;

    @JsonCreator
    public StockOperation(@JsonProperty("productCode") Product product, @JsonProperty("requestedQuantity") int quantity)
            throws RuntimeException {
        if (quantity == 0)
            throw new RuntimeException("StockOperation's quantity value has to be different from zero");
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
