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
package leetcode.medium;
 
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue; 


public class LeetCode_0107_BinaryTreeLevelOrderTraversalII {

	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
	}

	public List<List<Integer>> levelOrderBottom(TreeNode root) {
		List<List<Integer>> ans = new ArrayList<>();
		Deque<List<Integer>> stack = new ArrayDeque<>();
		if (root == null) {
			return ans;
		}
		TreeNode cur = root;
		TreeNode curEnd = cur;
		TreeNode nextEnd = null;
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(cur);
		List<Integer> item = new ArrayList<>();
		while (!queue.isEmpty() ) {
			cur = queue.poll();
			if (cur.left != null) {
				queue.offer(cur.left);
				nextEnd = cur.left;
			}
			if (cur.right != null) {
				queue.offer(cur.right);
				nextEnd = cur.right;
			}
			item.add(cur.val);
			if (cur == curEnd) {
				stack.push(item);
				item = new ArrayList<>();
				curEnd = nextEnd;
			}
		}
		while (!stack.isEmpty() ) {
			ans.add(stack.pop());
		}
		
		return ans;
	}
}
