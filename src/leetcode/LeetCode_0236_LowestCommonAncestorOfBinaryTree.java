package leetcode;

public class LeetCode_0236_LowestCommonAncestorOfBinaryTree {
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	public static class Info {
		public boolean findO1;
		public boolean findO2;
		public TreeNode ancestor;

		public Info(boolean findO1, boolean findO2, TreeNode ancestor) {
			this.findO1 = findO1;
			this.findO2 = findO2;
			this.ancestor = ancestor;
		}
	}

	public static TreeNode lowestCommonAncestor(TreeNode head, TreeNode o1, TreeNode o2) {
		return p(head, o1, o2).ancestor;
	}

	public static Info p(TreeNode head, TreeNode o1, TreeNode o2) {
		if (head == null) {
			return new Info(false, false, null);
		}
		Info left = p(head.left, o1, o2);
		Info right = p(head.right, o1, o2);
		boolean findO1 = left.findO1 || right.findO1 || head == o1;
		boolean findO2 = left.findO2 || right.findO2 || head == o2;
		TreeNode ancestor = null;
		if (findO1 && findO2) {
			if (left.findO2 && left.findO1) {
				ancestor = left.ancestor;
			} else if (right.findO2 && right.findO1) {
				ancestor = right.ancestor;
			} else {
				ancestor = head;
			}
		}
		return new Info(findO1, findO2, ancestor);
	}

}
