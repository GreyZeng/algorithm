// Given a singly linked list, group all odd nodes together followed by the even nodes. 
// Please note here we are talking about 
// the node number and not the value in the nodes.
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
package leetcode.medium;

public class LeetCode_0328_OddEvenLinkedList {

    public static class ListNode {
        ListNode next;
    }

    // 奇数节点和偶数节点放在一起
    // 所有偶数下标的数一定要在奇数下标数之后（注意：是下标而非值）
    public static ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }
        ListNode oddStart = null;
        ListNode oddEnd = null;
        ListNode evenStart = null;
        ListNode evenEnd = null;
        ListNode cur = head;
        int count = 1;
        while (cur != null) {
            if ((count & 1) == 1) {
                // 奇数
                if (oddStart == null) {
                    oddStart = cur;
                } else {
                    oddEnd.next = cur;
                }
                oddEnd = cur;
            } else {
                // 偶数
                if (evenStart == null) {
                    evenStart = cur;
                } else {
                    evenEnd.next = cur;
                }
                evenEnd = cur;
            }
            count++;
            cur = cur.next;
        }
        if (evenEnd != null) {
            evenEnd.next = null;
        }
        oddEnd.next = evenStart;
        return oddStart;

    }

}
