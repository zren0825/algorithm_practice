package com.company.LC;

import java.util.*;

public class L377CombinationSumIV {
    // DFS with memorization
    Map<Integer, Integer> map = new HashMap<>();

    public int combinationSum4(int[] nums, int target) {
        int count = 0;
        if (nums == null || nums.length == 0 || target < 0) return 0;
        if (target == 0) return 1;
        if (map.containsKey(target)) return map.get(target);
        for (int num : nums) {
            count += combinationSum4(nums, target - num);
        }
        map.put(target, count);
        return count;
    }

    // DP
    public int combinationSum4DP(int[] nums, int target) {
        int[] comb = new int[target + 1];
        comb[0] = 1;
        for (int i = 1; i < comb.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (i - nums[j] >= 0) {
                    comb[i] += comb[i - nums[j]];
                }
            }
        }
        return comb[target];
    }

    // Follow-up with negative integer
    Map<Integer, Map<Integer, Integer>> map2 = new HashMap<>();

    private int helper2(int[] nums, int len, int target, int MaxLen) {
        int count = 0;
        if (len > MaxLen) return 0;
        if (map2.containsKey(target) && map2.get(target).containsKey(len)) {
            return map2.get(target).get(len);
        }
        if (target == 0) count++;
        for (int num : nums) {
            count += helper2(nums, len + 1, target - num, MaxLen);
        }
        if (!map2.containsKey(target)) map2.put(target, new HashMap<Integer, Integer>());
        Map<Integer, Integer> mem = map2.get(target);
        mem.put(len, count);
        return count;
    }

    public int combinationSum42(int[] nums, int target, int MaxLen) {
        if (nums == null || nums.length == 0 || MaxLen <= 0) return 0;
        map2 = new HashMap<>();
        return helper2(nums, 0, target, MaxLen);
    }

}
