package com.globant;

import com.globant.repos.StoreRepositoryImpl;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.stream.Collectors;

public class StoreServer {
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(8081);
                Socket socket = serverSocket.accept();
                PrintWriter out = new PrintWriter(socket.getOutputStream());
                InputStreamReader in = new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8);) {
            Scanner scanner = new Scanner(in).useDelimiter("\n");
            while (true) {
                System.out.println("reading new input");
                StoreBO storeBO = new StoreBOImpl(new StoreRepositoryImpl());
                String response = storeBO.handleRequest(scanner.next());

                out.println(response);
                out.flush();
                System.out.println("response sent");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
