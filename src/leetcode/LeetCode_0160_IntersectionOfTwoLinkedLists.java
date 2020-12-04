package leetcode;

// tips：最后一个节点是否相等，不相等则一定不相交
// 如果一个长度是100，另外一个长度是80， 则让100的节点先走20个节点，然后开始两个链表开始走。
public class LeetCode_0160_IntersectionOfTwoLinkedLists {

	public class ListNode {
		int val;
		ListNode next;
	}

	public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
		if (null == headA || null == headB) {
			return null;
		}
		ListNode curA = headA;
		ListNode curB = headB;
		int n = 0;
		while (curA.next != null) {
			n++;
			curA = curA.next;
		}
		while (curB.next != null) {
			n--;
			curB = curB.next;
		}
		if (curA != curB) {
			return null;
		}
		int gap = Math.abs(n); 
		if (n > 0) {
			curA = headA;
			curB = headB;
		} else {
			curA = headB;
			curB = headA;
		}
		while (gap > 0) {
			curA = curA.next;
			gap--;
		}
		while (curA != null) {
			if (curA == curB) {
				return curA;
			}
			curA = curA.next;
			curB = curB.next;
		}
		return null;
	}

}
