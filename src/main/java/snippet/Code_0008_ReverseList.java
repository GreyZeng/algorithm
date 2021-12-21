package snippet;

/**
 * 单链表反转（递归，非递归）
 * 
 * @see LeetCode_0206_ReverseLinkedList.java
 */
// 单链表部分反转（递归，非递归）
// 双向链表反转
public class Code_0008_ReverseList {
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

	// 反转链表的一部分
	public static ListNode reverseBetween(ListNode head, int m, int n) {
		if (m == n) {
			return head;
		}

		ListNode cur = head;
		ListNode pre = null;
		while (m != 1) {
			m--;
			n--;
			cur = cur.next;
			pre = pre == null ? head : pre.next;
		}
		ListNode tCur = cur;
		ListNode tF = cur.next;
		ListNode tPre = pre;
		while (n != 1) {
			n--;
			tF = tF.next;
			ListNode t = tCur.next;
			tCur.next = tPre;
			tPre = tCur;
			tCur = t;
		}

		tCur.next = tPre;
		if (pre != null) {
			pre.next = tCur;
		} else {
			head = tCur;
		}
		cur.next = tF;
		return head;
	}

	// 递归方式 反转链表的一部分
	public ListNode reverseBetweenRecursive(ListNode head, int m, int n) {
		if (m == 1) {
			return reverseN(head, n);
		}
		head.next = reverseBetweenRecursive(head.next, m + 1, n - 1);
		return head;
	}

	// 反转链表前N个节点
	ListNode successor = null;

	public ListNode reverseN(ListNode head, int n) {

		if (n == 1) {
			successor = head.next;
			return head;
		}
		ListNode last = reverseN(head.next, n - 1);
		head.next.next = head;
		head.next = successor;
		return last;

	}

}
