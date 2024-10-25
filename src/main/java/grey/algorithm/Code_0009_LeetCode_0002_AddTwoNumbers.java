package grey.algorithm;

// 给你两个非空 的链表，表示两个非负的整数。它们每位数字都是按照逆序的方式存储的，并且每个节点只能存储一位数字。
// 请你将两个数相加，并以相同形式返回一个表示和的链表。
// 你可以假设除了数字 0 之外，这两个数都不会以 0开头。
// https://leetcode.com/problems/add-two-numbers
// https://www.lintcode.com/problem/167/
// 注意是从左往右加，最右侧要注意进位信息
// 笔记：https://www.cnblogs.com/greyzeng/p/16629407.html
public class Code_0009_LeetCode_0002_AddTwoNumbers {

	// l1 和 l2 非空
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		if (l1 == null || l2 == null) {
			return l1 == null ? l2 : l1;
		}
		ListNode newHead = new ListNode((l1.val + l2.val) % 10);
		ListNode cur = newHead;
		// 进位
		int carry = (l1.val + l2.val) >= 10 ? 1 : 0;
		l1 = l1.next;
		l2 = l2.next;
		while (l1 != null && l2 != null) {
			ListNode next = new ListNode((l1.val + l2.val + carry) % 10);
			carry = (l1.val + l2.val + carry) >= 10 ? 1 : 0;
			cur.next = next;
			cur = next;
			l1 = l1.next;
			l2 = l2.next;
		}
		while (l1 != null) {
			ListNode next = new ListNode((l1.val + carry) % 10);
			carry = (l1.val + carry) >= 10 ? 1 : 0;
			cur.next = next;
			cur = next;
			l1 = l1.next;
		}
		while (l2 != null) {
			ListNode next = new ListNode((l2.val + carry) % 10);
			carry = (l2.val + carry) >= 10 ? 1 : 0;
			cur.next = next;
			cur = next;
			l2 = l2.next;
		}
		if (carry != 0) {
			cur.next = new ListNode(carry);
			cur = cur.next;
		}
		return newHead;
	}

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
}
