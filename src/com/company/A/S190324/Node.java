package com.company.A.S190324;

import java.util.*;

public abstract class Node {
    protected String name;
    protected boolean[] permission;
    protected Node parent;
    protected final Date created;
    protected Date modified;

    protected Node(String n, Node p) {
        name = n;
        parent = p;
        created = new Date(); // new Date()
        //permission = new boolean[]{true, true, true};
// 这个地方有问题，System.currentTimeMilis() 的返回值是一个long不是Date，这里可以直接new Date()， Date的default constructor 会将新创建的Date变量初始化为当前系统时间
    }

    public Date getCreated() {
        return created;
    }

    public Date getModified() {
        return modified;
    }

    public Date setModified(Date time) {
        modified = time;
        return modified;
    }

    public void reName(String n) {
        name = n;
    }

    protected abstract long getSize(); // // 所有derived class必须实现，并且实现不一样

    public String getCurrentPath() {
        if (parent == null) return "";
        return parent.getCurrentPath() + "/" + name;
    }

}

