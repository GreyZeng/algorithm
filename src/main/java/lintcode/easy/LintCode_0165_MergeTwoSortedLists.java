package lintcode.easy;

// https://www.lintcode.com/problem/165/
public class LintCode_0165_MergeTwoSortedLists {
	/**
	 * @param l1: ListNode l1 is the head of the linked list
	 * @param l2: ListNode l2 is the head of the linked list
	 * @return: ListNode head of linked list
	 */
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		if (l1 == null || l2 == null) {
			// 如果任何一个链表为空，那么直接返回另外一个链表即可
			return l1 == null ? l2 : l1;
		}
		// 谁小谁作为头
		ListNode head = l1.val > l2.val ? l2 : l1;
		// t1 和 t2 表示l1和l2下一个要遍历的位置
		ListNode t1 = head == l1 ? l1.next : l1;
		ListNode t2 = head == l2 ? l2.next : l2;
		ListNode cur = head;
		while (t1 != null || t2 != null) {
			if (t1 == null) {
				// l1链表已经到头，剩下只需要把l2链表接入进来即可
				cur.next = t2;
				t2 = t2.next;
				cur = cur.next;
				continue;
			}
			if (t2 == null) {
				// l2链表已经到头，剩下只需要把l2链表接入进来即可
				cur.next = t1;
				t1 = t1.next;
				cur = cur.next;
				continue;
			}
			// l1和l2都没有到头，那么谁小谁接入进来即可。
			if (t1.val > t2.val) {
				cur.next = t2;
				t2 = t2.next;
			} else {
				cur.next = t1;
				t1 = t1.next;
			}
			cur = cur.next;
		}
		return head;
	}

	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
			next = null;
		}
	}
}