package com.globant.server;

import com.globant.StoreBOImpl;
import com.globant.repos.StoreRepositoryImpl;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class StoreServer {

    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(8081);) {
            while (true) {
                Socket socket = serverSocket.accept();
                RequestProcessingTask task = new RequestProcessingTask(socket, new StoreBOImpl(StoreRepositoryImpl.getInstance()));
                Thread thread = new Thread(task);
                thread.start();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
