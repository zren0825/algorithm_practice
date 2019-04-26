package com.company.A.S190412;

public class LongestIncreasingPath {
    public int longestIncreasingPath(int[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length ==0)
            return 0;
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] mem = new int[rows][cols];
        int maxLen = 0;
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                maxLen = Math.max(maxLen, dfs(matrix, i, j, Integer.MIN_VALUE, mem));
            }
        }
        return maxLen;
    }
    private int dfs(int[][] matrix, int i, int j, int prev, int[][] mem){
        int rows = matrix.length;
        int cols = matrix[0].length;
        if(i < 0 || i >= rows || j < 0 || j >= cols || matrix[i][j] <= prev)
            return 0;
        if(mem[i][j] != 0) return mem[i][j];
        int max = 0;
        max = Math.max(dfs(matrix, i+1, j, matrix[i][j], mem),
                Math.max(dfs(matrix, i-1, j, matrix[i][j], mem),
                        Math.max(dfs(matrix, i, j+1, matrix[i][j], mem),dfs(matrix, i, j-1, matrix[i][j], mem))));

        mem[i][j] = max + 1;
        return max + 1;

    }
}
