package com.company.LC;

/**
 * @Author Cherryl Li
 * @Date 2019-05-19
 * @Time 16:07
 */

/**
 * Given a string S and a string T, count the number of distinct subsequences of S which equals T.
 * <p>
 * A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).
 * <p>
 * Example 1:
 * <p>
 * Input: S = "rabbbit", T = "rabbit"
 * Output:
 * Explanation:
 * <p>
 * As shown below, there are 3 ways you can generate "rabbit" from S.
 * (The caret symbol ^ means the chosen letters)
 * <p>
 * rabbbit
 * ^^^^ ^^
 * rabbbit
 * ^^ ^^^^
 * rabbbit
 * ^^^ ^^^
 */

//二维DP，区间范围 i：[0,lenS] j：[0，lenT], Base Case：dp[i][lenT] =1; dp[lenS][j] =0;
//状态转移方程：相等：dp[i+1][j]，不相等add element:dp[i+1][j+1] moveOn：dp[i+1][j]两者相加找Subsequence
public class L115DistinctSubsequences {
    public void test() {
        String S = "rabbbit";
        String T = "rabbit";
        System.out.println(numDistinct(S, T));
        System.out.println(numDistinctDP(S, T));

    }

    //solution 1: dfs +Pruning
    public int numDistinct(String s, String t) {
        //corner case
        if (s == null || t == null || s.length() == 0) return 0;
        if (t.length() == 0) return 1;
        Integer[][] mem = new Integer[s.length()][t.length()];
        return dfs(s.toCharArray(), t.toCharArray(), 0, 0, mem);

    }

    private int dfs(char[] s, char[] t, int i, int j, Integer[][] mem) {
        //success case
        if (j == t.length) return 1;
        //fail case
        if (i == s.length) return 0;

        int res;
        if (mem[i][j] != null) return mem[i][j];

        if (s[i] != t[j]) {
            res = dfs(s, t, i + 1, j, mem);
        } else {
            //相等keep + moveOnt
            res = dfs(s, t, i + 1, j + 1, mem) + dfs(s, t, i + 1, j, mem);
        }
        mem[i][j] = res;
        return res;
    }

    // DP revised
    public int numDistinctDP(String s, String t) {
        int lenS = s.length(), lenT = t.length();
        //corner case
        if (s == null || t == null || lenS == 0) return 0;
        if (lenT == 0) return 1;

        int[][] dp = new int[lenS + 1][lenT + 1];
        for (int i = 0; i <= lenS; i++) {
            for (int j = 0; j <= lenT; j++) {
                char sc = s.charAt(i - 1);
                char tc = t.charAt(j - 1);
                if (j == 0) {
                    dp[i][j] = 1;
                    continue;
                }
                if (i > 0) {
                    if (sc == tc) {
                        dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                    } else {
                        dp[i][j] = dp[i - 1][j];
                    }
                }
            }
        }
        return dp[lenS][lenT];
    }
}
