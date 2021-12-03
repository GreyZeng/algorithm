package leetcode;

// ref:https://www.lintcode.com/problem/maximum-depth-of-binary-tree/description
public class LeetCode_0104_MaximumDepthOfBinaryTree {

	/*
	 * 注意最小高度比这个复杂，要额外小心判断空
	 * */
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
	}
	
	// morris遍历可以更优化
	public static int maxDepth(TreeNode root) {
		if (root == null) {
			return 0;
		}
		if (root.left == null && root.right == null) {
			return 1;
		}
		return Math.max(maxDepth(root.left),maxDepth(root.right))+1; 
	}
	public static int maxDepth1(TreeNode root) {
		return process(root).max;
	}
	public static Info process(TreeNode root) {
		if (root == null) {
			return new Info(0);
		}
		Info left = process(root.left);
		Info right = process(root.right);
		return new Info(Math.max(right.max, left.max) + 1);
	}
	public static class Info {
		public int max;
		public Info(int m) {
			max = m;
		}
	}
	// by morris
	public static int maxDepth2(TreeNode head) {
		if (head == null) {
			return 0;
		}
		TreeNode cur = head;
		TreeNode mostRight;
		int curLevel = 0;
		int maxHeight = Integer.MIN_VALUE;
		while (cur != null) {
			mostRight = cur.left;
			if (mostRight != null) {
				int rightBoardSize = 1;
				while (mostRight.right != null && mostRight.right != cur) {
					rightBoardSize++;
					mostRight = mostRight.right;
				}
				if (mostRight.right == null) { // 第一次到达
					curLevel++;
					mostRight.right = cur;
					cur = cur.left;
					continue;
				} else { // 第二次到达
					if (mostRight.left == null) {
						maxHeight = Math.max(maxHeight, curLevel);
					}
					curLevel -= rightBoardSize;
					mostRight.right = null;
				}
			} else { // 只有一次到达
				curLevel++;
			}
			cur = cur.right;
		}
		int finalRight = 1;
		cur = head;
		while (cur.right != null) {
			finalRight++;
			cur = cur.right;
		}
		if (cur.left == null && cur.right == null) {
			maxHeight = Math.max(maxHeight, finalRight);
		}
		return maxHeight;
	}

}
