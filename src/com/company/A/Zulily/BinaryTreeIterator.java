package com.company.A.Zulily;

import java.util.*;

import java.util.Iterator;

class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int val) {
        this.val = val;
    }

}

public class BinaryTreeIterator implements Iterator<TreeNode> {

    Stack<TreeNode> stack;

    BinaryTreeIterator(TreeNode root) {
        stack = new Stack<>();
        pushLefts(root);
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    @Override
    public TreeNode next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        TreeNode top = stack.pop();
        TreeNode cur = top.right;
        pushLefts(cur);
        return top;
    }

    private void pushLefts(TreeNode node) {
        TreeNode cur = node;
        while (cur != null) {
            stack.push(cur);
            cur = cur.left;
        }
    }

    public static void main(String[] args) {
        TreeNode n0 = new TreeNode(0);
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        n0.left = n1;
        n1.left = n2;
        n1.right = n4;
        n2.left = n3;
        Iterator<TreeNode> bti = new BinaryTreeIterator(n0);
        // 3 2 1 4 0
        while (bti.hasNext()) {
            System.out.println(bti.next().val);
        }
    }
}
