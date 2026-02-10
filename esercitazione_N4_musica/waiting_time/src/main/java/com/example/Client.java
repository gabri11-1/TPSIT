package com.example;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private Read reader;
    private Write writer;
    private Scanner scanner;

    public Client(Socket socket) throws IOException {
        BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(
                new OutputStreamWriter(socket.getOutputStream()));

        reader = new Read(in);
        writer = new Write(out);
        scanner = new Scanner(System.in);
    }

    public void start() throws IOException {
        System.out.println("Comandi: LOOK, ATTACK, QUIT");

        while (true) {
            System.out.print("> ");
            String command = scanner.nextLine();

            writer.send(command);
            String response = reader.receive();
            System.out.println(response);

            if (command.equalsIgnoreCase("QUIT")) {
                break;
            }
        }
    }

    public void scrivi(){
        try{
            OutputStream outputStream = Socket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(outputStream, true);
            printWriter.println("Ciao Server!");
            printWriter.flush();

        } catch (IOException e){
            e.printStackTrace();
        }
            
    }
}
