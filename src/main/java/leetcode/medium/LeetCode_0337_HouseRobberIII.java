// The thief has found himself a new place for his thievery again.
// There is only one entrance to this area, called the "root." 
// Besides the root, each house has one and only one parent house. 
// After a tour, the smart thief realized that "all houses in this place forms a binary tree". 
// It will automatically contact the police if two directly-linked houses were broken into on the same night.

// Determine the maximum amount of money the thief can rob tonight without alerting the police.

// Example 1:

// Input: [3,2,3,null,3,null,1]

//      3
//     / \
//    2   3
//     \   \ 
//      3   1

// Output: 7 
// Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
// Example 2:

// Input: [3,4,5,1,3,null,1]

//      3
//     / \
//    4   5
//   / \   \ 
//  1   3   1

// Output: 9
// Explanation: Maximum amount of money the thief can rob = 4 + 5 = 9.
package leetcode.medium;

public class LeetCode_0337_HouseRobberIII {
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

    public static class Info {
        public int y;
        public int n;

        public Info(int y, int n) {
            this.y = y;
            this.n = n;
        }
    }

    public static int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Info info = process(root);
        return Math.max(info.y, info.n);
    }

    private static Info process(TreeNode root) {
        if (root.left == null && root.right == null) {
            return new Info(root.val, 0);
        }
        Info left = null;
        if (root.left != null) {
            left = process(root.left);
        }
        Info right = null;
        if (root.right != null) {
            right = process(root.right);
        }
        if (left == null) {
            return new Info(root.val + right.n, Math.max(right.n, right.y));
        }
        if (right == null) {
            return new Info(root.val + left.n, Math.max(left.n, left.y));
        }
        int y = root.val + left.n + right.n;
        int n = Math.max(Math.max(Math.max(left.n + right.n, left.y + right.y), left.n + right.y), (left.y + right.n));
        return new Info(y, n);
    }
}
