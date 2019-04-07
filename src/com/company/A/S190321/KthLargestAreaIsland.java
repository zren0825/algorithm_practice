package com.company.A.S190321;

import java.util.*;

public class KthLargestAreaIsland {
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
