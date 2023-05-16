// Invert a binary tree.

// Example:

// Input:

// 4
// / \
// 2 7
// / \ / \
// 1 3 6 9
// Output:

// 4
// / \
// 7 2
// / \ / \
// 9 6 3 1
package leetcode;

// 翻转二叉树
// https://leetcode.cn/problems/invert-binary-tree/
public class LeetCode_0226_InvertBinaryTree {

    public class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
    }

    public static TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = root.left;
        root.left = invertTree(root.right);
        root.right = invertTree(left);
        return root;
    }
}
