/*You are given the root of a binary search tree (BST), where exactly two nodes of the tree were swapped by mistake. Recover the tree without changing its structure.

        Follow up: A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?*/
package leetcode;

public class LeetCode_0099_RecoverBinarySearchTree {
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
    public void recoverTree(TreeNode root) {

    }
}
