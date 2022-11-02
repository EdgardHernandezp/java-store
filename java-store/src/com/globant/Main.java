package com.globant;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Main {

    public static void main(String[] args) {
        //TODO create collection with existent products
        //TODO group products by type to find the cheaper ones
        //TODO find functionality
        Response response = null;
        int amount = 1;
        try (Socket socket = new Socket("localhost", 8081);
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
            Request request = new Request(1, amount);
            out.writeObject(request);
            response = (Response) in.readObject();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        ShoppingCart shoppingCart = new ShoppingCartDefault();
        Product product = response.getProduct();
        shoppingCart.addItem(new Item(product, amount));

        shoppingCart.showShoppingCartContent();
        System.out.println("Total: " + shoppingCart.calculateTotal());

//        shoppingCart.deleteItem(2);
//        shoppingCart.showShoppingCartContent();
//        System.out.println("Total: " + shoppingCart.calculateTotal());

    }
}
