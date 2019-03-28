package com.company.A;

import java.util.*;

public class S190222 {
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
        public boolean equals(TreeNode a, TreeNode b){
            return a.value == b.value;
        }
    }
    static TreeNode LCA = null;
    static int maxCollectCount = 0;
    public static Set<TreeNode> naryTreeClosestCommonAncestor(TreeNode root, Set<TreeNode> nodes) {
        if(root == null) {
            return new HashSet<>();
        }
        Set<TreeNode> collect = new HashSet<>();
        if(nodes.contains(root)) {
            collect.add(root);
            return collect;
        }
        for(TreeNode child: root.children) {
            collect.addAll(naryTreeClosestCommonAncestor(child, nodes));
        }

        if(collect.size() > maxCollectCount) {
            LCA = root;
            maxCollectCount = collect.size();
        }
        return collect;
    }


      public static void main(String[] args) {
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
          System.out.println(LCA.value);
      }
}
