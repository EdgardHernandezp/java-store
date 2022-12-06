package com.globant;

import com.globant.communication.ServerFacade;
import com.globant.shoppingcart.Item;
import com.globant.shoppingcart.Product;
import com.globant.shoppingcart.ShoppingCart;
import java.util.List;
import java.util.Optional;

public class CostumerSession {
    private final ShoppingCart shoppingCart;
    private final ServerFacade serverFacade = new ServerFacade(); //TODO: like this or inject it?

    public CostumerSession(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public void showShoppingCartContent() {
        int i = 0;
        for (Item item : shoppingCart.getItems()) {
            String itemRow = i + " " + item.toString();
            System.out.println(itemRow);
        }
    }

    public float showShoppingCartTotal() {
        return shoppingCart.calculateTotal();
    }

    public void addProductToShoppingCart(int productCode, int quantity) {
        Optional<Product> optProduct = serverFacade.updateProductStockInStorage(productCode, quantity * -1);
        optProduct.ifPresentOrElse(product -> {
            shoppingCart.addItem(product, quantity);
            System.out.println(product.getName() + " added successfully");
        }, () -> System.out.println("product with code: " + productCode + " couldn't be added to the shopping cart"));
    }

    public void removeItemFromShoppingCart(int itemId) {
        //TODO: Check with mentor how to maintain data consistency between shopping cart and storage
        try {
            Item item = shoppingCart.removeItem(itemId);
            Optional<Product> optProduct = serverFacade.updateProductStockInStorage(item.getProduct().getCode(), item.getQuantity());
            optProduct.ifPresent(product -> System.out.println(product.getName() + " removed successfully"));
            //TODO: handle case where there's an error updating storage
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Item with id = " + itemId + " could not be removed from shopping cart");
            e.printStackTrace();
        }
    }

    public void searchProductByName(String userProvidedProductName) {
        List<Product> products = serverFacade.searchProductByName(userProvidedProductName);
        System.out.println("products found:");
        products.stream().forEach(System.out::println);
    }
}
