package com.globant.communication.requests;

public class StockWithdrawal {
    private final int productCode;
    private final int requestedQuantity;

    public StockWithdrawal(int productCode, int requestedQuantity) {
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
