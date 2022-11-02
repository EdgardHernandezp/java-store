package com.globant;

public interface ShoppingCart {
    void addItem(Item item);

    void deleteItem(int itemId);

    float calculateTotal();

    void showShoppingCartContent();
}
