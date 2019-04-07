package com.company.A.S190222;

import java.util.Arrays;

public class CompressCharArray {
    public static char[] compress(char[] chars) {
        int start = 0;
        for (int end = 0, count = 0; end < chars.length; end++) {
            count++;
            if (end == chars.length - 1 || chars[end] != chars[end + 1]) {
                //We have found a difference or we are at the end of array
                chars[start] = chars[end]; // Update the character at start pointer
                start++;
                if (count != 1) {
                    // Copy over the character count to the array
                    char[] arr = String.valueOf(count).toCharArray();
                    for (int i = 0; i < arr.length; i++, start++)
                        chars[start] = arr[i];
                }
                // Reset the counter
                count = 0;
            }
        }
        return Arrays.copyOfRange(chars, 0, start);
    }

    public static void main(String[] args) {
        char[] test1 = {'a', 'a', 'b', 'b', 'c', 'c', 'c'};
        char[] result1 = compress(test1);
        for (char c : result1) {
            System.out.print(c + " ");
        }
        System.out.println();
        char[] test2 = {'a'};
        char[] result2 = compress(test2);
        for (char c : result2) {
            System.out.print(c + " ");
        }
        System.out.println();
        char[] test3 = {'a', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'};
        char[] result3 = compress(test3);
        for (char c : result3) {
            System.out.print(c + " ");
        }
        System.out.println();
    }
}
