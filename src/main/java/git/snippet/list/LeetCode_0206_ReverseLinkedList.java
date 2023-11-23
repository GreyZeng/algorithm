package git.snippet.list;

// 单链表反转
// 笔记：https://www.cnblogs.com/greyzeng/p/17852345.html
// https://leetcode.com/problems/reverse-linked-list/
// Follow up: A linked list can be reversed either iteratively or recursively. Could you implement
// both?
public class LeetCode_0206_ReverseLinkedList {
    // 非递归版本
    public ListNode reverseList2(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }
        return pre;
    }

    // 递归版本
    public ListNode reverseList(ListNode head) {
        return reverse(head);
    }

    public ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode preHead = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return preHead;
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
