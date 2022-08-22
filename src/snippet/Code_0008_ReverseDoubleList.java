package snippet;

// 反转双向链表
public class Code_0008_ReverseDoubleList {
	public class ListNode {
		int val;
		ListNode next;

		ListNode() {
		}

		ListNode(int val) {
			this.val = val;
		}

		ListNode(int val, ListNode next) {
			this.val = val;
			this.next = next;
		}
	}

	// 双向链表反转
	public static class DoubleNode {
		public DoubleNode next;
		public DoubleNode last;
		public int value;

		public DoubleNode(int v) {
			value = v;
		}
	}

	public static DoubleNode reverse(DoubleNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		DoubleNode pre = null;
		DoubleNode t = null;
		while (head != null) {
			t = head.next;
			head.next = pre;
			pre = head;
			head = t;
			pre.last = head;
		}
		return pre;
	}
}
