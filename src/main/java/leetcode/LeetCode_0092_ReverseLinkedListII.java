// Reverse a linked list from position m to n. Do it in one-pass.

// Note: 1 ≤ m ≤ n ≤ length of list.

// Example:

// Input: 1->2->3->4->5->NULL, m = 2, n = 4
// Output: 1->4->3->2->5->NULL
package leetcode;

public class LeetCode_0092_ReverseLinkedListII {
	public static class ListNode {
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

	public static ListNode reverseBetween(ListNode head, int m, int n) {
		if (m == n) {
			// 不变
			return head;
		}
		ListNode startPre = null;
		ListNode start = null;
		ListNode end;
		ListNode endAfter = null;
		ListNode cur = head;
		int i = 1;
		while (i <= n) {
			if (i == m - 1) {
				startPre = cur;
			} else if (i == m) {
				start = cur;
			} else if (i == n) {
				end = cur;
				if (end.next != null) {
					endAfter = end.next;
					end.next = null;
				}
			}
			cur = cur.next;
			i++;
		}
		i = m;
		ListNode pre = null;
		// 反转链表操作
		while (i <= n) {
			ListNode tmp = start.next;
			start.next = pre;
			pre = start;
			start = tmp;
			if (i == m) {
				pre.next = endAfter;
			}
			i++;
		}
		// 换头了
		if (m == 1 && n != 1) {
			return pre;
		}
		startPre.next = pre;
		return head;
	}

	public static ListNode reverseBetweenRecursive(ListNode head, int m, int n) {
		if (m == 1) {
			return reverseN(head, n);
		}
		head.next = reverseBetweenRecursive(head.next, m - 1, n - 1);
		return head;
	}

	// 反转链表前N个节点
	static ListNode successor = null;

	public static ListNode reverseN(ListNode head, int n) {

		if (n == 1) {
			successor = head.next;
			return head;
		}
		ListNode last = reverseN(head.next, n - 1);
		head.next.next = head;
		head.next = successor;
		return last;

	}

	public static void main(String[] args) {
		testCase1();
		testCase2();
	}

	public static void testCase1() {
		System.out.println("begin test");
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(3);
		head.next.next.next = new ListNode(4);
		head.next.next.next.next = new ListNode(5);
		head.next.next.next.next.next = new ListNode(6);
		ListNode t = reverseBetween(head, 2, 4);
		while (t != null) {
			System.out.print(t.val + " ");
			t = t.next;
		}
		System.out.println("end test");
	}

	public static void testCase2() {
		System.out.println("begin test");
		ListNode head = new ListNode(3);
		head.next = new ListNode(5);

		ListNode t = reverseBetween(head, 1, 2);
		while (t != null) {
			System.out.print(t.val + " ");
			t = t.next;
		}
		System.out.println("end test");
	}
}