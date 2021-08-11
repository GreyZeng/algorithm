package leetcode;


// 平衡二叉树要么是一棵空树. 要么保证左右子树的高度之差不大于 1. 子树也必须是一颗平衡二叉树
public class LeetCode_0110_BalancedBinaryTree {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }

    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        return process(root).isB;
    }

    private Info process(TreeNode root) {
        if (root == null) {
            return new Info(0, true);
        }
        Info left = process(root.left);
        Info right = process(root.right);
        return new Info(Math.max(left.height, right.height) + 1, left.isB && right.isB && (Math.abs(left.height - right.height) <= 1));
    }

    public class Info {
        public int height;
        public boolean isB;

        public Info(int h, boolean isB) {
            height = h;
            this.isB = isB;
        }
    }
}
