package com.company.A.S190418;

import java.util.*;

public class NumberOfWaysFromStartToEnd {
    public static long numOfWaysFromStartToEndDFS(int width, int height) {
        Long[][] mem = new Long[width][height];
        return numOfWaysFromStartToEndDFS(width, height, 0, 0, mem);
    }

    private static long numOfWaysFromStartToEndDFS(int width, int height, int i, int j, Long[][] mem) {
        if (i == width - 1 && j == height - 1) {
            return 1;
        }
        if (i == width || j == height) {
            return 0;
        }
        if (mem[i][j] != null) {
            return mem[i][j];
        }
        long count = 0;
        count += numOfWaysFromStartToEndDFS(width, height, i + 1, j, mem);
        count += numOfWaysFromStartToEndDFS(width, height, i, j + 1, mem);

        mem[i][j] = count;
        return count;
    }

    public static long numOfWaysFromStartToEndDP(int width, int height) {
        int[] dp = new int[width];
        Arrays.fill(dp, 1);
        for (int i = 1; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (j == 0) {
                    continue;
                }
                dp[j] += dp[j - 1];
            }
        }
        return dp[width - 1];
    }

    public static void main(String[] args) {
        int width = 10;
        int height = 10;
        System.out.println("DP: " + numOfWaysFromStartToEndDP(width, height));
        System.out.println("DFS: " + numOfWaysFromStartToEndDFS(width, height));
    }
}
