package lintcode;

public class LintCode_0103_LinkedListCycleII {
	public static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
			next = null;
		}
	}

	public static ListNode detectCycle(ListNode head) {
		if (head == null || head.next == null || head.next.next == null) {
			return null;
		}
		// 1. 快指针一次走两步，慢指针一次走一步
		// 2. 如果无环，快指针一定会走到空
		// 3. 如果有环，快指针和慢指针一定会在某处相遇。
		// 4. 相遇后，快指针回到原点，慢指针保持在原地
		// 5. 快慢指针同时每次走一步，一定在入环处相遇
		ListNode fast = head;
		ListNode slow = head;
		while (fast != null && fast.next != null) {
			fast = fast.next.next;
			slow = slow.next;
			if (fast == slow) {
				break;
			}
		}
		if (fast == null || fast.next == null) {
			return null;
		}
		if (fast == slow) {
			fast = head;
		}
		while (fast != slow) {
			fast = fast.next;
			slow = slow.next;
		}
		return fast;
	}

	public static void main(String[] args) {
		// 0->1->2->3->4->5->6->7->8->null
		ListNode head = new ListNode(0);
		head.next = head;
//		head.next = new ListNode(1);
//		head.next.next = new ListNode(2);
//		head.next.next.next = new ListNode(3);
//		head.next.next.next.next = new ListNode(4);
//		head.next.next.next.next.next = new ListNode(5);
//		head.next.next.next.next.next.next = new ListNode(6);
//		head.next.next.next.next.next.next.next = new ListNode(7);
//		head.next.next.next.next.next.next.next.next = new ListNode(8);
		ListNode c = detectCycle(head);
		System.out.println(c == null ? "null" : c.val);
	}
}
