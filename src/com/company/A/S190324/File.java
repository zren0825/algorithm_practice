package com.company.A.S190324;

public class File extends Node {
    private String content;
    private long size;

    public File(String name, Node parent, long size, boolean[] pm) {
        super(name, parent);
        this.size = size;
        permission = pm; //protected
    }

    @Override
    public long getSize() {
        return size;
    }

    public String setContent(String content, long size) {
        this.size = size;
        this.content = content;
        return content;
    }

    public String getContent() {
        return this.content;
    }
}

