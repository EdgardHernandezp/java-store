package com.globant.server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.globant.StoreBO;
import com.globant.utils.ParserUtil;
import com.globant.utils.PropertiesHolder;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class RequestProcessingTask implements Runnable {
    private final Socket socket;
    private final StoreBO storeBO;

    public RequestProcessingTask(Socket socket, StoreBO storeBO) {
        this.socket = socket;
        this.storeBO = storeBO;
    }

    @Override
    public void run() {
        try (PrintWriter out = new PrintWriter(socket.getOutputStream());
                InputStreamReader in = new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8);) {
            Scanner scanner = new Scanner(in).useDelimiter("\n");
            if (scanner.hasNext()) {
                System.out.println("reading new input");
                String message = scanner.next();
                try {
                    out.println(storeBO.handleRequest(ParserUtil.parseRequest(message)));
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                    String rawResponse = PropertiesHolder.getProperty("json.server.error.msg");
                    out.println(String.format(rawResponse, e.getMessage()));
                }
                out.flush();
                System.out.println("response sent");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
