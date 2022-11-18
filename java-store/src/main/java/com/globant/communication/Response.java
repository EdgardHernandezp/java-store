package com.globant.communication;

import com.globant.Product;
import java.io.Serializable;
import java.util.List;

public class Response implements Serializable {
    private static final long serialVersionUID = 1l;
    private List<Product> products;
    private String errorDescription;

    public Response() {
    }

    public Response(List<Product> products, String errorDescription) {
        this.products = products;
        this.errorDescription = errorDescription;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }
}
