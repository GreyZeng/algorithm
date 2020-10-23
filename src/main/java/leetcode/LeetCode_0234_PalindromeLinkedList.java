package leetcode;

//Given a singly linked list, determine if it is a palindrome.
//
//        Example 1:
//
//        Input: 1->2
//        Output: false
//        Example 2:
//
//        Input: 1->2->2->1
//        Output: true
//        Follow up:
//        Could you do it in O(n) time and O(1) space?

import java.util.Stack;

/**
 * 给定一个单链表的头节点head，请判断该链表是否为回文结构。
 * 1）栈方法特别简单（笔试用）
 * 2）改原链表的方法就需要注意边界了（面试用）
 */
public class LeetCode_0234_PalindromeLinkedList {
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

    // 利用栈O(n)
    public static boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        Stack<Integer> stack = new Stack<>();
        ListNode c = head;
        while (c != null) {
            stack.push(c.val);
            c = c.next;
        }
        ListNode t = head;
        while (t != null) {
            if (stack.peek() == t.val) {
                stack.pop();
                t = t.next;
            } else {
                return false;
            }
        }
        return true;
    }

    //利用栈O(n/2)
    // 利用快慢指针，快指针到终点，慢指针到中间位置(
    public static boolean isPalindrome2(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode c = head;
        ListNode r = head.next;
        while (c.next != null && c.next.next != null) {
            c = c.next.next;
            r = r.next;
        }
        // r来到中点位置（奇数是中点后一个位置，偶数是下中点位置）

        Stack<Integer> stack = new Stack<>();
        while (r != null) {
            stack.push(r.val);
            r = r.next;
        }

        ListNode t = head;
        while (!stack.isEmpty()) {
            if (stack.peek() == t.val) {
                stack.pop();
                t = t.next;
            } else {
                return false;
            }
        }
        return true;
    }

    //修改原链表，空间O(1)
    public static boolean isPalindrome3(ListNode head) {
        // 0个点和一个点都是
        if (head == null || head.next == null) {
            return true;
        }
        // 两个点的话，第0个和第一个点的值相等即为回环
        // 否则不是回环
        if (head.next.next == null) {
            return head.next.val == head.val;
        }
        ListNode cur = head; // 存一下头节点的位置，便于后续还原整个链表

        // 偶数到上中点位置，奇数到中点位置
        ListNode fast = cur.next;
        ListNode slow = cur;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        ListNode rightComplete = slow.next;
        slow.next = null; // 断开为两个链表

        ListNode rightStart = rightComplete; // 存一下rightStart的位置，便于后续还原整个链表
        // 以rightStart为头的链表做一次单链表反转，得到新链表的头节点，假设这个反转后的链表头节点为lastNode
        ListNode lastNode = null;
        ListNode next;
        while (rightStart != null) {
            next = rightStart.next;
            rightStart.next = lastNode;
            lastNode = rightStart;
            rightStart = next;
        }

        ListNode n3 = lastNode;
        ListNode n4 = lastNode;

        // 左边从头开始向中间遍历，右边从尾部开始向中间遍历
        // 只要有不匹配的就为false，两个有一个遍历结束即为true

        boolean result = true;

        while (n3 != null && cur != null) {
            if (n3.val != cur.val) {
                result = false;
                break;
            }
            n3 = n3.next;
            cur = cur.next;
        }
        ListNode newHead = null;
        ListNode temp;
        while (n4 != null) {
            temp = n4.next;
            n4.next = newHead;
            newHead = n4;
            n4 = temp;
        }

        ListNode fin = head;
        // 还原链表
        while (fin.next != null) {
            fin = fin.next;
        }
        fin.next = newHead;
        return result;
    }
}
