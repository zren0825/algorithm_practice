package com.company.A.S190222;

import java.util.*;

class Interval {
    int startTime;
    int endTime;
    int value;

    public Interval(int startTime, int endTime, int value) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.value = value;
    }
}

class EndPoint {
    int value;
    int time;
    boolean isEnd;

    public EndPoint(int value, int time, boolean isEnd) {
        this.value = value;
        this.time = time;
        this.isEnd = isEnd;
    }
}

public class IntervalMaxValue {
    // Intervals: startTime, endTime, value, 找最大叠加的value
    // Not streaming
    public int maxValue(List<Interval> intervals) {
        List<EndPoint> endPoints = new LinkedList<>();
        for (Interval interval : intervals) {
            endPoints.add(new EndPoint(interval.value, interval.startTime, false));
            endPoints.add(new EndPoint(interval.value, interval.endTime, true));
        }
        Collections.sort(endPoints, (a, b) -> a.time - b.time);
        int maxValue = 0;
        int curMax = 0;
        for (EndPoint endPoint : endPoints) {
            if (!endPoint.isEnd) {
                curMax += endPoint.value;
            } else {
                curMax -= endPoint.value;
            }
            maxValue = curMax > maxValue ? curMax : maxValue;
        }
        return maxValue;
    }

    // Streaming
    public int maxValue() {
        TreeMap<Integer, Interval> treeMap = new TreeMap<>();
        int maxValue = 0;
        while (hasNext()) {
            Interval newInterval = getNext();
            int startTime = newInterval.startTime;
            int endTime = newInterval.endTime;
            int value = newInterval.value;
            boolean inserted = false;
            Interval prevInterval = treeMap.get(treeMap.floorKey(startTime));
            if (prevInterval != null && prevInterval.endTime > startTime) {
                treeMap.remove(prevInterval.startTime);
                // 两种情况都要加最左边的
                if (startTime != prevInterval.startTime) {
                    treeMap.put(prevInterval.startTime, new Interval(prevInterval.startTime, startTime, prevInterval.value));
                }
                // 包含
                if (endTime <= prevInterval.endTime) {
                    treeMap.put(startTime, new Interval(startTime, endTime, prevInterval.value + value));
                    if (endTime != prevInterval.endTime) {
                        treeMap.put(endTime, new Interval(endTime, prevInterval.endTime, prevInterval.value));
                    }
                } else {
                    treeMap.put(startTime, new Interval(startTime, prevInterval.endTime, prevInterval.value + value));
                    treeMap.put(prevInterval.endTime, new Interval(prevInterval.endTime, endTime, value));
                }
                maxValue = prevInterval.value + value > maxValue ? prevInterval.value + value : maxValue;

                inserted = true;
            }
            Interval nextInterval = treeMap.get(treeMap.ceilingKey(startTime));
            while (nextInterval != null && nextInterval.startTime < endTime) {
                treeMap.remove(nextInterval.startTime);
                // 两种情况都要加最左边的
                if (startTime != nextInterval.startTime) {
                    treeMap.put(startTime, new Interval(startTime, nextInterval.startTime, value));
                }
                // 包含
                if (nextInterval.endTime <= endTime) {
                    treeMap.put(nextInterval.startTime, new Interval(nextInterval.startTime, nextInterval.endTime, nextInterval.value + value));
                    if (nextInterval.endTime != endTime) {
                        treeMap.put(nextInterval.endTime, new Interval(nextInterval.endTime, endTime, value));
                    }
                } else {
                    treeMap.put(nextInterval.startTime, new Interval(nextInterval.startTime, endTime, nextInterval.value + value));
                    treeMap.put(endTime, new Interval(endTime, nextInterval.endTime, nextInterval.value));
                }
                maxValue = prevInterval.value + value > maxValue ? prevInterval.value + value : maxValue;
                inserted = true;
                nextInterval = treeMap.get(treeMap.ceilingKey(nextInterval.startTime));
            }
            if(!inserted) {
                treeMap.put(startTime, newInterval);
                maxValue = value > maxValue ? value : maxValue;
            }
        }
        return maxValue;
    }

    private Interval getNext() {
        return null;
    }

    private boolean hasNext() {
        return false;
    }

    public static void main(String[] args) {

    }
}
