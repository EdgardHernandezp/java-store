package com.globant;

import java.util.Optional;
import java.util.Scanner;

public class StoreBO {
    private final CostumerSession costumerSession;

    public StoreBO(CostumerSession costumerSession) {
        this.costumerSession = costumerSession;
    }

    public void run() {
        System.out.println("Welcome to the generic store! These are the available actions: ");
        System.out.println("1 - Add product to shopping cart");
        System.out.println("2 - Remove product from shopping cart");
        System.out.println("3 - Show products in shopping cart");
        System.out.println("4 - Show shopping cart's total price");
        System.out.println("5 - search product by name");
        System.out.println("6 - show available products in the store");

        Scanner inputReader = new Scanner(System.in);
        int userInput = inputReader.nextInt();
        switch (userInput) {
            case 1:
                System.out.println("Indicate the code for the product to add");
                //TODO: validate user input
                final int productCode = inputReader.nextInt();
                System.out.println("Indicate product quantity");
                int quantity = inputReader.nextInt();
                Optional<Product> optProductAdded = addProductToShoppingCart(productCode, quantity);
                //TODO: add messages to a property file
                optProductAdded.ifPresentOrElse(product -> System.out.println(product.getName() + " added successfully"),
                        () -> System.out.println("product with code: " + productCode + " couldn't be added to the shopping cart"));
                break;
            case 2:

                break;
            case 3:
                System.out.println("These are the available products");
                showShoppingCartContent();
                break;
            case 4:
                printShoppingCartTotal();
                break;
            case 5:
                break;
            case 6:
                break;
        }

        inputReader.close();
    }

    private void printShoppingCartTotal() {
        costumerSession.showShoppingCartTotal();
    }

    private Optional<Product> addProductToShoppingCart(int productCode, int quantity) {
        return costumerSession.addProductToShoppingCart(productCode, quantity);
    }

    private void showShoppingCartContent() {
        costumerSession.showShoppingCartContent();
    }
}
