package grey.algorithm;

// 链表上实现 Partition（注：要保持相对顺序）
// https://leetcode.com/problems/partition-list/
// https://www.lintcode.com/problem/96/
// 笔记：https://www.cnblogs.com/greyzeng/p/16923068.html
public class Code_0010_LintCode_0096_PartitionList {

	// 仅做Partition
	// 注：要保持相对秩序
	public ListNode partition(ListNode head, int x) {
		ListNode sH = null;
		ListNode sT = null;
		ListNode bH = null;
		ListNode bT = null;
		ListNode cur = head;
		while (cur != null) {
			if (cur.val < x) {
				if (sH == null) {
					sH = cur;
					sT = cur;
				} else {
					sT.next = cur;
					sT = sT.next;
				}
			} else {
				// cur.val >= x
				if (bH == null) {
					bH = cur;
					bT = cur;
				} else {
					bT.next = cur;
					bT = bT.next;
				}
			}
			cur = cur.next;
		}
		if (bT != null) {
			//  断链，防止形成环
			bT.next = null;
		}
		if (sH != null) {
			sT.next = bH;
			return sH;
		}
		return bH;
	}

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
}
