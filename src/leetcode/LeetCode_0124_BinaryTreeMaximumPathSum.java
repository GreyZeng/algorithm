package leetcode;


//
//Given a non-empty binary tree, find the maximum path sum.
//
//		For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path must contain at least one node and does not need to go through the root.
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
        return process(root).max;
    }

    public static Info process(TreeNode root) {
        if (root == null) {
            return null;
        }

        if (root.left == null && root.right == null) {
            return new Info(root.val, root.val);
        }
        int max;
        int len;
        if (root.left == null) {
            Info right = process(root.right);
            len = root.val + right.len;
            len = Math.max(len, root.val);
            max = Math.max(right.max, len);
            return new Info(max, len);
        }
        if (root.right == null) {
            Info left = process(root.left);
            len = root.val + left.len;
            len = Math.max(len, root.val);
            max = Math.max(left.max, len);
            return new Info(max, len);
        }
        Info right = process(root.right);
        Info left = process(root.left);


        len = Math.max(root.val, root.val + left.len);
        len = Math.max(len, root.val + right.len);
        max = Math.max(Math.max(len, right.max), left.max);
        max = Math.max(max, root.val + left.len + right.len);
        return new Info(max, len);

    }

    public static class Info {
        public int max;
        public int len;

        public Info(int max, int len) {
            this.max = max;
            this.len = len;
        }
    }


}
