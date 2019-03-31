package com.company.A;

import java.util.*;

public class S1903215 {
    /* --------------------------------------- 1 --------------------------------------- */
    // Number of island, 求面积第k大的岛，返回面积
    // BFS O(m*n*log(k))
    private static int findKthLargestIsland(int[][] grid, int k) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            throw new IllegalArgumentException();
        }
        int rows = grid.length;
        int cols = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(k);
        boolean[][] visited = new boolean[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    queue.offer(new int[]{i, j});
                    int area = markIslandAndGetArea(grid, queue, visited);
                    if (minHeap.size() < k) {
                        minHeap.offer(area);
                    } else if (area > minHeap.peek()) {
                        minHeap.poll();
                        minHeap.offer(area);
                    }
                }
            }
        }
        if (minHeap.isEmpty()) {
            return 0;
        }
        return minHeap.poll();
    }

    private static int markIslandAndGetArea(int[][] grid, Queue<int[]> queue, boolean[][] visited) {
        int rows = grid.length;
        int cols = grid[0].length;
        int area = 0;
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            area++;
            for (int[] dir : dirs) {
                int r = cur[0] + dir[0];
                int c = cur[1] + dir[1];
                if (r >= 0 && r < rows && c >= 0 && c < cols && grid[r][c] == 1 && !visited[r][c]) {
                    queue.offer(new int[]{r, c});
                    visited[r][c] = true;
                }
            }
        }
        return area;
    }

    /* --------------------------------------- 2 --------------------------------------- */
    // String getGenes(); 获得下一个DNA序列内容
    // String[] 里面的每个短DNA序列是不是在长DNA序列里面
    static class TrieNode {
        private TrieNode[] children;
        private String DNASeries;

        TrieNode(String DNASeries) {
            children = new TrieNode[26]; // A G T C
            this.DNASeries = DNASeries;
        }
    }

    private TrieNode root;

    private void insertDNASeries(String DNASeries) {
        TrieNode cur = root;
        for (char c : DNASeries.toCharArray()) {
            TrieNode next = cur.children[c - 'A'];
            if (next == null) {
                cur.children[c - 'A'] = new TrieNode(null);
            }
            cur = cur.children[c - 'A'];
        }
        cur.DNASeries = DNASeries;
    }

    public List<String> getAppearingDNASeries(String[] DNASeriesArray) {
        for (String DNASeries : DNASeriesArray) {
            insertDNASeries(DNASeries);
        }
        List<String> appearingDNASeries = new LinkedList<>();
        List<TrieNode> pointers = new ArrayList<>();
        while (hasNextGenes()) {
            char gene = getGenes();
            // 每次有新的streaming data 先加个pointer进去
            TrieNode newPointer = root;
            pointers.add(newPointer);
            // 对于list里面每个pointer, 看看能不能往下走一步
            for (int i = 0; i < pointers.size(); i++) {
                TrieNode curPointer = pointers.get(i);
                // 能走： 走一步，如果是一个series，加到result里
                if (curPointer.children[gene - 'A'] != null) {
                    curPointer = curPointer.children[gene - 'A'];
                    if (curPointer.DNASeries != null) {
                        appearingDNASeries.add(curPointer.DNASeries);

                    }
                    pointers.set(i, curPointer);
                    // 不能走： 直接从list里面remove
                } else {
                    pointers.remove(i--); // i - 1 因为删掉了一个
                }
            }
        }

        return appearingDNASeries;
    }

    private boolean hasNextGenes() {
        return true;
    }

    private char getGenes() {
        return 'A';
    }

    public static void main(String[] args) {
        List<TrieNode> pointers = new ArrayList<>();
        pointers.add(new TrieNode("a"));
        pointers.add(new TrieNode("b"));
        pointers.add(new TrieNode("c"));
        TrieNode curPointer = pointers.get(1); // b
        curPointer = pointers.get(2);
        System.out.println(pointers.get(1).DNASeries);
    }
}
