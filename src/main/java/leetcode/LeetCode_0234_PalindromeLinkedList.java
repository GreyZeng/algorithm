package leetcode;

//Given a singly linked list, determine if it is a palindrome.
//
//        Example 1:
//
//        Input: 1->2
//        Output: false
//        Example 2:
//
//        Input: 1->2->2->1
//        Output: true
//        Follow up:
//        Could you do it in O(n) time and O(1) space?

import java.util.Stack;

public class LeetCode_0234_PalindromeLinkedList {
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

	// 修改原链表，空间O(1)
	public static boolean isPalindrome(ListNode head) {
		// 奇数来到中点位置
		// 偶数来到上中点位置
		if (head == null || head.next == null) {
			return true;
		}
		if (head.next.next == null) {
			return head.val == head.next.val;
		}

		// 链表有3个点或以上
		ListNode slow = head.next;
		ListNode fast = head.next.next;
		while (fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		ListNode rightHalfHead = slow.next;

		slow.next = null; // 断开成两个链表
		
		ListNode reverseRightHalfHead = reverse(rightHalfHead);
		ListNode backup = reverseRightHalfHead;
		slow = head;
		boolean result = true;
		ListNode midLast  = slow;
		while (slow != null && reverseRightHalfHead != null) {
			if (slow.val != reverseRightHalfHead.val) {
				result = false;
			}
			midLast = slow;
			slow = slow.next;
			reverseRightHalfHead = reverseRightHalfHead.next;
		}
		 
		// 恢复原链表
		while (midLast.next!=null) {
			midLast = midLast.next;
		}
		midLast.next = reverse(backup);
		
		
		return result;
	}

	public static ListNode reverse(ListNode head) {
		ListNode pre = null;
		ListNode cur = head;
		while (cur != null) {
			ListNode next = cur.next;
			cur.next = pre;
			pre = cur;
			cur = next;
		}
		return pre;
	}

	// 利用栈O(n/2)
	public static boolean isPalindrome2(ListNode head) {
		if (head == null || head.next == null) {
			return true;
		}
		if (head.next.next == null) {
			return head.val == head.next.val;
		}
		ListNode slow = head.next;
		ListNode fast = head.next.next;
		while (fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		slow = slow.next;
		fast = head;
		Stack<ListNode> stack = new Stack<>();
		while (slow != null) {
			stack.push(slow);
			slow = slow.next;
		}
		while (!stack.isEmpty()) {
			if (stack.pop().val != fast.val) {
				return false;
			}
			fast = fast.next;
		}
		return true;

	}

	// 利用栈O(n)
	public static boolean isPalindrome3(ListNode head) {
		Stack<ListNode> stack = new Stack<>();
		ListNode c = head;
		while (c != null) {
			stack.push(c);
			c = c.next;
		}
		c = head;
		while (c != null) {
			if (c.val != stack.pop().val) {
				return false;
			}
			c = c.next;
		}
		return true;
	}

}
