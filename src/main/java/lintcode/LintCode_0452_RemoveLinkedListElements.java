package lintcode;

public class LintCode_0452_RemoveLinkedListElements {
	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
			next = null;
		}
	}

	public ListNode removeElements(ListNode head, int val) {
		ListNode t = head;
		while (t != null && t.val == val) {
			t = t.next;
		}
		if (t == null) {
			return null;
		}
		ListNode c = head;
		while (c.next != null) {
			if (c.next.val == val) {
				c.next = c.next.next;
			} else {
				c = c.next;
			}
		}
		return head;
	}

}
