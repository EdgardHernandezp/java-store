package com.globant;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(java.lang.String[] args) {
        //TODO return a menu with codes and amounts for the client
        //TODO: transfer communication logic to different class
        System.out.println("Initiating server");
        StoreServer server = new StoreServer();
        server.run();
    }
}
