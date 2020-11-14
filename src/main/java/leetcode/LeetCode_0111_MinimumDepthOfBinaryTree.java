package leetcode;

// 二叉树递归套路
// Morris遍历
public class LeetCode_0111_MinimumDepthOfBinaryTree {

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

	public static int minDepth(TreeNode head) {
		if (head == null) {
			return 0;
		}
		return p(head).minH;
	}

	public static Info p(TreeNode head) {
		if (head == null) {
			return new Info(0);
		}
		if (head.left == null && head.right == null) {
			return new Info(1);
		}
		if (head.left == null) {
			return new Info(p(head.right).minH + 1);
		}
		if (head.right == null) {
			return new Info(p(head.left).minH + 1);
		}
		return new Info(Math.min(p(head.left).minH, p(head.right).minH) + 1);
	}

	public static class Info {
		public int minH;

		public Info(int minH) {
			this.minH = minH;
		}
	}
}
