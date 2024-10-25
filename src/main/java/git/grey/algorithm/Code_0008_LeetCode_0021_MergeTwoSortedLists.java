package git.grey.algorithm;

// merge two sorted lists
// leetcode: https://leetcode.com/problems/merge-two-sorted-lists
// lintcode: https://www.lintcode.com/problem/165/
// https://www.cnblogs.com/greyzeng/p/7551789.html
public class Code_0008_LeetCode_0021_MergeTwoSortedLists {

	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		if (l1 == null || l2 == null) {
			return l1 == null ? l2 : l1;
		}
		// 到这一步，l1和l2都不为空
		ListNode head = l1.val < l2.val ? l1 : l2;
		l1 = head == l1 ? l1.next : l1;
		l2 = head == l2 ? l2.next : l2;
		ListNode cur = head;
		while (l1 != null && l2 != null) {
			if (l1.val < l2.val) {
				cur.next = l1;
				cur = cur.next;
				l1 = l1.next;
			} else {
				cur.next = l2;
				cur = cur.next;
				l2 = l2.next;
			}
		}
		cur.next = l1 == null ? l2 : l1;
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
