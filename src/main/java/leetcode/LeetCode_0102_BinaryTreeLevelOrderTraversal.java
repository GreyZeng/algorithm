package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
//
//		For example:
//		Given binary tree [3,9,20,null,null,15,7],
//		3
//		/ \
//		9  20
//		/  \
//		15   7
//		return its level order traversal as:
//		[
//		[3],
//		[9,20],
//		[15,7]
//		]
public class LeetCode_0102_BinaryTreeLevelOrderTraversal {

	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
	}

	public static List<List<Integer>> levelOrder(TreeNode root) {
		if (root == null) {
			return new ArrayList<>();
		}
		LinkedList<TreeNode> queue = new LinkedList<>();
		List<List<Integer>> ans = new ArrayList<>();
		queue.add(root);
		int size = 1;
		int c = 0;
		while (!queue.isEmpty()) {
			size = queue.size();
			List<Integer> list = new ArrayList<>();
			for (c = 0; c < size; c++) {
				TreeNode cur = queue.pop();
				list.add(cur.val);
				if (cur.left != null) {
					queue.add(cur.left);
				}
				if (cur.right != null) {
					queue.add(cur.right);
				}
			}
			ans.add(list);
		}
		return ans;
	}
}
