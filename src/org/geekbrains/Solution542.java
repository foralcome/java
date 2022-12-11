package org.geekbrains;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution542 {
    static final int[][] DIRECTIONS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) {
        int[][] mat = new int[][]{{0, 0, 0}, {0, 1, 0}, {1, 1, 1}};
        int[][] res = new int[mat.length][mat[0].length];
        print(mat, "Исходная матрица");
        res = updateMatrix(mat);

        print(res, "Результат");
    }

    public static int[][] updateMatrix(int[][] mat) {
        Queue<int[]> q = new LinkedList<>();
        int[][] values = new int[mat.length][mat[0].length];
        boolean[][] wasHere = new boolean[mat.length][mat[0].length];

        for (int row = 0; row < mat.length; row++) {
            for (int col = 0; col < mat[0].length; col++) {
                if (mat[row][col] == 0) {
                    for (int[] direction : DIRECTIONS) {
                        int newRow = row + direction[0];
                        int newCol = col + direction[1];
                        if (newRow < 0 || newCol < 0 || newRow >= mat.length || newCol >= mat[0].length) {
                            continue;
                        }
                        if (mat[newRow][newCol] == 1 && wasHere[newRow][newCol] == false) {
                            values[newRow][newCol] = 1;
                            wasHere[newRow][newCol] = true;
                            q.add(new int[]{newRow, newCol});
                        }
                    }
                }
            }
        }

        while (!q.isEmpty()) {
            int[] curPoint = q.poll();
            int curRow = curPoint[0];
            int curCol = curPoint[1];
            for (int[] direction : DIRECTIONS) {
                int newRow = curRow + direction[0];
                int newCol = curCol + direction[1];
                if (newRow < 0 || newCol < 0 || newRow >= mat.length || newCol >= mat[0].length || mat[newRow][newCol] != 1) {
                    continue;
                }
                if (values[newRow][newCol] == 0 || values[newRow][newCol] > values[curRow][curCol] + 1) {
                    values[newRow][newCol] = values[curRow][curCol] + 1;
                    q.add(new int[]{newRow, newCol});
                }
            }
        }
        return values;
    }

    public static void print(int[][] mat, String title) {
        System.out.println(title + ":");
        for (int row = 0; row < mat.length; row++) {
            for (int col = 0; col < mat[0].length; col++) {
                if (col != 0) System.out.print(" ");
                System.out.print(mat[row][col]);
            }
            System.out.println();
        }
    }
}
