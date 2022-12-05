package com.globant.shoppingcart;

import java.util.LinkedList;
import java.util.List;

//TODO: currently is allowing to remove only by item; allow removing by quantity
public class ShoppingCartDefault implements ShoppingCart {

    List<Item> items = new LinkedList<>();

    @Override
    public void addItem(Product product, int amount) {
        items.add(new Item(product, amount));
    }

    @Override
    public Item removeItem(int itemId) throws IndexOutOfBoundsException {
            return items.remove(itemId);
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
