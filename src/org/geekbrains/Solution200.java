package org.geekbrains;

import java.net.StandardSocketOptions;

public class Solution200 {
    public static void main(String[] args) {
        int[][] grid = load();
        print(grid);
        //System.out.print(getCountIslands(grid));
        System.out.print(getMaxSquareIsland(grid));
    }

    public static int[][] load() {
        int[][] grid = {{0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}};
        return grid;
    }

    public static int getCountIslands(int[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    dfs(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    public static void dfs(int[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[i].length || grid[i][j] != 1) {
            return;
        }

        grid[i][j] = -1;
        dfs(grid, i - 1, j);
        dfs(grid, i + 1, j);
        dfs(grid, i, j - 1);
        dfs(grid, i, j + 1);
    }

    public static int getMaxSquareIsland(int[][] grid) {
        int maxSquare = 0;
        int currentSquare = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    currentSquare = getSquareIsland(grid, i, j);
                    if (currentSquare > maxSquare) {
                        maxSquare = currentSquare;
                    }
                }
            }
        }
        return maxSquare;
    }

    public static int getSquareIsland(int[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[i].length || grid[i][j] != 1) {
            return 0;
        }

        int square = 1;

        grid[i][j] = -1;
        square += getSquareIsland(grid, i - 1, j);
        square += getSquareIsland(grid, i + 1, j);
        square += getSquareIsland(grid, i, j - 1);
        square += getSquareIsland(grid, i, j + 1);

        return square;
    }

    public static void print(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.print('\n');
        }
    }
}