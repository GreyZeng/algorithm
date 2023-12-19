package git.snippet.tree;
// 笔记：https://www.cnblogs.com/greyzeng/articles/16971977.html
// 判断一棵树是否是镜面树
// https://leetcode.com/problems/symmetric-tree
public class LeetCode_0101_SymmetricTree {
    public boolean isSymmetric(TreeNode root) {
        if (null == root) {
            return true;
        }
        if (root.left == null || root.right == null) {
            return root.left == null && root.right == null;
        }
        return root.left.val == root.right.val && isSymmetric(root.left, root.right);
    }

    // 判断 left 和 right 两棵树是否是镜面对称
    public boolean isSymmetric(TreeNode left, TreeNode right) {
        if (left == null || right == null) {
            return left == null && right == null;
        }
        // left.val == right.val
        return left.val == right.val && isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
    }


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
}
