package com.globant.shoppingcart;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

public class Product implements Serializable {
    private final int code;
    private final String name;
    private final float price;

    @JsonCreator
    public Product(@JsonProperty("code") int code, @JsonProperty("name") String name, @JsonProperty("price") float price) {
        this.code = code;
        this.name = name;
        this.price = price;
    }

    public int getCode() {
        return code;
    }

    public float getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Product{" + "code=" + code + ", name='" + name + '\'' + ", price=" + price + '}';
    }
}
