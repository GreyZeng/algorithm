package leetcode;

//Given a binary tree, determine if it is a valid binary search tree (BST).
//
//		Assume a BST is defined as follows:
//
//		The left subtree of a node contains only nodes with keys less than the node's key.
//		The right subtree of a node contains only nodes with keys greater than the node's key.
//		Both the left and right subtrees must also be binary search trees.
//
//
//		Example 1:
//
//		2
//		/ \
//		1   3
//
//		Input: [2,1,3]
//		Output: true
//		Example 2:
//
//		5
//		/ \
//		1   4
//		/ \
//		3   6
//
//		Input: [5,1,4,null,null,3,6]
//		Output: false
//		Explanation: The root node's value is 5 but its right child's value is 4.
public class LeetCode_0098_ValidateBinarySearchTree {

	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
	}

	public static class Info {
		public int max;
		public int min;
		public boolean isBST;

		public Info(int max, int min, boolean isBST) {
			this.max = max;
			this.min = min;
			this.isBST = isBST;
		}

	}

	public boolean isValidBST(TreeNode head) {
		if (head == null) {
			return true;
		}
		return p(head).isBST;
	}

	public static Info p(TreeNode head) {
		if (head == null) {
			return null;
		}
		int max = head.val;
		int min = head.val;
		Info left = p(head.left);
		if (null != left) {
			max = Math.max(left.max, max);
			min = Math.min(left.min, min);
		}
		Info right = p(head.right);
		if (right != null) {
			max = Math.max(right.max, max);
			min = Math.min(right.min, min);
		}
		boolean isBST = (left != null && left.max < head.val && right != null && right.min > head.val && right.isBST
				&& left.isBST) || (left == null && right != null && right.min > head.val && right.isBST)
				|| (left != null && left.max < head.val && right == null && left.isBST)
				|| (left == null && right == null);
		return new Info(max, min, isBST);
	}

	// morris遍历
	public boolean isValidBST2(TreeNode head) {
		if (head == null) {
			return true;
		}
		TreeNode cur = head;
		TreeNode mostRight;
		TreeNode pre = null;
		while (cur != null) {
			mostRight = cur.left;
			if (mostRight != null) {
				while (mostRight.right != null && mostRight.right != cur) {
					mostRight = mostRight.right;
				}
				if (mostRight.right == null) {
					mostRight.right = cur;
					cur = cur.left;
				} else {
					mostRight.right = null;
					if (pre != null && pre.val >= cur.val) {
						return false;
					}
					pre = cur;
					cur = cur.right;
				}
			} else {
				if (pre != null && pre.val >= cur.val) {
					return false;
				}
				pre = cur;
				cur = cur.right;
			}
		}
		return true;
	}

}
