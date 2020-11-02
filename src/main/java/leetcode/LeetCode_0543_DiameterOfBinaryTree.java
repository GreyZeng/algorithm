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
package leetcode;

public class LeetCode_0543_DiameterOfBinaryTree {

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
    }

    private static class Info {
        public int distance;
        public int height;

        public Info(int distance, int height) {
            this.distance = distance;
            this.height = height;
        }
    }

    public static int diameterOfBinaryTree(TreeNode root) {
        return process(root).distance;
    }

    private static Info process(TreeNode t) {
        if (t == null) {
            return new Info(0, 0);
        }
        Info left = process(t.left);
        Info right = process(t.right);
        int distance = Math.max(Math.max(left.distance, right.distance), left.height + right.height);
        int height = Math.max(right.height, left.height) + 1;
        return new Info(distance, height);
    }


}
