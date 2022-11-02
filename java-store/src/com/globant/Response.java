package com.globant;

import java.io.Serializable;

public class Response implements Serializable {
    private static final long serialVersionUID = 1l;
    private Product product;
    private String errorDescription;

    public Response(Product product, String errorDescription) {
        this.product = product;
        this.errorDescription = errorDescription;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }
}
