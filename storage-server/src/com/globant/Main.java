package com.globant;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {
        //TODO return a menu with codes and amounts for the client
        //TODO add state, control de amount of items in storage for each product
        //check for amounts of products before returning product
        System.out.println("Initiating server");
        try (ServerSocket serverSocket = new ServerSocket(8081);
                Socket socket = serverSocket.accept();
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

            StorageProtocol protocol = new DefaultStorageProtocol();
            Request request;
            while (true) {
                try {
                    System.out.println("reading new input");
                    request = (Request) in.readObject();
                    Response response = protocol.handleRequest(request);
                    out.writeObject(response);
                    System.out.println("response sent");
                } catch (EOFException e) {
                    System.out.println("no more responses");
                    break;
                }
            }
            System.out.println("Terminating server");

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
