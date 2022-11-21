package com.globant.communication;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.stream.Collectors;

public class StorageProtocol {
    static public String requestProduct(String request) {
        String response = "No response from server";
        try (Socket socket = new Socket("localhost", 8081);
                PrintWriter out = new PrintWriter(socket.getOutputStream());
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8))) {
            out.write(request);
            out.flush();
            Scanner scanner = new Scanner(in).useDelimiter("\n"); //TODO: understand flush and the blocking problem
            response = scanner.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}
