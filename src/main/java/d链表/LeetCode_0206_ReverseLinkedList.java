package d链表;

// 单链表反转
// 笔记：https://www.cnblogs.com/greyzeng/p/16629407.html
// https://leetcode.com/problems/reverse-linked-list/
public class LeetCode_0206_ReverseLinkedList {
    public static class ListNode {
        ListNode next;
    }

    // 非递归版本
    public ListNode reverseList(ListNode head) {
        if (null == head || head.next == null) {
            return head;
        }
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
