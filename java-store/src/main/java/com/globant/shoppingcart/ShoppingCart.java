package com.globant.shoppingcart;

import com.globant.Product;

public interface ShoppingCart {
    void addItem(Product product, int amount);

    void deleteItem(int itemId);

    float calculateTotal();

    void showShoppingCartContent();
}
