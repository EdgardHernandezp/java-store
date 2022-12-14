package com.globant.pojos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Product {
    private int code;
    private String name;
    private double price;
    private ProductType productType;

    @JsonCreator
    public Product(@JsonProperty("code") int code,@JsonProperty("name") String name, @JsonProperty("price") double price, @JsonProperty("productType") ProductType productType) {
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
