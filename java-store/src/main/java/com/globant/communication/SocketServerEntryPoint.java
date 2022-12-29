package com.globant.communication;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class SocketServerEntryPoint implements ServerEntryPoint {
    private static SocketServerEntryPoint instance;

    private SocketServerEntryPoint() {
    }

    public static SocketServerEntryPoint getInstance() {
        if (instance == null)
            instance = new SocketServerEntryPoint();
        return instance;
    }

    @Override
    public String sendRequest(String request) {
        String response = "{\"responseCode\":500,\"description\":\"server error: %s\"}";
        try (Socket socket = new Socket("localhost", 8081);
                PrintWriter out = new PrintWriter(socket.getOutputStream());
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8))) {
            out.write(request.concat("\n"));
            out.flush();
            Scanner scanner = new Scanner(in).useDelimiter("\n");
            response = scanner.next();
        } catch (Exception e) {
            response= String.format(response, e.getMessage());
            e.printStackTrace();
        }
        return response;
    }
}
