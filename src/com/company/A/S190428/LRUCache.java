package com.company.A.S190428;

import java.util.*;
public class LRUCache {
        class ListNode{
            int key;
            int val;
            ListNode prev;
            ListNode next;
            ListNode(int key, int val){
                this.key = key;
                this.val = val;
                this.prev = null;
                this.next = null;
            }
        }
        private Map<Integer, ListNode> map;
        private ListNode head;
        private ListNode tail;
        private int capacity;
        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.map = new HashMap<>();
            this.head = new ListNode(0, 0);
            this.tail = new ListNode(0, 0);
            this.head.next = tail;
            this.tail.prev = head;
        }
        public int get(int key) {
            ListNode node = map.get(key);
            if(node == null) return -1;
            removeNode(node);
            insertFirst(node);
            return node.val;
        }

        public void put(int key, int value) {
            ListNode node = map.get(key);
            if(node != null){ // contains key
                node.val = value;
                removeNode(node);
                insertFirst(node);
            }
            else{
                if(map.size() == capacity){
                    ListNode removed = removeLast();
                    map.remove(removed.key);
                }

                ListNode newNode = new ListNode(key, value);
                insertFirst(newNode);
                map.put(key, newNode);

            }
        }
        private ListNode removeLast(){
            if(tail.prev == head) return null;
            ListNode ret = tail.prev;
            ret.prev.next = tail;
            tail.prev = ret.prev;
            return ret;
        }
        private ListNode insertFirst(ListNode node){
            if(node == null) return node;
            head.next.prev = node;
            node.next = head.next;
            head.next = node;
            node.prev = head;
            return node;
        }
        private ListNode removeNode(ListNode node){
            if(node == null) return node;
            node.prev.next = node.next;
            node.next.prev = node.prev;
            node.next = node.prev = null;
            return node;
        }
}
