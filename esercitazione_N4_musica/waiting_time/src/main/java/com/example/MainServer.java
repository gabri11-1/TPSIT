package com.example;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class MainServer {

    public static void main(String[] args) {
        System.out.println("Server avviato...");

        try (ServerSocket serverSocket = new ServerSocket(Main.PORT)) {

            Socket client = serverSocket.accept();
            System.out.println("Client connesso.");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(client.getInputStream()));
            PrintWriter out = new PrintWriter(
                    new OutputStreamWriter(client.getOutputStream()));

            Read reader = new Read(in);
            Write writer = new Write(out);
            Server server = new Server();

            String request;
            while ((request = reader.receive()) != null) {
                String response = server.handleRequest(request);
                writer.send(response);

                if (request.equalsIgnoreCase("QUIT")) {
                    break;
                }
            }

            client.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
