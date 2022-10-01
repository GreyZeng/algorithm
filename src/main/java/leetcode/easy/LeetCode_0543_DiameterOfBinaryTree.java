/*Given a binary tree, you need to compute the length of the diameter of the tree.
The diameter of a binary tree is the length of the longest path between any two nodes in a tree.
This path may or may not pass through the root.

		Example:
		Given a binary tree
		1
		/ \
		2   3
		/ \
		4   5
		Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].

		Note: The length of path between two nodes is represented by the number of edges between them.*/
package leetcode.easy;

public class LeetCode_0543_DiameterOfBinaryTree {

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
    }

    
    public static int diameterOfBinaryTree(TreeNode head) {
        if (head == null) {
            return 0;
        }
        return process(head).max;
    }

    public static class Info {
        public int maxHeight; // 从当前节点插到最底部最大高度
        public int max; // 当前树的最大距离

        public Info(int max, int maxHeight) {
            this.max = max;
            this.maxHeight = maxHeight;
        }
    }

    private static Info process(TreeNode head) {
        if (head == null) {
            return new Info(0, 0);
        }
        Info left = process(head.left);
        Info right = process(head.right);
        int max = Math.max(left.maxHeight + right.maxHeight, Math.max(left.max, right.max));
        int maxHeight = Math.max(left.maxHeight, right.maxHeight) + 1;
        return new Info(max, maxHeight);
    }

}
