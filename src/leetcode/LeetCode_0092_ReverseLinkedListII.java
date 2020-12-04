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
// 		System.out.println("tF is " + tF.val);
// 		System.out.println("tCur is " + tCur.val + " tCur next is " + tCur.next.val);
// 		System.out.println("tPre is " + tPre.val+ " tPre next is " + tPre.next.val);
// 		System.out.println("cur is " + cur.val+ " cur next is " + cur.next.val);
//		

		tCur.next = tPre;
		if (pre != null) {
			pre.next = tCur;
		} else {
			head = tCur;
		}
		cur.next = tF;
//		System.out.println("tF is " + tF.val);
// 		System.out.println("tCur is " + tCur.val + " tCur next is " + tCur.next.val);
// 		System.out.println("tPre is " + tPre.val+ " tPre next is " + tPre.next.val);
// 		System.out.println("cur is " + cur.val+ " cur next is " + cur.next.val);
//		
//		//pre.next = tCur;
//		//cur.next = tCur:;
		return head;

	}

	public ListNode reverseBetweenRecursive(ListNode head, int m, int n) {
		if (m == 1) {
			return reverseN(head, n);
		}
		head.next = reverseBetweenRecursive(head.next, m - 1, n - 1);
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