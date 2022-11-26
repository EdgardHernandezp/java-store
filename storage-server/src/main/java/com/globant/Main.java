package com.globant;

import com.globant.server.StoreServer;

public class Main {

    public static void main(java.lang.String[] args) {
        //TODO return a menu with codes and amounts for the client
        System.out.println("Initiating server");
        StoreServer server = new StoreServer();
        server.run();
    }
}
