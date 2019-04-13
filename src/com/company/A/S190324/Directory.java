package com.company.A.S190324;

import java.util.*;

public class Directory extends Node {
    private List<Node> content;

    public Directory(String n, Node p, boolean[] pm) {
        super(n, p);
        permission = pm; //protected
        content = new ArrayList<Node>();
    }

    @Override
    public long getSize() { // 必须实现
        long size = 0;
        for (Node n : content) {
            size += n.getSize(); // recursion return case1 leaf file case2 leaf empty
        }
        return size;
    }

    public void addNode(Node node) {
        content.add(node);
    }

    public void deleteDir(String name) {
        // remove node
        // content.remove(node);
    }

    public int filesNum() {
        int count = 0;
        for (Node child : content) {
            if (child instanceof Directory) {
                Directory d = (Directory) child;
                count += d.filesNum();
            } else {
                count++;
            }
        }
        return count;
    }

    public List<Node> getContent() {
        return this.content;
    }

    public void setContent(List<Node> content) {
        this.content = content;
    }
}
