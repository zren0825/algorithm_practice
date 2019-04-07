package com.company.A.S190222;

class ListNode {
    int val;
    ListNode next;

    public ListNode(int val) {
        this.val = val;
        this.next = null;
    }
}

public class LinkedListRemoveLastNthNode {
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
        if (fast == null) {
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

//        ListNode cur = removeNthLastNodeTwoPointers(head, 5);
//        while (cur != null) {
//            System.out.println(cur.val);
//            cur = cur.next;
//        }

    }
}
