package tree;

//Given the root of a binary tree and an integer targetSum,
// return true if the tree has a root-to-leaf path such that adding up all the values along the path equals targetSum.
//        A leaf is a node with no children.
// https://leetcode.com/problems/path-sum
public class LeetCode_0112_PathSum {

    private static int sum = 0;
    private static boolean isSum = false;

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        sum = targetSum;
        isSum = false;
        process(root, 0);
        return isSum;
    }

    public void process(TreeNode root, int preSum) {
        if (root.left == null && root.right == null) {
            //  leaf node
            if (root.val + preSum == sum) {
                isSum = true;
            }
            return;
        }
        preSum += root.val;
        if (root.left != null) {
            process(root.left, preSum);
        }
        if (root.right != null) {
            process(root.right, preSum);
        }
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
