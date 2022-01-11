package leetcode.medium;

import java.util.LinkedList;

// 什么是完全二叉树：每一层都是满的，或者即便不满，也是从左到右依次变满的
public class LeetCode_0958_CheckCompletenessOfABinaryTree {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }

    // 暴力解法
    // 1. 某个节点没有左孩子但是有右孩子，一定不是完全二叉树
    // 2. 第一次出现左右孩子不双全的时候，接下来的节点都是叶子节点
    public boolean isCompleteTree0(TreeNode head) {
        if (head == null) {
            return true;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        // 是否遇到过左右两个孩子不双全的节点
        boolean leaf = false;
        TreeNode l = null;
        TreeNode r = null;
        queue.add(head);
        while (!queue.isEmpty()) {
            head = queue.poll();
            l = head.left;
            r = head.right;
            if (
            // 如果遇到了不双全的节点之后，又发现当前节点不是叶节点
            (leaf && (l != null || r != null)) || (l == null && r != null)

            ) {
                return false;
            }
            if (l != null) {
                queue.add(l);
            }
            if (r != null) {
                queue.add(r);
            }
            if (l == null || r == null) {
                leaf = true;
            }
        }
        return true;
    }

    // 二叉树的递归套路
    public boolean isCompleteTree(TreeNode root) {
        if (null == root) {
            return true;
        }
        return process(root).isCBT;
    }

    public Info process(TreeNode root) {
        if (root == null) {
            return new Info(true, true, 0);
        }
        Info left = process(root.left);
        Info right = process(root.right);
        int height = Math.max(left.height, right.height) + 1;
        boolean isCBT = (left.isFull && right.isFull
                && ((left.height - right.height) == 1 || left.height == right.height))
                || (left.isCBT && right.isFull && (left.height - right.height) == 1)
                || (left.isFull && right.isCBT && (left.height == right.height));
        boolean isFull = left.isFull && right.isFull && (right.height == left.height);
        return new Info(isCBT, isFull, height);
    }

    public static class Info {
        public boolean isCBT; // 是否完全二叉树
        public boolean isFull; // 是否满二叉树
        public int height; // 高度

        public Info(boolean isCBT, boolean isFull, int height) {
            this.isCBT = isCBT;
            this.isFull = isFull;
            this.height = height;
        }
    }

}
