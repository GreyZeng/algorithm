package list;

// 单链表反转
// 笔记：https://www.cnblogs.com/greyzeng/p/16629407.html
// https://leetcode.com/problems/reverse-linked-list/
// Follow up: A linked list can be reversed either iteratively or recursively. Could you implement both?
public class LeetCode_0206_ReverseLinkedList {
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

	// 非递归版本
	public ListNode reverseList(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode pre = null;
		while (head != null) {
			ListNode t = head.next;
			head.next = pre;
			pre = head;
			head = t;
		}
		return pre;
	}

	// 递归版本
	public ListNode reverseList2(ListNode head) {
		return reverse(head);
	}

	// 反转head为头的链表，并把反转后的头节点返回
	public ListNode reverse(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode nextHead = reverse(head.next);
		head.next.next = head;
		head.next = null;
		return nextHead;
	}
}