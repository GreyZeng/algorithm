package leetcode;

//Given a binary tree, find the length of the longest path where each node in the path has the same value. This path may or may not pass through the root.
//
//        The length of path between two nodes is represented by the number of edges between them.
//
//
//
//        Example 1:
//
//        Input:
//
//        5
//        / \
//        4   5
//        / \   \
//        1   1   5
//        Output: 2
//
//
//
//        Example 2:
//
//        Input:
//
//        1
//        / \
//        4   5
//        / \   \
//        4   4   5
//        Output: 2
//
//
//
//        Note: The given binary tree has not more than 10000 nodes. The height of the tree is not more than 1000.
public class LeetCode_0687_LongestUnivaluePath {
    public static class TreeNode {
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


    public static int longestUnivaluePath(TreeNode root) {
        return process(root).max;
    }

    public static class Info {
        public int max;
        public int len;

        public Info(int max, int len) {
            this.max = max;
            this.len = len;
        }
    }

    public static Info process(TreeNode root) {
        if (root == null) {
            return new Info(0, 0);
        }
        if (root.left == null && root.right == null) {
            return new Info(0, 0);
        }
        int max;  // 实际最大
        int len; // 向一侧扎到最深
        if (null == root.left) {
            Info right = process(root.right);
            if (root.right.val == root.val) {
                len = right.len + 1;
                max = Math.max(len, right.max);
            } else {
                len = 0;
                max = right.max;
            }
            return new Info(max, len);
        }

        if (null == root.right) {
            Info left = process(root.left);
            if (root.left.val == root.val) {
                len = left.len + 1;
                max = Math.max(left.max, len);
            } else {
                len = 0;
                max = left.max;
            }
            return new Info(max, len);
        }
        Info left = process(root.left);
        Info right = process(root.right);
        if (root.right.val == root.val && root.left.val == root.val) {
            len = Math.max(right.len, left.len) + 1;
            max = Math.max(Math.max(right.len + left.len + 2, right.max), left.max);
        } else if (root.left.val == root.val) {
            len = left.len + 1;
            max = Math.max(Math.max(len, right.max), left.max);
        } else if (root.right.val == root.val) {
            len = right.len + 1;
            max = Math.max(Math.max(len, right.max), left.max);
        } else {
            len = 0;
            max = Math.max(right.max, left.max);
        }
        return new Info(max, len);
    }

}
