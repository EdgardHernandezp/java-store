package com.globant;

import com.globant.communication.ServerFacade;
import com.globant.payment.PaymentProcessor;
import com.globant.shoppingcart.Item;
import com.globant.shoppingcart.Product;
import com.globant.shoppingcart.ShoppingCart;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class CostumerSession {
    private final ShoppingCart shoppingCart;
    private final ServerFacade serverFacade = new ServerFacade(); //TODO: like this or inject it?
    private final PaymentProcessor paymentProcessor;

    public CostumerSession(ShoppingCart shoppingCart, PaymentProcessor paymentProcessor) {
        this.shoppingCart = shoppingCart;
        this.paymentProcessor = paymentProcessor;
    }

    public void showShoppingCartContent() {
        int i = 0;
        for (Item item : shoppingCart.getItems()) {
            String itemRow = i++ + " " + item.toString();
            System.out.println(itemRow);
        }
    }

    public float showShoppingCartTotal() {
        return shoppingCart.calculateTotal();
    }

    public void addProductToShoppingCart(int productCode, int quantity) {
        Optional<Product> optProduct = serverFacade.checkProductExistence(productCode);
        optProduct.ifPresentOrElse(product -> {
            shoppingCart.addItem(product, quantity);
            System.out.println(product.getName() + " added successfully");
        }, () -> System.out.println("product with code: " + productCode + " couldn't be added to the shopping cart"));
    }

    public void removeItemFromShoppingCart(int itemId, int quantity) {
        try {
            Item item = shoppingCart.removeItem(itemId, quantity);
            System.out.println(item.getProduct().getName() + " removed successfully");
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

    public void showAvailableProducts() {
        System.out.println("Products available:");
        serverFacade.searchAvailableProducts().stream().forEach(System.out::println);
    }

    public void checkoutShoppingCart() {
        if (paymentProcessor.processPayment(shoppingCart.calculateTotal())) {
            Item[] itemsNotProcessed = serverFacade.updateProductsStockInStorage(shoppingCart.getItems());
            if (itemsNotProcessed.length > 0) {
                System.out.println("The next items had no stock:");
                Arrays.stream(itemsNotProcessed).forEach(System.out::println);
                System.out.print("Total amount to reimburse: ");
                float totalReimbursement = 0;
                for (Item item : itemsNotProcessed)
                    totalReimbursement += (item.getProduct().getPrice() * item.getQuantity());
                System.out.println(totalReimbursement);
            }
            shoppingCart.removeAllItems();
        }
    }
}
