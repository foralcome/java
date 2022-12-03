package org.geekbrains;

import java.util.Vector;

public class Solution733 {
    public static void main(String[] args) {
        //int[][] image = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};
        //print(floodFill(image, 1, 1, 2));

        //int[][] image = {{0, 0, 0}, {0, 0, 0}};
        //print(floodFill(image, 0, 0, 0));

        int[][] image = {{0, 0, 0}, {0, 0, 0}};
        print(floodFill(image, 0, 0, 2));
    }

    public static int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int colorCheck = image[sr][sc];
        if (colorCheck == color)
            return image;
        return floodFillPixel(image, sr, sc, colorCheck, color);
    }

    public static int[][] floodFillPixel(int[][] image, int i, int j, int colorCheck, int colorFill) {
        //System.out.printf("[%d %d] check %d fill %d\n", i, j, colorCheck, colorFill);
        if (i < 0 || i >= image.length || j < 0 || j >= image[i].length || image[i][j] != colorCheck) {
            return image;
        }

        image[i][j] = colorFill;
        image = floodFillPixel(image, i - 1, j, colorCheck, colorFill);
        image = floodFillPixel(image, i + 1, j, colorCheck, colorFill);
        image = floodFillPixel(image, i, j - 1, colorCheck, colorFill);
        image = floodFillPixel(image, i, j + 1, colorCheck, colorFill);
        return image;
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
