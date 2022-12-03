package org.geekbrains;

import java.util.ArrayList;
import java.util.List;

public class Solution100 {
    public static void main(String[] args) {
        //root =  0 1 2  3   4
        int[] dataTreeP = {1, 2, 3, 0, 5};
        TreeNode rootTreeP = new TreeNode();
        rootTreeP = load(0, dataTreeP);

        int[] dataTreeQ = {1, 2, 3, 0, 5};
        TreeNode rootTreeQ = new TreeNode();
        rootTreeQ = load(0, dataTreeQ);

        System.out.println(isSameTree(rootTreeP, rootTreeQ));
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

    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p != null && q != null && p.val != q.val) return false;
        if ((p != null && q == null) || (p == null && q != null)) return false;

        boolean checkLeft = isSameTree(p.left, q.left);
        boolean checkRight = isSameTree(p.right, q.right);
        return checkLeft && checkRight;
    }
}
