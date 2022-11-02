package com.globant;

import java.io.Serializable;

public class Request implements Serializable {
    private static final long serialVersionUID = 1l;
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
