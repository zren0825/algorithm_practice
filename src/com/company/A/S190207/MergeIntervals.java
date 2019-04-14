package com.company.A.S190207;

import java.util.*;

class Interval {
    int start;
    int end;

    Interval() {
        start = 0;
        end = 0;
    }

    Interval(int s, int e) {
        start = s;
        end = e;
    }
}


public class MergeIntervals {
    public List<Interval> merge1(List<Interval> intervals) {
        List<Interval> res = new LinkedList<>();
        if (intervals == null || intervals.size() == 0) return res;
        Collections.sort(intervals, (a, b) -> a.start - b.start);
        Interval prev = intervals.get(0);
        res.add(prev);
        for (int i = 1; i < intervals.size(); i++) {
            Interval cur = intervals.get(i);
            // merge
            if (prev.end >= cur.start) {
                int newEnd = Math.max(prev.end, cur.end);
                Interval newInterval = new Interval(prev.start, newEnd);
                res.set(res.size() - 1, newInterval);
                prev = newInterval;
            }
            // not merge
            else {
                res.add(cur);
                prev = cur;
            }
        }
        return res;
    }

    public List<Interval> merge2(List<Interval> intervals) {
        int n = intervals.size();
        int[] start = new int[n];
        int[] end = new int[n];
        for (int i = 0; i < n; i++) {
            start[i] = intervals.get(i).start;
            end[i] = intervals.get(i).end;
        }
        Arrays.sort(start);
        Arrays.sort(end);

        List<Interval> res = new ArrayList<Interval>();
        for (int i = 0, j = 0; i < n; i++) {
            if (i == n - 1 || start[i + 1] > end[i]) {
                res.add(new Interval(start[j], end[i]));
                j = i + 1;
            }
        }
        return res;
    }

}
