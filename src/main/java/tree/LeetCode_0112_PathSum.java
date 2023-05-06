package tree;

// https://leetcode.com/problems/path-sum
public class LeetCode_0112_PathSum {

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
    // TODO
    public boolean hasPathSum(TreeNode root, int targetSum) {
        return true;
    }
}
