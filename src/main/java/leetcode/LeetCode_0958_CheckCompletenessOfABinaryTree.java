package leetcode;

public class LeetCode_0958_CheckCompletenessOfABinaryTree {

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

	public static class Info {
		public boolean isFull;
		public boolean isCBT;
		public int height;

		public Info(int height, boolean isFull, boolean isCBT) {
			this.height = height;
			this.isCBT = isCBT;
			this.isFull = isFull;
		}
	}

	public static boolean isCompleteTree(TreeNode root) {
		if (null == root) {
			return true;
		}
		return p(root).isCBT;
	}

	public static Info p(TreeNode head) {
		if (head == null) {
			return new Info(0, true, true);
		}
		Info left = p(head.left);
		Info right = p(head.right);
		boolean isFull = false;
		boolean isCBT = false;
		int height = 0;
		isFull = left.isFull && right.isFull && left.height == right.height;
		isCBT = (left.isFull && right.isCBT && left.height == right.height)
				|| (left.isFull && right.isFull && left.height - right.height <= 1 && left.height - right.height >= 0)
				|| (left.isCBT && right.isFull && left.height - right.height == 1);
		height = Math.max(left.height, right.height) + 1;
		return new Info(height,isFull, isCBT);
	}

}
