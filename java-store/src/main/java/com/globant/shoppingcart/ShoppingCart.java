package com.globant.shoppingcart;

public interface ShoppingCart {
    void addItem(Product product, int amount);

    Item removeItem(int itemId, int quantity) throws IndexOutOfBoundsException;

    float calculateTotal();

    Item[] getItems();

    void removeAllItems();
}
