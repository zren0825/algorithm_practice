package com.company.A.S190412;

public class BestTimeToBuyAndSellStock {
    public int maxProfitSingleTranSingleHold(int[] prices) {
        if(prices == null || prices.length == 0) return 0;
        int buy = Integer.MAX_VALUE;
        int benefit = 0;
        /*
        for(int i = 0; i < prices.length; i++){
            if(prices[i] > buy){
                benefit = Math.max(benefit, prices[i] - buy);
            }
            else{
                buy = prices[i];
            }
        }
        */
        int cur = 0;
        for(int i = 1; i < prices.length; i++) {
            cur = Math.max(0, cur += prices[i] - prices[i-1]);
            benefit = Math.max(cur, benefit);
        }

        return benefit;
    }

    public int maxProfitMultiTranSingleHold(int[] prices) {
        if(prices == null || prices.length == 0) return 0;
        int buy = Integer.MAX_VALUE;
        int benefit = 0;
        /*
        for(int i = 0; i < prices.length; i++){
            if(prices[i] > buy){
                benefit = Math.max(benefit, prices[i] - buy);
            }
            else{
                buy = prices[i];
            }
        }
        */
        int cur = 0;
        for(int i = 1; i < prices.length; i++) {
            cur = Math.max(0, cur += prices[i] - prices[i-1]);
            benefit = Math.max(cur, benefit);
        }
        return benefit;
    }

    public int maxProfitTwoTranSingleHold(int[] prices) {
        if(prices == null || prices.length == 0) return 0;
        int len = prices.length;
        int[][] dp = new int[len + 1][5 + 1];
        dp[0][1] = 0;
        dp[0][2] = dp[0][3] = dp[0][4] = dp[0][5] = Integer.MIN_VALUE;
        for(int i = 1; i <= len; i++){
            for(int j = 1; j <= 5; j++){
                // status 1,3,5
                if(j % 2 == 1){
                    dp[i][j] = dp[i-1][j];
                    if(i > 1 && j > 1 && dp[i-1][j-1] != Integer.MIN_VALUE)
                        dp[i][j] = Math.max(dp[i][j], dp[i-1][j-1] + prices[i-1] - prices[i-2]);
                }
                // status 2,4
                else{
                    dp[i][j] = dp[i-1][j-1];
                    if(i > 1 && j > 1 && dp[i-1][j] != Integer.MIN_VALUE)
                        dp[i][j] = Math.max(dp[i-1][j] + prices[i-1] - prices[i-2], dp[i][j]);

                    if(i > 1 && j > 2 && dp[i-1][j-2] != Integer.MIN_VALUE)
                        dp[i][j] = Math.max(dp[i-1][j-2] + prices[i-1] - prices[i-2], dp[i][j]);
                }
            }
        }
        return Math.max(dp[len][1], Math.max(dp[len][3], dp[len][5]));
    }

    // Time: O(N*K)  Space: O(N*K)
    // Use Rolling Array to optimize space to O(K)
    public int maxProfitKTranSingleHold(int k, int[] prices) {
        if(prices == null || prices.length == 0) return 0;
        int len = prices.length;
        // Rollback to Stock II
        if(k > len/2){ // k > len
            return oneDayKeep(prices);
        }
        int[][] dp = new int[len + 1][2*k+1 + 1];
        //Init
        dp[0][1] = 0;
        for(int i = 2; i <= 2*k + 1; i++) dp[0][i] = Integer.MIN_VALUE;
        for(int i = 1; i <= len; i++){
            for(int j = 1; j <= 2 * k + 1; j++){
                // status 1,3,5
                if(j % 2 == 1){
                    dp[i][j] = dp[i-1][j];
                    if(i > 1 && j > 1 && dp[i-1][j-1] != Integer.MIN_VALUE)
                        dp[i][j] = Math.max(dp[i][j], dp[i-1][j-1] + prices[i-1] - prices[i-2]);
                }
                // status 2,4
                else{
                    dp[i][j] = dp[i-1][j-1];
                    if(i > 1 && j > 1 && dp[i-1][j] != Integer.MIN_VALUE)
                        dp[i][j] = Math.max(dp[i-1][j] + prices[i-1] - prices[i-2], dp[i][j]);

                    if(i > 1 && j > 2 && dp[i-1][j-2] != Integer.MIN_VALUE)
                        dp[i][j] = Math.max(dp[i-1][j-2] + prices[i-1] - prices[i-2], dp[i][j]);
                }
            }
        }
        int benefit = 0;
        for(int i = 1; i <= 2*k + 1; i+=2){
            benefit = Math.max(dp[len][i], benefit);
        }
        return benefit;
    }
    private int oneDayKeep(int[] prices){
        int benefit = 0;
        for(int i = 1; i < prices.length; i++){
            benefit += prices[i] > prices[i-1] ? prices[i] - prices[i-1] : 0;
        }
        return benefit;
    }
}
