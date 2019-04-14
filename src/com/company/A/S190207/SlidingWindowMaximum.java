package com.company.A.S190207;

import java.util.*;

public class SlidingWindowMaximum {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length < k || k == 0) return new int[]{};
        int[] res = new int[nums.length - k + 1];
        // store index
        Deque<Integer> deq = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            // 出界了
            while (!deq.isEmpty() && deq.peekFirst() < i - k + 1) {
                deq.pollFirst();
            }
            //不是最大的了
            while (!deq.isEmpty() && nums[deq.peekLast()] < nums[i]) {
                deq.pollLast();
            }
            deq.offer(i);
            if (i >= k - 1) {
                res[i - k + 1] = nums[deq.peekFirst()];
            }
        }
        return res;
    }
}
