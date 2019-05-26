package com.company.LC;

public class L132PalindromePartitioningII {
    // DFS
    public int minCut(String s) {
        return dfs(s, 0, new Integer[s.length()]) - 1;
    }

    private int dfs(String s, int index, Integer[] mem) {
        if (index >= s.length()) {
            return 0;
        }
        if (mem[index] != null) {
            return mem[index];
        }
        int minCut = s.length();
        for (int i = s.length() - 1; i >= index; i--) {
            if (isPalindrome(s.substring(index, i + 1))) {
                minCut = Math.min(minCut, dfs(s, i + 1, mem) + 1);
            }
        }
        mem[index] = minCut;
        return minCut;
    }

    private boolean isPalindrome(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != s.charAt(s.length() - i - 1)) {
                return false;
            }
        }
        return true;
    }

    // DP
    public int minCutDP(String s) {
        if (s == null || s.length() <= 1) return 0;
        int len = s.length();
        boolean[][] isPalindrome = new boolean[len][len];
        int[] dp = new int[len + 1];

        for (int i = len - 1; i >= 0; i--) {
            dp[i] = len - i;
            for (int j = i; j < len; j++) {
                if (i == j || (s.charAt(i) == s.charAt(j)
                        && (j == i + 1 || isPalindrome[i + 1][j - 1]))) {
                    dp[i] = Math.min(dp[i], dp[j + 1] + 1);
                    isPalindrome[i][j] = true;
                }
            }
        }
        return dp[0] - 1;
    }
}
