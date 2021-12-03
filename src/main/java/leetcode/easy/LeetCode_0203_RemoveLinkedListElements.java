//Remove all elements from a linked list of integers that have value val.
//
//Example:
//
//Input:  1->2->6->3->4->5->6, val = 6
//Output: 1->2->3->4->5
package leetcode.easy;

public class LeetCode_0203_RemoveLinkedListElements {
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

	public static ListNode removeElements(ListNode head, int val) {
		while (head != null && head.val == val) {
			head = head.next;
		}
		if (head == null) {
			return null;
		}
		ListNode pre = head;
		ListNode cur = head.next;
		while (cur != null) {
			if (cur.val == val) {
				cur = cur.next;
				pre.next = cur;
			} else {
				pre = cur;
				cur = cur.next;
			}
		}
		return head;
		
	}
}
