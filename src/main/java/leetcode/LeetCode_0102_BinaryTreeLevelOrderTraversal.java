package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


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
		List<List<Integer>> ans = new ArrayList<>();
		if (root == null) {
			return ans;
		}
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		TreeNode curEnd = root;
		TreeNode nextEnd = null;
		List<Integer> item = new ArrayList<>();
		while (!queue.isEmpty()) {
			TreeNode c = queue.poll();
			if (c.left != null) {
				queue.offer(c.left);
				nextEnd = c.left;
			}
			if (c.right != null) {
				queue.offer(c.right);
				nextEnd = c.right;
			}
			item.add(c.val);
			if (c == curEnd) {
				ans.add(item);
				item = new ArrayList<>();
				curEnd = nextEnd;
			}
		}
		return ans; 
	}
}
