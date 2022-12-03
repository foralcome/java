package org.geekbrains;

import java.util.ArrayList;
import java.util.List;


public class Solution257 {

    public static void main(String[] args) {
        //root =  0 1 2  3   4
        int[] data = {1, 2, 3, 0, 5};
        TreeNode root = new TreeNode();
        root = load(0, data);

        List<String> l = new ArrayList<>();
        l = binaryTreePaths(root);
        printListPath(l);
    }

    public static TreeNode load(int index, int[] data) {
        if (index > data.length) return null;

        TreeNode node = new TreeNode(data[index]);

        int indexLeft = index * 2 + 1;
        if (indexLeft < data.length) {
            node.left = null;
            if (data[indexLeft] != 0) {
                TreeNode nLeft = new TreeNode(data[indexLeft]);
                node.left = load(indexLeft, data);
            }
        }
        int indexRight = index * 2 + 2;
        if (indexRight < data.length) {
            node.right = null;
            if (data[indexRight] != 0) {
                TreeNode nRight = new TreeNode(data[indexRight]);
                node.right = load(indexRight, data);
            }
        }

        return node;
    }

    public static List<String> binaryTreePaths(TreeNode root) {
        List<String> l = new ArrayList<>();
        dfs(root, l, Integer.toString(root.val));
        return l;
    }

    private static void dfs(TreeNode node, List<String> l, String path) {
        if (node.left == null && node.right == null)
            l.add(path);

        if (node.left != null) {
            dfs(node.left, l, path + "->" + Integer.toString(node.left.val));
        }
        if (node.right != null) {
            dfs(node.right, l, path + "->" + Integer.toString(node.right.val));
        }
    }

    public static void printListPath(List<String> l){
        for (String s : l){
            System.out.println(s);
        }
    }
}
