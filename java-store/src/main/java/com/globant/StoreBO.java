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
            System.out.println("0 - Exit the program");

            int userInput = inputReader.nextInt();
            switch (userInput) {
                case 1:
                    System.out.println("Indicate the code for the product to add");
                    //TODO: validate user input
                    final int productCode = inputReader.nextInt();
                    System.out.println("Indicate product quantity");
                    int quantity = inputReader.nextInt();
                    addProductToShoppingCart(productCode, quantity);
                    //TODO: add messages to a property file
                    break;
                case 2:
                    System.out.println("Indicate product id in shopping cart");
                    int itemId = inputReader.nextInt();
                    removeItemFromShoppingCart(itemId);
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
                    break;
                case 6:
                    break;
                default:
                    exitFlag = userInput;
                    break;
            }
            //TODO: if car it's not paid, return all products back to storage
        }

        inputReader.close();
    }

    private void removeItemFromShoppingCart(int itemId) {
        //TODO: add product back to storage
        costumerSession.removeItemFromShoppingCart(itemId);
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
