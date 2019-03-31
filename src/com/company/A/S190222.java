package com.company.A;

import java.util.*;

public class S190222 {
    // 多叉树找多个nodes的共同祖先
    static class TreeNode {
        int value;
        List<TreeNode> children;

        public TreeNode(int value) {
            this.value = value;
            this.children = new LinkedList<>();
        }

        public int hashCode() {
            return this.value;
        }

        public boolean equals(TreeNode a, TreeNode b) {
            return a.value == b.value;
        }
    }

    static TreeNode LCA = null;
    static int maxCollectCount = 0;

    public static Set<TreeNode> naryTreeClosestCommonAncestor(TreeNode root, Set<TreeNode> nodes) {
        if (root == null) {
            return new HashSet<>();
        }
        Set<TreeNode> collect = new HashSet<>();
        if (nodes.contains(root)) {
            collect.add(root);
            return collect;
        }
        for (TreeNode child : root.children) {
            collect.addAll(naryTreeClosestCommonAncestor(child, nodes));
        }

        if (collect.size() > maxCollectCount) {
            LCA = root;
            maxCollectCount = collect.size();
        }
        return collect;
    }

    // Intervals: startTime, endTime, value, 找最大叠加的value
    // Not streaming
    static class Interval {
        int startTime;
        int endTime;
        int value;

        public Interval(int startTime, int endTime, int value) {
            this.startTime = startTime;
            this.endTime = endTime;
            this.value = value;
        }
    }

    static class EndPoint {
        int value;
        int time;
        boolean isEnd;

        public EndPoint(int value, int time, boolean isEnd) {
            this.value = value;
            this.time = time;
            this.isEnd = isEnd;
        }
    }

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
            Interval prevInterval = treeMap.get(treeMap.floorKey(startTime));
            if (prevInterval != null && prevInterval.endTime > startTime) {
                if (endTime <= prevInterval.endTime) {
                    // 思考：能不能不拆？
                    // 拆成三段不overlap的
                } else {
                    // 拆成三段不overlap的
                }
                maxValue = prevInterval.value + value > maxValue ? prevInterval.value + value : maxValue;
            }
            Interval nextInterval = treeMap.get(treeMap.ceilingKey(startTime));
            while (nextInterval != null && nextInterval.startTime < endTime) {
                // 拆成三段不overlap的
            }
            // update max
        }
        return 0;
    }

    private Interval getNext() {
        return null;
    }

    private boolean hasNext() {
        return false;
    }

    static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
            this.next = null;
        }
    }

    //static int lastN = 0;
    public static ListNode removeNthLastNodeRecursion(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        removeNthLastNode(dummy, n, new int[]{0});

        return dummy.next;
        // return listnode是因为如果head被删了 需要一个新的head
    }

    private static void removeNthLastNode(ListNode head, int n, int[] lastN) {
        if (head == null) {
            return;
        }
        removeNthLastNode(head.next, n, lastN);
        lastN[0] += 1;
        if (lastN[0] == n + 1) {
            ListNode removedNode = head.next;
            head.next = removedNode.next;
            removedNode.next = null;
        }
    }

    public static ListNode removeNthLastNodeTwoPointers(ListNode head, int n) {
        if (head == null) {
            return null;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode slow = dummy;
        ListNode fast = dummy;
        while (n-- > 0 && fast != null) {
            fast = fast.next;
        }
        if(fast == null) {
            throw new IllegalArgumentException("n is too large.");
        }
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        ListNode removedNode = slow.next;
        slow.next = removedNode.next;
        removedNode.next = null;

        return dummy.next;
    }


    public static void main(String[] args) {
        // TEST  LCA
        TreeNode n0 = new TreeNode(0);
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        n0.children.add(n1);
        n1.children.add(n2);
        n1.children.add(n4);
        n2.children.add(n3);
        Set<TreeNode> nodes = new HashSet<>();
        nodes.add(n2);
        nodes.add(n3);
        nodes.add(n4);
        naryTreeClosestCommonAncestor(n0, nodes);
        //System.out.println(LCA.value);

        // TEST list remove n
        ListNode head = new ListNode(1);
        ListNode ln2 = new ListNode(2);
        head.next = ln2;
        ListNode ln3 = new ListNode(3);
        ln2.next = ln3;
        ListNode ln4 = new ListNode(4);
        ln3.next = ln4;
        ListNode ln5 = new ListNode(5);
        ln4.next = ln5;

        ListNode cur = removeNthLastNodeTwoPointers(head, 5);
        while (cur != null) {
            System.out.println(cur.val);
            cur = cur.next;
        }

    }
}
