package com.example;

import java.io.BufferedReader;
import java.io.IOException;

public class Read {

    private BufferedReader in;

    public Read(BufferedReader in) {
        this.in = in;
    }

    public String receive() throws IOException {
        return in.readLine();
    }
}
