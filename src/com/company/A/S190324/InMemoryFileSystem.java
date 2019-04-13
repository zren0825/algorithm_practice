package com.company.A.S190324;

import java.util.*;


public class InMemoryFileSystem {
    // fields
    Node root;
    Node current;

    // methods
    public InMemoryFileSystem(String rootName) {
        root = new Directory(rootName, null, new boolean[]{true, true, true});
        root.parent = root;
        current = root;
    }


    public void mkdir(String path) {
        if (path == null || path.length() == 0)
            return;
        String[] dirs = path.split("/");
        Node cur = root;
        List<Node> contents = null;
        boolean flag = false;
        for (String dir : dirs) {
            // reset
            if (cur instanceof Directory) {
                contents = ((Directory) cur).getContent();
                for (Node child : contents) {
                    if (child instanceof Directory && child.name.equals(dir)) {
                        cur = child;
                        flag = true;
                        break; // must break after change cur otherwise, iterator
                    }
                }
                //mkdir
                if (!flag) {
                    Node newDir = new Directory(dir, cur, new boolean[]{true, true, true});
                    ((Directory) cur).addNode(newDir);
                    cur = newDir;
                    flag = false;
                }
            }
        }
    }

    public void cd(String path) {
        if (path == null || path.length() == 0)
            return;
        String[] dirs = path.split("/");
        Node cur = root;
        List<Node> contents = null;
        boolean flag = false;
        for (String dir : dirs) {
            // reset
            if (cur instanceof Directory) {
                contents = ((Directory) cur).getContent();
                for (Node child : contents) {
                    if (child instanceof Directory && child.name.equals(dir)) {
                        cur = child;
                        flag = true;
                        break; // must break after change cur otherwise, iterator
                    }
                } // else
                // CD
                if (!flag) {
                    throw new RuntimeException("No such file or directory.");
                }
            }
        }
        this.current = cur;
    }

    // delete(String path)
    public void removeDirectory(String path) {
        Node cachedCur = current;
        String[] tokens = path.split("/");
        String removedName = tokens[tokens.length - 1];
        tokens = Arrays.copyOfRange(tokens, 0, tokens.length - 1);
        String deletePath = "";
        for (String token : tokens) {
            deletePath += "/" + token;
        }
        cd(deletePath); //大概率不是这么做的
        ((Directory) current).deleteDir(removedName);
        current = cachedCur;
    }

    public List<File> getFilesSizeLargerThan(long size) {
        List<File> largeFiles = new LinkedList<>();
        getFilesSizeLargerThan(root, largeFiles, size);
        return largeFiles;
    }

    private void getFilesSizeLargerThan(Node cur, List<File> largeFiles, long size) {
        if (cur instanceof File && cur.getSize() > size) {
            largeFiles.add(((File) cur));
            return;
        }
        if (cur instanceof File) {
            return;
        }
        for (Node child : ((Directory) cur).getContent()) {
            getFilesSizeLargerThan(child, largeFiles, size);
        }
    }

    public List<File> getFilesByType(String surfix) {
        List<File> filesOfType = new LinkedList<>();
        getFilesByType(root, filesOfType, surfix);
        return filesOfType;
    }

    private void getFilesByType(Node cur, List<File> filesOfType, String suffix) {
        if (cur instanceof File && isFileWithSuffix((File) cur, suffix)) {
            filesOfType.add(((File) cur));
            return;
        }
        if (cur instanceof File) {
            return;
        }
        for (Node child : ((Directory) cur).getContent()) {
            getFilesByType(child, filesOfType, suffix);
        }
    }

    private static boolean isFileWithSuffix(File file, String suffix) {
//        Pattern pattern = Pattern.compile(".+\." + suffix + "($|\n)");
//        Matcher matcher = pattern.matcher(file.name);
//        return matcher.matches();
        String name = file.name;
        int nameLength = name.length();
        int suffixLength = suffix.length();
        return name.substring(nameLength - suffixLength, nameLength).equals(suffix);
    }

    public static void main(String[] args) {
        String name = "abc.xml";
        String suffix = "xml";
        int nameLength = name.length();
        int suffixLength = suffix.length();
        System.out.println(name.substring(nameLength - suffixLength, nameLength).equals(suffix));
    }
}

