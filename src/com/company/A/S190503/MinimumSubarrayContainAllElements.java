package com.company.A.S190503;

public class MinimumSubarrayContainAllElements {
    // LC76
    public String minWindow(String s, String t) {
        int[] count = new int[256];
        for (int i = 0; i < t.length(); i++) {
            count[t.charAt(i)]++;
        }
        int start = 0, end = 0, total = t.length(), dist = Integer.MAX_VALUE, begin = 0;
        while (end < s.length()) {
            if (count[s.charAt(end)] > 0) {
                total--;
            }
            count[s.charAt(end)]--;
            end++;
            while (total == 0) {
                // update dist
                if (dist > end - start) {
                    dist = end - (begin = start);
                }
                // invalidate window
                if (count[s.charAt(start)] == 0) {
                    total++;
                }
                count[s.charAt(start)]++;
                start++;
            }
        }
        return dist == Integer.MAX_VALUE ? "" : s.substring(begin, begin + dist);
    }
}
