package leetcode;

public class LeetCode_0328_OddEvenLinkedList {

	public static class ListNode {
		int val;
		ListNode next;
	}

	public ListNode oddEvenList(ListNode head) {
		if (head == null || head.next == null || head.next.next == null) {
			return head;
		}
		ListNode evenH = null;
		ListNode evenT = null;
		ListNode oddH = null;
		ListNode oddT = null;
		int count = 1;
		ListNode c;
		boolean isFirstEven = false;
		boolean isFirstOdd = false;
		while (head != null) {
			c = head.next;
			head.next = null;
			if (count % 2 != 0) {
				if (evenH == null) {
					evenH = head;
					isFirstEven = true;
				} else {
					if (isFirstEven) {
						isFirstEven = false;
						evenT = head;
						evenH.next = evenT;
					} else {
						evenT.next = head;
						evenT = evenT.next;
					}
				}
			} else {
				if (oddH == null) {
					oddH = head;
					isFirstOdd = true;
				} else {
					if (isFirstOdd) {
						isFirstOdd = false;
						oddT = head;
						oddH.next = oddT;
					} else {
						oddT.next = head;
						oddT = oddT.next;
					}
				}
			}
			head = c;
			count++;
		}
		evenT.next = oddH;
		return evenH;
	}

}
