package com.globant.shoppingcart;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Product {
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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Product product = (Product) o;

        return code == product.code;
    }

    @Override
    public int hashCode() {
        return code;
    }
}
