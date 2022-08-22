package leetcode.easy;

//Reverse a singly linked list.
//
//Example:
//
//Input: 1->2->3->4->5->NULL
//Output: 5->4->3->2->1->NULL
//Follow up:
//
//A linked list can be reversed either iteratively or recursively. Could you implement both?
// https://leetcode-cn.com/problems/reverse-linked-list/
public class LeetCode_0206_ReverseLinkedList {
    public static class ListNode {
        ListNode next;
    }

    // 非递归版本
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = null;
        while (head != null) {
            ListNode t = head.next;
            head.next = pre;
            pre = head;
            head = t;
        }
        return pre;
    }

    // 递归版本
    public ListNode reverseList2(ListNode head) {
        return reverse(head);
    }

    // 反转head为头的链表，并把反转后的头节点返回
    public ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return pre;
    }
}
