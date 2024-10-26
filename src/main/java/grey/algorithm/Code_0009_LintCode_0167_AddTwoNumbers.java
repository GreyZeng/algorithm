package grey.algorithm;

// 给你两个非空 的链表，表示两个非负的整数。它们每位数字都是按照逆序的方式存储的，并且每个节点只能存储一位数字。
// 请你将两个数相加，并以相同形式返回一个表示和的链表。
// 你可以假设除了数字 0 之外，这两个数都不会以 0开头。
// https://leetcode.com/problems/add-two-numbers
// https://www.lintcode.com/problem/167/
// 注意是从左往右加，最右侧要注意进位信息
// 笔记：https://www.cnblogs.com/greyzeng/p/16629407.html
public class Code_0009_LintCode_0167_AddTwoNumbers {
	// 不额外开辟空间，复用 l1 和 l2 两个链表
    public ListNode addLists(ListNode l1, ListNode l2) {
		if (l1 == null || l2 == null) {
			return l1 == null ? l2 : l1;
		}
		int sum = l1.val + l2.val;
		int v = sum % 10;
		int carry = sum / 10;
		l1.val = v;
		ListNode head = l1; // 复用 l1 链表
        ListNode last = l1; // 用于记录求和链表的尾部
		l1 = l1.next;
		l2 = l2.next;
		while (l1 != null && l2 != null) {
			sum = l1.val + l2.val + carry;
			v = sum % 10;
			carry = sum / 10;
			l1.val = v;
			last = l1; // 记录一下最后一个位置
			l1 = l1.next;
			l2 = l2.next;
		}
		while (l1 != null) {
			// l2 没有了
			sum = l1.val + carry;
			v = sum % 10;
			carry = sum / 10;
			l1.val = v;
			last = l1; // 记录一下最后一个位置
			l1 = l1.next;
		}
		while (l2 != null) {
            // l1 没有了，接下来复用 l2 链表
			sum = l2.val + carry;
			v = sum % 10;
			carry = sum / 10;
			l2.val = v;
            last.next = l2;
            last = last.next;
			l2 = l2.next;
		}
		if (carry != 0) {
			last.next = new ListNode(carry);
		}
		return head;
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
