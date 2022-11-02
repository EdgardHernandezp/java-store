package com.globant;

import java.util.LinkedList;
import java.util.List;

public class ShoppingCartDefault implements ShoppingCart {

    List<Item> items = new LinkedList<>();

    @Override
    public void addItem(Item item) {
        items.add(item);
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
    public void showShoppingCartContent() {
        items.stream().forEach(item -> System.out.println("name: " + item.getProduct().getName()));
    }
}
