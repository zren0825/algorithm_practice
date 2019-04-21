package com.company.A.S190418;

import java.util.*;

public class SolveMaze {
    // 0 means can pass, 1 means obstacle
    public boolean canSolveMazeDFS(int[][] maze, int[] start, int[] end) {
        // corner case
        return canSolveMazeDFS(maze, new boolean[maze.length][maze[0].length], end, start);
    }

    private boolean canSolveMazeDFS(int[][] maze, boolean[][] visited, int[] end, int[] cur) {
        int row = maze.length;
        int col = maze[0].length;

        if (cur[0] == end[0] && cur[1] == end[1]) {
            return true;
        }
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for (int[] dir : dirs) {
            int r = cur[0] + dir[0];
            int c = cur[1] + dir[1];
            if (r >= 0 && r < row && c >= 0 && c < col && maze[r][c] != 1 && !visited[r][c]) {
                visited[r][c] = true;
                if (canSolveMazeDFS(maze, visited, end, new int[]{r, c})) {
                    return true;
                }
                visited[r][c] = false;
            }
        }
        return false;
    }

    public boolean canSolveMazeBFS(int[][] maze, int[] start, int[] end) {
        // corner case
        int row = maze.length;
        int col = maze[0].length;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(start);
        boolean[][] visited = new boolean[row][col];
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (cur[0] == end[0] && cur[1] == end[1]) {
                return true;
            }
            for (int[] dir : dirs) {
                int r = cur[0] + dir[0];
                int c = cur[1] + dir[1];
                if (r >= 0 && r < row && c >= 0 && c < col && maze[r][c] != 1 && !visited[r][c]) {
                    visited[r][c] = true;
                    queue.offer(new int[]{r, c});
                }
            }
        }
        return false;
    }
}
