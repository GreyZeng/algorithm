//Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).
//
//For example:
//Given binary tree [3,9,20,null,null,15,7],
//    3
//   / \
//  9  20
//    /  \
//   15   7
//return its bottom-up level order traversal as:
//[
//  [15,7],
//  [9,20],
//  [3]
//]
package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;


public class LeetCode_0107_BinaryTreeLevelOrderTraversalII {

	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
	}

	public List<List<Integer>> levelOrderBottom(TreeNode root) {
		List<List<Integer>> ans = new ArrayList<>();
		Stack<List<Integer>> help = new Stack<>();
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
				help.push(item);
				item = new ArrayList<>();
				curEnd = nextEnd;
			}
		}
		while (!help.isEmpty()) {
			ans.add(help.pop());
		}
		return ans;
	}
}
