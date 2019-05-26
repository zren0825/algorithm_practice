package com.company.LC;

/**
 * @Author Cherryl Li
 * @Date 2019-05-17
 * @Time 15:42
 */


import java.util.ArrayList;
import java.util.List;
/**
 * @Author Cherryl Li
 * @Date 2019-05-19
 * @Time 17:14
 */

/**
 * Given two words word1 and word2, find the minimum number of operations required to convert word1 to word2.
 * <p>
 * You have the following 3 operations permitted on a word:
 * <p>
 * Insert a character
 * Delete a character
 * Replace a character
 * Example 1:
 * <p>
 * Input: word1 = "horse", word2 = "ros"
 * Output: 3
 * Explanation:
 * horse -> horse (replace 'h' with 'r')
 * rorse -> rose (remove 'r')
 * rose -> ros (remove 'e')
 * Example 2:
 * <p>
 * Input: word1 = "intention", word2 = "execution"
 * Output: 5
 * Explanation:
 * intention -> inention (remove 't')
 * inention -> enention (replace 'i' with 'e')
 * enention -> exention (replace 'n' with 'x')
 * exention -> exection (replace 'n' with 'c')
 * exection -> execution (insert 'u')
 */
//分叉问题dfs，过程是两个string，是否match(相等-->前进，不相等 -->增加，删除)，2维的DP
public class L72EditDistance {
    public void test() {
        String word1 = "horse";
        String word2 = "ros";
        System.out.println(minDistance(word1, word2));
        System.out.print(minDistance1(word1, word2));
    }

    public int minDistance(String word1, String word2) {
        int len1 = word1.length(), len2 = word2.length();
        int[][] cost = new int[len1 + 1][len2 + 1];


        for (int j = 0; j <= len2; j++)
            cost[0][j] = j;

        for (int i = 0; i <= len1; i++)
            cost[i][0] = i;


        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                // 如果word1在i-1位置字符与word2在j-1位置字符一样 则不需要转换 即cost[i][j] = cost[i - 1][j - 1]
                if (word1.charAt(i - 1) == word2.charAt(j - 1))
                    cost[i][j] = cost[i - 1][j - 1];
                    // 否则依次查看对word1在i-1位置上的字符操作次数：增删改
                else {
                    int add = cost[i][j - 1] + 1; // 在word1的i-1位置上增加一个字符word2.charAt(j - 1)
                    int delete = cost[i - 1][j] + 1; // 将word1在i-1的位置上的字符删掉
                    int update = cost[i - 1][j - 1] + 1; // 将word1在i-1上的位置上的字符换成word2在j-1位置上的字符
                    cost[i][j] = Math.min(Math.min(add, delete), update);
                }
            }
        }
        return cost[len1][len2];
    }

    //solution1: dfs + pruning
    public int minDistance1(String word1, String word2) {
        int len1 = word1.length(), len2 = word2.length();
        Integer[][] mem = new Integer[len1 + 1][len2 + 1];

        int min = dfs(word1.toCharArray(), 0, word2.toCharArray(), 0, mem);

        return min;
    }

    private int dfs(char[] s1, int i, char[] s2, int j, Integer[][] mem) {
        int len1 = s1.length, len2 = s2.length;
        if (i == len1) return len2 - j;
        if (j == len2) return len1 - i;
        int min;
        if (mem[i][j] != null
        ) return mem[i][j];

        if (s1[i] == s2[j]) {
            min = dfs(s1, i + 1, s2, j + 1, mem);
        } else {
            int op1 = dfs(s1, i + 1, s2, j + 1, mem);//replace
            int op2 = dfs(s1, i + 1, s2, j, mem);//insert2 and delete1
            int op3 = dfs(s1, i, s2, j + 1, mem);//insert1 and delete2
            min = Math.min(op3, Math.min(op1, op2)) + 1;

        }
        mem[i][j] = min;
        return min;
    }

}
