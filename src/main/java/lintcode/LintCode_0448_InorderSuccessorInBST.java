package lintcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// leetcode 285
// https://www.lintcode.com/problem/inorder-successor-in-bst/description
public class LintCode_0448_InorderSuccessorInBST {
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	public static TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
		if (p == null) {
			return null;
		}
		if (p.right != null) {
			return rightLeftMost(p.right);
		}
		TreeNode sucessor = null;
		while (root != null) {
			if (root.val > p.val) {
				sucessor = root;
				root = root.left;
			} else if (root.val < p.val) {
				root = root.right;
			} else {
				break;
			}
		}
		return sucessor;
	}

	private static TreeNode rightLeftMost(TreeNode p) {
		while (p.left != null) {
			p = p.left;
		}
		return p;
	}

	public static TreeNode inorderSuccessor2(TreeNode root, TreeNode p) {
		List<TreeNode> ans = new ArrayList<>();
		if (root == null) {
			return null;
		}
		in2(root, ans);
		boolean find = false;
		for (TreeNode c : ans) {
			if (c == p) {
				find = true;
			} else if (find) {
				return c;
			}
		}
		return null;
	}

	private static void in2(TreeNode root, List<TreeNode> ans) {
		if (root == null) {
			return;
		}
		in2(root.left, ans);
		ans.add(root);
		in2(root.right, ans);
	}

	public TreeNode inorderSuccessor3(TreeNode root, TreeNode p) {
		if (root == null) {
			return null;
		}
		boolean flag = false;
		Stack<TreeNode> stack = new Stack<>();
		TreeNode cur = root;
		while (!stack.isEmpty() || cur != null) {
			if (cur != null) {
				stack.push(cur);
				cur = cur.left;
			} else {
				cur = stack.pop();
				if (cur == p) {
					flag = true;
				} else if (flag) {
					return cur;
				}
				cur = cur.right;
			}
		}
		return null;
	}
}
