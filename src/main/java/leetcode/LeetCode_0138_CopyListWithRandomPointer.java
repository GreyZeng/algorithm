package leetcode;

public class LeetCode_0138_CopyListWithRandomPointer {

	public static class Node {
		int val;
		Node next;
		Node random;

		public Node(int val) {
			this.val = val;
			this.next = null;
			this.random = null;
		}
	}

	public static Node copyRandomList(Node head) {
		if (null == head) {
			return null;
		}
		Node c = head;
		while (c != null) {
			Node next = c.next;
			c.next = new Node(c.val);
			c.next.next = next;
			c = next;
		}
		c = head;
		while (c != null && c.next != null) {
			c.next.random = c.random == null ? null : c.random.next;
			c = c.next.next;
		}
		c = head;
		Node ans = head.next;
		Node copy = ans;
		while (c.next.next != null) {
			c.next = c.next.next;
			c = c.next;
			copy.next = copy.next.next;
			copy = copy.next;
		}
		c.next = null; 
		return ans;
	}

}
