package tree;

// A height-balanced binary tree is a binary tree in which the depth of the two subtrees of every node never differs by more than one.
// 判断一棵树是否是平衡二叉树
// 平衡二叉树要么是一棵空树.
// 要么保证左右子树的高度之差不大于 1.
// 子树也必须是一颗平衡二叉树
// https://leetcode.com/problems/balanced-binary-tree/
// 笔记：https://www.cnblogs.com/greyzeng/p/16703346.html
public class LeetCode_0110_BalancedBinaryTree {

    public class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public boolean isBalanced(TreeNode root) {
        return p(root).isBT;
    }
    public Info p(TreeNode root) {
        if (root == null) {
            return new Info(0,true);
        }
        Info left = p(root.left);
        Info right = p(root.right);
        int height = Math.max(left.height, right.height) + 1; 
        boolean isBT = left.isBT && right.isBT && Math.abs(left.height - right.height) <= 1;
         return new Info(height,isBT);
    }
    public class Info {
        public int height;
        public boolean isBT;
        public Info(int height, boolean isBT) {
            this.height = height;
            this.isBT = isBT;
        }
    }
    
}
