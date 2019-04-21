package com.company;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        String command = "git --version";
        Process child = Runtime.getRuntime().exec(command);

        InputStream is = child.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String line = null;
//        while ((line = reader.readLine()) != null) {
//            System.out.println(line);
//        }
        System.out.println(reader.readLine());
        is.close();
    }
}
