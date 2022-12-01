package com.globant;

import com.globant.communication.ServerFacade;
import com.globant.shoppingcart.ShoppingCart;
import java.util.Arrays;
import java.util.Optional;

public class CostumerSession {
    private final ShoppingCart shoppingCart;
    private final ServerFacade serverFacade = new ServerFacade(); //TODO: like this or inject it?

    public CostumerSession(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public void showShoppingCartContent() {
        Arrays.stream(shoppingCart.getItems()).forEach(System.out::println);
    }

    public float showShoppingCartTotal() {
        return shoppingCart.calculateTotal();
    }

    public Optional<Product> addProductToShoppingCart(int productCode, int quantity) {
        Optional<Product> optProduct = serverFacade.retrieveProductFromStorage(productCode, quantity);
        optProduct.ifPresent(product -> shoppingCart.addItem(product, quantity));
        return optProduct;
    }
}
