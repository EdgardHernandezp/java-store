package com.globant;

import java.io.Serializable;

public class Product implements Serializable {
    private static final long serialVersionUID = 1l;
    private int code;
    private String name;
    private float price;

    public Product(int code, String name, float price) {
        this.code = code;
        this.name = name;
        this.price = price;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
