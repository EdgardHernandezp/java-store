package com.globant.server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.globant.StoreBO;
import com.globant.StoreBOImpl;
import com.globant.repos.StoreRepositoryImpl;
import com.globant.utils.ParserUtil;
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
            if (scanner.hasNext()) {
                System.out.println("reading new input");
                StoreBO storeBO = new StoreBOImpl(new StoreRepositoryImpl());
                String message = scanner.next();
                try {
                    out.println(storeBO.handleRequest(ParserUtil.parseRequest(message)));
                } catch (JsonProcessingException e) {
                    //TODO: return a response json string
                    e.printStackTrace();
                    out.println("error");
                }
                out.flush();
                System.out.println("response sent");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
