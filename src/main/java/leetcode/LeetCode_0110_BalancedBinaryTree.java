package leetcode;

public class LeetCode_0110_BalancedBinaryTree {

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

	public static boolean isBalanced(TreeNode root) {
		if (root == null) {
			return true;
		}
		return p(root).isBalanced;
	}

	private static Info p(TreeNode root) {
		if (root == null) {
			return new Info(0, true);
		}
		Info left = p(root.left);
		Info right = p(root.right);
		return new Info(Math.max(left.height, right.height) + 1,
				left.isBalanced && right.isBalanced && Math.abs(left.height - right.height) <= 1);
	}

	public static class Info {
		public int height;
		public boolean isBalanced;

		public Info(int height, boolean isBalanced) {
			this.height = height;
			this.isBalanced = isBalanced;
		}
	}

}
