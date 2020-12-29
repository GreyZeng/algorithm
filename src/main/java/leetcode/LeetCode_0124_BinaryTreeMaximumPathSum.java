package leetcode;

//
//Given a non-empty binary tree, find the maximum path sum.
//
//		For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path must contain at least one node and does not need to go through the root.
//
//		Example 1:
//
//		Input: [1,2,3]
//
//		1
//		/ \
//		2   3
//
//		Output: 6
//		Example 2:
//
//		Input: [-10,9,20,null,null,15,7]
//
//		-10
//		/ \
//		9  20
//		/  \
//		15   7
//
//		Output: 42
// Constraints:

// The number of nodes in the tree is in the range [0, 3 * 10^4].
// -1000 <= Node.val <= 1000
// ref lintcode : https://www.lintcode.com/problem/binary-tree-maximum-path-sum/description
public class LeetCode_0124_BinaryTreeMaximumPathSum {

	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		public TreeNode(int v) {
			this.val = v;
		}
	}

	public static int maxPathSum(TreeNode root) {
		return process(root).max;
	}

	public static Info process(TreeNode root) {
		if (root == null) {
			return null;
		}
		Info left = process(root.left);
		Info right = process(root.right);
		if (left == null && right == null) {
			return new Info(root.val, root.val);
		}
		if (left == null) {
			int len = Math.max(root.val, root.val + right.len);
			int max = Math.max(right.max, len);
			return new Info(len, max);
		}
		if (right == null) {
			int len = Math.max(root.val, root.val + left.len);
			int max = Math.max(left.max, len);
			return new Info(len, max);
		}
		int len = Math.max(Math.max(root.val + right.len, root.val + left.len), root.val);
		int max = Math.max(root.val + left.len + right.len, Math.max(Math.max(left.max, right.max), len));
		return new Info(len, max);
	}

	public static class Info {
		public int len;
		public int max;

		public Info(int l, int m) {
			len = l;
			max = m;
		}
	}
}
