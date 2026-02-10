package com.example;

import java.io.PrintWriter;

public class Write {

    private PrintWriter out;

    public Write(PrintWriter out) {
        this.out = out;
    }

    public void send(String json) {
        out.println(json);
        out.flush();
    }
}
