package com.globant;

import java.io.Serializable;

public class Product implements Serializable {
    private static final long serialVersionUID = 1l;
    private int code;
    private String name;
    private double price;
    private ProductType productType;

    public Product(int code, String name, double price, ProductType productType) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.productType = productType;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
