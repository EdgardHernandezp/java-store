package com.globant.shoppingcart;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Item {
    private final Product product;

    private int quantity;

    @JsonCreator
    public Item(@JsonProperty("product") Product product, @JsonProperty("quantity") int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public Product getProduct() {
        return product;
    }

    @Override
    public String toString() {
        return "Item{" + "product=" + product + ", quantity=" + quantity + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Item item = (Item) o;

        return product.equals(item.product);
    }

    @Override
    public int hashCode() {
        return product.hashCode();
    }
}
