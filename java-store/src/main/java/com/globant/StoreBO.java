package com.globant;

import java.util.Scanner;

public class StoreBO {
    private final CostumerSession costumerSession;

    public StoreBO(CostumerSession costumerSession) {
        this.costumerSession = costumerSession;
    }

    public void run() {
        Scanner inputReader = new Scanner(System.in);
        int exitFlag = -1;
        while (exitFlag != 0) {
            System.out.println("Welcome to the generic store! These are the available actions: ");
            System.out.println("1 - Add product to shopping cart");
            System.out.println("2 - Remove product from shopping cart");
            System.out.println("3 - Show products in shopping cart");
            System.out.println("4 - Show shopping cart's total price");
            System.out.println("5 - search product by name");
            System.out.println("6 - show available products in the store");
            System.out.println("7 - manage resources");
            //TODO: admin features (add product, add product type)
            System.out.println("8 - checkout");
            System.out.println("9 - Clear shopping cart");
            System.out.println("0 - Exit the program");

            int userInput = inputReader.nextInt();
            switch (userInput) {
                case 1:
                    System.out.println("Indicate the code for the product to add");
                    final int productCode = inputReader.nextInt();
                    System.out.println("Indicate product quantity");
                    int quantity = inputReader.nextInt();
                    addProductToShoppingCart(productCode, quantity);
                    break;
                case 2:
                    System.out.println("Indicate product id in shopping cart");
                    int itemId = inputReader.nextInt();
                    System.out.println("Indicate item quantity to remove");
                    int itemQuantity = inputReader.nextInt();
                    removeItemFromShoppingCart(itemId, itemQuantity);
                    break;
                case 3:
                    System.out.println("These are the available products");
                    showShoppingCartContent();
                    System.out.println();
                    break;
                case 4:
                    printShoppingCartTotal();
                    break;
                case 5:
                    System.out.println("provide product name:");
                    String userProvidedProductName = inputReader.next();
                    searchProductByName(userProvidedProductName);
                    break;
                case 6:
                    showAvailableProducts();
                    break;
                case 7:
                    break;
                case 8:
                    checkoutShoppingCart();
                    break;
                case 9:
                    clearShoppingCart();
                    break;
                default:
                    exitFlag = userInput;
                    break;
            }
        }

        inputReader.close();
    }

    private void clearShoppingCart() {
        costumerSession.clearShoppingCart();
    }

    private void checkoutShoppingCart() {
        costumerSession.checkoutShoppingCart();
    }

    private void showAvailableProducts() {
        costumerSession.showAvailableProducts();
    }

    private void searchProductByName(String userProvidedProductName) {
        costumerSession.searchProductByName(userProvidedProductName);
    }

    private void removeItemFromShoppingCart(int itemId, int quantity) {
        costumerSession.removeItemFromShoppingCart(itemId, quantity);
    }

    private void printShoppingCartTotal() {
        costumerSession.showShoppingCartTotal();
    }

    private void addProductToShoppingCart(int productCode, int quantity) {
        costumerSession.addProductToShoppingCart(productCode, quantity);
    }

    private void showShoppingCartContent() {
        costumerSession.showShoppingCartContent();
    }
}
