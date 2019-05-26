package com.company;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Main {

    public static void main(String[] args) throws IOException {
//        String command = "git --version";
//        Process child = Runtime.getRuntime().exec(command);
//
//        InputStream is = child.getInputStream();
//        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
//        String line = null;
////        while ((line = reader.readLine()) != null) {
////            System.out.println(line);
////        }
//        System.out.println(reader.readLine());
//        is.close();


        String test = "WorkItemIdentifier[ClientID,fObjectID,fInstructionID,WorkID]";
        String[] tokens = test.split("(?=\\[)");
        //System.out.println(tokens[1]);

        String pattern = "\\[(.*),(.*),(.*),(.*)\\]";
        // Create a Pattern object
        Pattern r = Pattern.compile(pattern);
        // Now create matcher object.
        Matcher m = r.matcher(tokens[1]);
        if(m.find()) {
            //System.out.println(m.group(0));
            System.out.println(m.group(1));
            System.out.println(m.group(2));
            System.out.println(m.group(3));
            System.out.println(m.group(4));
        }


    }
}
