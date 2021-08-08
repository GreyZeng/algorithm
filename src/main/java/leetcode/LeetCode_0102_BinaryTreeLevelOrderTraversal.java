package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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

//树的按层遍历
//1. hash表+LinkedList
//2. 仅用LinkedList
//3. 自定义队列 
public class LeetCode_0102_BinaryTreeLevelOrderTraversal {

	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
	}

	public static class MyNode {
		public TreeNode data;
		public MyNode next;

		public MyNode(TreeNode node) {
			data = node;
		}
	}

	public static class MyQueue {
		public MyNode front;
		public MyNode end;
		public int size;

		public MyQueue() {
			front = null;
			end = null;
		}

		public void offer(MyNode c) {
			size++;
			if (front == null) {
				front = c;
				end = c;
			} else {
				end.next = c;
				end = c;
			}
		}

		public boolean isEmpty() {
			return size == 0;
		}

		public MyNode poll() {
			size--;
			MyNode ans = front;
			front = front.next;
			ans.next = null;
			return ans;
		}

	}

	// 用自定义Queue
	public static List<List<Integer>> levelOrder(TreeNode root) {
		List<List<Integer>> ans = new ArrayList<>();
		if (root == null) {
			return ans;
		}
		MyNode head = new MyNode(root);
		MyQueue queue = new MyQueue();
		queue.offer(head);
		MyNode curEnd = head;
		MyNode nextEnd = null;
		List<Integer> item = new ArrayList<>();
		MyNode t = null;
		while (!queue.isEmpty()) {
			MyNode c = queue.poll();
			if (c.data.left != null) {
				t = new MyNode(c.data.left);
				queue.offer(t);
				nextEnd = t;
			}
			if (c.data.right != null) {
				t = new MyNode(c.data.right);
				queue.offer(t);
				nextEnd = t;
			}
			item.add(c.data.val);
			if (curEnd.data == c.data) {
				ans.add(item);
				item = new ArrayList<>();
				curEnd = nextEnd;
			}
		}
		return ans;
	}

	// 仅用LinkedList
	public static List<List<Integer>> levelOrder2(TreeNode root) {
		List<List<Integer>> ans = new ArrayList<>();
		if (root == null) {
			return ans;
		}
		Queue<TreeNode> queue = new LinkedList<>();
		List<Integer> item = new ArrayList<>();
		TreeNode curEnd = root;
		TreeNode nextEnd = null;
		TreeNode cur = root;
		queue.offer(cur);
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

		return ans;
	}

	// 用LinkedList和HashMap
	public static List<List<Integer>> levelOrder3(TreeNode root) {
		List<List<Integer>> ans = new ArrayList<>();
		if (root == null) {
			return ans;
		}
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		Map<TreeNode, Integer> map = new HashMap<>();
		map.put(root, 0);
		int currentLevel = 0;
		List<Integer> item = new ArrayList<>();
		while (!queue.isEmpty()) {
			TreeNode c = queue.poll();
			int level = map.get(c);
			if (c.left != null) {
				queue.offer(c.left);
				map.put(c.left, level + 1);
			}
			if (c.right != null) {
				queue.offer(c.right);
				map.put(c.right, level + 1);
			}

			if (level == currentLevel) {
				item.add(c.val);
			} else {
				ans.add(item);
				item = new ArrayList<>();
				item.add(c.val);
				currentLevel = level;
			}
		}
		ans.add(item);
		return ans;
	}

}
