package com.company.A.S190403;

import java.util.*;

public class MaxSubArray {
    // max subarray sum
    public static int maxSubArraySum(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int maxSum = nums[0];
        int curSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            curSum = Math.max(curSum + nums[i], nums[i]);
            maxSum = Math.max(maxSum, curSum);
        }
        return maxSum;
    }

    // return subarray
    public static int[] maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return new int[]{};
        int curLeft = 0;
        int curRight = 0;
        int curSum = nums[0];
        int maxLeft = 0;
        int maxRight = 0;
        int maxSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (curSum + nums[i] >= nums[i]) {
                curSum = curSum + nums[i];
                curRight = i;
            } else {
                curSum = nums[i];
                curLeft = curRight = i;
            }
            if (maxSum < curSum) {
                maxLeft = curLeft;
                maxRight = curRight;
                maxSum = curSum;
            }
        }
        return Arrays.copyOfRange(nums, maxLeft, maxRight + 1);
    }

    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int res = maxSubArraySum(nums);
        System.out.println("maxSubArraySum: " + res);

        System.out.println("maxSubArray Array: ");
        int[] resArray = maxSubArray(nums);
        for (int n : resArray) {
            System.out.println(n);
        }

    }
}
