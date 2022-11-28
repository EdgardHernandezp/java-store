package com.globant.communication;

import java.io.Serializable;

public class Request implements Serializable {
    private int productCode;
    private int amount;

    public int getProductCode() {
        return productCode;
    }

    public Request(int productCode, int amount) {
        this.productCode = productCode;
        this.amount = amount;
    }

    public void setProductCode(int productCode) {
        this.productCode = productCode;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
