package git.snippet.tree;
// 笔记：https://www.cnblogs.com/greyzeng/articles/15700243.html
// Given the root of a binary tree and an integer targetSum,
// return true if the tree has a root-to-leaf path such that adding up all the values along the path
// equals targetSum.
//        A leaf is a node with no children.
// https://leetcode.com/problems/path-sum
public class LeetCode_0112_PathSum {


    public boolean hasPathSum(TreeNode root, int targetSum) {
        return process(root, 0, targetSum);
    }

    // 从 某个节点到 叶子节点，是否可以累计得到某个值（targetSum), 之前的值是 preSum
    public boolean process(TreeNode node, int preSum, int target) {
        if (node == null) {
            return false;
        }
        if (node.left == null && node.right == null) {
            // node 是叶子节点
            return preSum + node.val == target;
        }
        return process(node.left, preSum + node.val, target) || process(node.right, preSum + node.val, target);
    }


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }
}
