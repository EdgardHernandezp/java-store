package com.globant.shoppingcart;

import java.util.LinkedList;
import java.util.List;

public class ShoppingCartDefault implements ShoppingCart {

    private List<Item> items = new LinkedList<>();

    @Override
    public void addItem(Product product, int amount) {
        Item item = new Item(product, amount);
        int index = items.indexOf(item);
        if (index >= 0) {
            item = items.get(index);
            item.setQuantity(item.getQuantity() + amount);
        } else
            items.add(item);
    }

    @Override
    public Item removeItem(int itemId, int quantity) throws IndexOutOfBoundsException {
        Item currentItem = items.get(itemId);
        int remainingQuantity = currentItem.getQuantity() - quantity;
        if (remainingQuantity <= 0)
            return items.remove(itemId);

        currentItem.setQuantity(remainingQuantity);
        return items.set(itemId, currentItem);
    }

    @Override
    public float calculateTotal() {
        return items.stream().map(item -> item.getProduct().getPrice() * item.getQuantity())
                .reduce((subtotal, nextAmount) -> subtotal + nextAmount).get();
    }

    @Override
    public Item[] getItems() {
        return items.stream().toArray(Item[]::new);
    }

    @Override
    public void removeAllItems() {
        items = new LinkedList<>();
    }
}
