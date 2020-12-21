//Given a singly linked list, group all odd nodes together followed by the even nodes. Please note here we are talking about the node number and not the value in the nodes.
//
//		You should try to do it in place. The program should run in O(1) space complexity and O(nodes) time complexity.
//
//		Example 1:
//
//		Input: 1->2->3->4->5->NULL
//		Output: 1->3->5->2->4->NULL
//		Example 2:
//
//		Input: 2->1->3->5->6->4->7->NULL
//		Output: 2->3->6->7->1->5->4->NULL
//
//
//		Constraints:
//
//		The relative order inside both the even and odd groups should remain as it was in the input.
//		The first node is considered odd, the second node even and so on ...
//		The length of the linked list is between [0, 10^4].
package leetcode;

public class LeetCode_0328_OddEvenLinkedList {

	public static class ListNode {
		int val;
		ListNode next;
	}

	public ListNode oddEvenList(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode oddS = null;
            ListNode oddE = null;
            ListNode evenS = null;
            ListNode evenE = null;
            
            ListNode c = head;
            int count = 1;
            while (c != null) {
                ListNode next = c.next;
                c.next = null;
                if ((count & 1) != 1) {
                    if (oddS == null) {
                        oddS = c;
                        oddE = c;
                    } else {
                        oddE.next = c;
                        oddE = c;
                    }
                } else {
                    if (evenS == null) {
                        evenS = c;
                        evenE = c;
                    } else {
                        evenE.next = c;
                        evenE = c;
                    }
                }
                count++;
                c = next;
            }
            if (evenE != null) {
                evenE.next = oddS;
                return evenS;
            }
            return evenS;
	}

}
