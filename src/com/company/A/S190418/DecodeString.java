package com.company.A.S190418;

import java.util.*;

public class DecodeString {
    public String decodeString(String s) {
        if (s == null || s.length() == 0) return s;
        int len = s.length();
        Stack<StringBuilder> stackS = new Stack<>();
        stackS.push(new StringBuilder());
        Stack<Integer> stackN = new Stack();
        int curVal = 0;
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                curVal = curVal * 10 + (c - '0');
            } else if (c == '[') {
                stackS.push(new StringBuilder());
                stackN.push(curVal);
                curVal = 0;
            } else if (c == ']') {
                int count = stackN.pop();
                StringBuilder top = stackS.pop();
                //StringBuilder sb = new StringBuilder();
                for (int j = 0; j < count; j++) {
                    stackS.peek().append(top.toString());
                }
            } else if (Character.isLetter(c)) {
                stackS.peek().append(c);
            }


        }

        return stackS.peek().toString();
    }
}
