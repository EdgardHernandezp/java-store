package com.globant.communication.requests;

public class StockOperation {
    private final int productCode;
    private final int quantity;

    public StockOperation(int productCode, int quantity) {
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
