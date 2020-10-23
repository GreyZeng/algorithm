package leetcode;

//Reverse a singly linked list.
//
//Example:
//
//Input: 1->2->3->4->5->NULL
//Output: 5->4->3->2->1->NULL
//Follow up:
//
//A linked list can be reversed either iteratively or recursively. Could you implement both?
// 扩展见：https://zhuanlan.zhihu.com/p/86745433?utm_source=ZHShareTargetIDMore
public class LeetCode_0206_ReverseLinkedList {
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

    // 迭代方式
    // 时间复杂度O(N), 空间复杂度O(1)
    public static ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode next;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    // 递归方法
    // 时间复杂度O(N), 空间复杂度O(N)[主要是递归栈所占的空间]
    public static ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode p = reverseList2(head.next);
        head.next.next = head;
        head.next = null;
        return p;

    }

    public static void main(String[] args) {
        ListNode c = new ListNode(1);
        c.next = new ListNode(2);
        c.next.next = new ListNode(3);
        c.next.next.next = new ListNode(4);
        ListNode m = reverseList(c);
        while (m != null) {
            System.out.println(m.val);
            m = m.next;
        }
    }
}
