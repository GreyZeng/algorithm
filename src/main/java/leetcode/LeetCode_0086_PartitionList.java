//Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
//
//You should preserve the original relative order of the nodes in each of the two partitions.
//
//Example:
//
//Input: head = 1->4->3->2->5->2, x = 3
//Output: 1->2->2->4->3->5
package leetcode;

public class LeetCode_0086_PartitionList {

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

	public static ListNode partition(ListNode head, int x) {

		ListNode ls = null;// 小于区域的头
		ListNode lt = null;// 小于区域的尾
		ListNode ms = null;// 大于区域的头
		ListNode mt = null;// 大于区域的尾
		ListNode c = head;
		while (c != null) {
			ListNode t = c.next;
			c.next = null;
			if (c.val < x) {
				if (ls == null) {
					ls = c;
					lt = c;
				} else {
					lt.next = c;
					lt = lt.next;
				}
			}   else {
				if (ms == null) {
					ms = c;
					mt = c;
				} else {
					mt.next = c;
					mt = mt.next;
				}
			}
			c = t;
		}

		// 小于区域的尾巴， 
		if (lt != null) { // 如果有小于区域
			lt.next = ms;
			mt = mt == null ? lt : mt; // 下一步，谁去连大于区域的头，谁就变成eT
		}
		
		return ls != null ? ls : (ms != null ? ms : mt);
	}
}
