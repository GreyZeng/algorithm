package leetcode;


// 本质是树的按层遍历
// 1. hash表+LinkedList
// 2. 仅用LinkedList
// 3. 自定义队列
// 本题有特殊要求：
//You may only use constant extra space.
//Recursive approach is fine, you may assume implicit stack space does not count as extra space for this problem.
// 所以可以采用自定义队列的方式

public class LeetCode_0116_PopulatingNextRightPointersInEachNode {
	public static class Node {
		public int val;
		public Node left;
		public Node right;
		public Node next;
	}

	public static class MyQueue {
		public Node head;
		public Node tail;
		public int size;

		public void offer(Node data) {
			size++;
			if(head == null) {
				head = data;
				tail = data;
			} else {
				tail.next = data;
				tail = data;
			}

		}

		public boolean isEmpty() {
			return size == 0;
		}

		public Node poll() {
			size--;
			Node ans = head;
			head = head.next;
			ans.next = null;
			return ans;
		}

	}

	public static Node connect(Node root) {
		// TODO
		return null;
	}

}
