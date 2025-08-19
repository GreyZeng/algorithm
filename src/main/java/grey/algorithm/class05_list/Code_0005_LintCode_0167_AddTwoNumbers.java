package grey.algorithm.class05_list;

// 给你两个非空 的链表，表示两个非负的整数。它们每位数字都是按照逆序的方式存储的，并且每个节点只能存储一位数字。
// 请你将两个数相加，并以相同形式返回一个表示和的链表。
// 你可以假设除了数字 0 之外，这两个数都不会以 0开头。
// https://leetcode.com/problems/add-two-numbers
// https://www.lintcode.com/problem/167/
// 注意是从左往右加，最右侧要注意进位信息
// 笔记：https://www.cnblogs.com/greyzeng/p/16629407.html
public class Code_0005_LintCode_0167_AddTwoNumbers {

    // 不额外开辟空间，复用 l1 和 l2 两个链表
    public ListNode addLists(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return l1 == null ? l2 : l1;
        }
        int sum = l1.val + l2.val;
        int carry = sum / 10;
        int val = sum % 10;
        l1.val = val;
        ListNode head = l1;
        l1 = l1.next;
        l2 = l2.next;
        ListNode cur = head;
        while (l1 != null && l2 != null) {
            sum = l1.val + l2.val + carry;
            val = sum % 10;
            carry = sum / 10;
            l1.val = val;
            cur = l1;
            l1 = l1.next;
            l2 = l2.next;
        }
        while (l1 != null) {
            sum = l1.val + carry;
            val = sum % 10;
            carry = sum / 10;
            l1.val = val;
            cur = l1;
            l1 = l1.next;
        }
        if (l2 != null) {
            cur.next = l2;
        }
        while (l2 != null) {
            sum = l2.val + carry;
            val = sum % 10;
            carry = sum / 10;
            l2.val = val;
            cur = l2;
            l2 = l2.next;
        }
        if (carry != 0) {
            cur.next = new ListNode(carry);
        }
        return head;
    }

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
}
