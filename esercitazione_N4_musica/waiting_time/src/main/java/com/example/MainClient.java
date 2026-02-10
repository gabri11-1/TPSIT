package com.example;

import java.net.Socket;

public class MainClient {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket(Main.HOST, Main.PORT);
            Client client = new Client(socket);
            client.start();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
