package leetcode;

public class LeetCode_0101_SymmetricTree {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }

    public boolean isSymmetric(TreeNode root) {
        return isSymmetric(root, root);
    }

    private boolean isSymmetric(TreeNode r1, TreeNode r2) {
        if (r1 == null) {
            return r2 == null;
        }
        if (r2 == null) {
            return false;
        }
        return r1.val == r2.val && isSymmetric(r1.left, r2.right) && isSymmetric(r1.right, r2.left);
    }

}
