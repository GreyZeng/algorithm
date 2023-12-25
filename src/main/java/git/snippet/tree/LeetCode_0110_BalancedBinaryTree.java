package git.snippet.tree;


// 判断一棵树是否是平衡二叉树
// 平衡二叉树要么是一棵空树.
// 要么保证左右子树的高度之差不大于 1.
// 子树也必须是一颗平衡二叉树
// https://leetcode.com/problems/balanced-binary-tree/
// 笔记：https://www.cnblogs.com/greyzeng/p/16703346.html
public class LeetCode_0110_BalancedBinaryTree {
    public boolean isBalanced(TreeNode root) {
        return process(root).isBST;
    }

    public Info process(TreeNode root) {
        if (root == null) {
            return new Info(0, true);
        }
        Info left = process(root.left);
        Info right = process(root.right);
        return new Info(Math.max(left.height, right.height) + 1, left.isBST && right.isBST && Math.abs(left.height - right.height) <= 1);
    }

    public class Info {
        public int height;
        public boolean isBST;

        public Info(int height, boolean isBST) {
            this.height = height;
            this.isBST = isBST;
        }
    }

    public class TreeNode {
        TreeNode left;
        TreeNode right;
    }
}
