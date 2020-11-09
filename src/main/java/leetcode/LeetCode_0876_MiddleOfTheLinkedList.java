//Given a non-empty, singly linked list with head node head, return a middle node of linked list.
//
//If there are two middle nodes, return the second middle node.
//
// 
//
//Example 1:
//
//Input: [1,2,3,4,5]
//Output: Node 3 from this list (Serialization: [3,4,5])
//The returned node has value 3.  (The judge's serialization of this node is [3,4,5]).
//Note that we returned a ListNode object ans, such that:
//ans.val = 3, ans.next.val = 4, ans.next.next.val = 5, and ans.next.next.next = NULL.
//Example 2:
//
//Input: [1,2,3,4,5,6]
//Output: Node 4 from this list (Serialization: [4,5,6])
//Since the list has two middle nodes with values 3 and 4, we return the second one.
// 
//
//Note:
//
//The number of nodes in the given list will be between 1 and 100.
package leetcode;

public class LeetCode_0876_MiddleOfTheLinkedList {
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

	// 奇数返回中点，偶数返回下中点
	public static ListNode middleNode(ListNode head) {
		if (null == head || head.next == null) {
			return head;
		}
		if (head.next.next == null) {
			return head.next;
		}
		ListNode slow = head;
		ListNode fast = head;
		while(fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		return slow;
	}
}
