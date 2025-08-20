package grey.algorithm.code05_list;

// 单链表反转
// 笔记：https://www.cnblogs.com/greyzeng/p/17852345.html
// https://leetcode.com/problems/reverse-linked-list/
// https://www.lintcode.com/problem/35/
// Follow up: A linked list can be reversed either iteratively or recursively. Could you implement
// both?
public class Code_0005_LintCode_0035_ReverseLinkedList {

    // 非递归版本
    // lintcode版本
    public ListNode reverse(ListNode head) {
        ListNode cur = head;
        ListNode pre = null;
        while (cur != null) {
            ListNode tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }
        return pre;
    }

    // 递归版本
    // leetcode版本
    // 递归函数定义：反转以 head 为头的链表，并把反转后的头节点返回。
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode preHead = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return preHead;
    }

    public class ListNode {
        ListNode next;
    }
}
