package leetcode.hard;

//
//Given a non-empty binary tree, find the maximum path sum.
//
//		For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections.
//
//		The path must contain at least one node and does not need to go through the root.
//
//		Example 1:
//
//		Input: [1,2,3]
//
//		1
//		/ \
//		2   3
//
//		Output: 6
//		Example 2:
//
//		Input: [-10,9,20,null,null,15,7]
//
//		-10
//		/ \
//		9  20
//		/  \
//		15   7
//
//		Output: 42
// Constraints:

// The number of nodes in the tree is in the range [0, 3 * 10^4].
// -1000 <= Node.val <= 1000
// ref lintcode : https://www.lintcode.com/problem/binary-tree-maximum-path-sum/description
public class LeetCode_0124_BinaryTreeMaximumPathSum {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int v) {
            this.val = v;
        }
    }

    public static int maxPathSum(TreeNode root) {
        return p(root).max;
    }

    private static Info p(TreeNode head) {
        if (head == null) {
            return null;
        }
        Info left = p(head.left);
        Info right = p(head.right);
        if (left == null && right == null) {
            return new Info(head.val, head.val);
        }
        if (right == null) {
            // left != null
            int sideMaxSumContainsRoot = Math.max(head.val, left.sideMaxSumContainsRoot + head.val);
            int max = Math.max(sideMaxSumContainsRoot, left.max);
            return new Info(max, sideMaxSumContainsRoot);
        }
        if (left == null) {
            int sideMaxSumContainsRoot = Math.max(head.val, right.sideMaxSumContainsRoot + head.val);
            int max = Math.max(sideMaxSumContainsRoot, right.max);
            return new Info(max, sideMaxSumContainsRoot);
        }
        // left != null && right != null

        int sideMaxSumContainsRoot = Math.max(head.val, Math.max(head.val + left.sideMaxSumContainsRoot, head.val + right.sideMaxSumContainsRoot));
        int max = Math.max(head.val + right.sideMaxSumContainsRoot + left.sideMaxSumContainsRoot, Math.max(sideMaxSumContainsRoot, Math.max(right.max, left.max)));
        return new Info(max, sideMaxSumContainsRoot);
    }

    public static class Info {
        private final int max;
        private final int sideMaxSumContainsRoot; // 包含头节点最多能往一侧延伸的最大路径和

        public Info(int max, int sideMaxSumContainsRoot) {
            this.max = max;
            this.sideMaxSumContainsRoot = sideMaxSumContainsRoot;
        }
    }
}
