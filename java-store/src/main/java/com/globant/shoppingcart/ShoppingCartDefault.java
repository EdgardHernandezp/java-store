package com.globant.shoppingcart;

import com.globant.Product;
import java.util.LinkedList;
import java.util.List;

public class ShoppingCartDefault implements ShoppingCart {

    List<Item> items = new LinkedList<>();

    @Override
    public void addItem(Product product, int amount) {
        items.add(new Item(product, amount));
    }

    @Override
    public void deleteItem(int itemId) {
        items.remove(itemId);
    }

    @Override
    public float calculateTotal() {
        return items.stream().map(item -> item.getProduct().getPrice() * item.getQuantity()).reduce((subtotal, nextAmount) -> subtotal + nextAmount).get();
    }

    @Override
    public Item[] getItems() {
        return items.stream().toArray(Item[]::new);
    }
}
