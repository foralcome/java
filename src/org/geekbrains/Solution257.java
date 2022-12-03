package org.geekbrains;

import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.List;

public class Solution257 {

    public static void main(String[] args) {
        List<Integer> data = new ArrayList<>();
    }

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> l = new ArrayList<>();
        dfs(root, l, Integer.toString(root.val));
        return l;
    }

    private void dfs(TreeNode node, List<String> l, String path) {
        if (node.left == null && node.right == null)
            l.add(path);

        if (node.left != null) {
            dfs(node.left, l, path + "->" + Integer.toString(node.left.val));
        }
        if (node.right != null) {
            dfs(node.right, l, path + "->" + Integer.toString(node.right.val));
        }
    }
}
