package leetcode;


import java.util.Stack;

// 判断一个链表是否是回文
// 笔记：https://www.cnblogs.com/greyzeng/p/16629407.html
// https://leetcode.cn/problems/palindrome-linked-list/
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


    // 修改原链表，空间O(1)
    public static boolean isPalindrome(ListNode head) {
        // 0个节点
        // 1个节点 都是回文
        if (head == null || head.next == null) {
            return true;
        }
        // 判断两个节点
        if (head.next.next == null) {
            return head.val == head.next.val;
        }
        // 判断三个节点
        if (head.next.next.next == null) {
            return head.val == head.next.next.val;
        }

        // 到这一步，至少有四个节点

        // 使用快慢指针
        // 奇数来到中点前一个位置(假设为a)和中点后一个位置(假设为b)
        // 偶数来到上中点位置(假设为a)和下中点位置(假设为b)
        // head ... a 这个链表，链表反转一下 a...head
        // 设置两个指针，一个指向a，一个指向b，每个位置对比，结果记录在result中
        // 恢复整个链表
        ListNode slow = head;
        ListNode fast = head.next.next;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode a = slow;
        ListNode b;
        ListNode mid = null;
        if (fast != null) {
            // 链表个数为奇数
            mid = a.next;
            b = a.next.next;
        } else {
            b = a.next;
            // 链表个数为偶数
        }
        // 断开链表
        a.next = null;

        // 反转前半部分链表
        ListNode c = reverse(head);

        boolean result = true;
        ListNode leftStart = c;
        ListNode rightStart = b;
        while (leftStart.next != null) {
            if (leftStart.val != rightStart.val) {
                result = false;
            }
            leftStart = leftStart.next;
            rightStart = rightStart.next;
        }
        if (leftStart.val != rightStart.val) {
            result = false;
        }
        // leftStart来到开始节点
        // rightStart来到末尾节点
        ListNode cur = reverse(leftStart);
        while (cur.next != null) {
            cur = cur.next;
        }
        if (mid == null) {
            cur.next = b;
        } else {
            cur.next = mid;
            mid.next = b;
        }
        return result;
    }

    private static ListNode reverse(ListNode head) {
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

    // 利用栈O(n/2)
    public static boolean isPalindrome2(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        if (head.next.next == null) {
            return head.val == head.next.val;
        }
        ListNode slow = head.next;
        ListNode fast = head.next.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        slow = slow.next;
        fast = head;
        Stack<ListNode> stack = new Stack<>();
        while (slow != null) {
            stack.push(slow);
            slow = slow.next;
        }
        while (!stack.isEmpty()) {
            if (stack.pop().val != fast.val) {
                return false;
            }
            fast = fast.next;
        }
        return true;

    }

    // 利用栈O(n)
    public static boolean isPalindrome3(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        ListNode c = head;
        while (c != null) {
            stack.push(c);
            c = c.next;
        }
        c = head;
        while (c != null) {
            if (c.val != stack.pop().val) {
                return false;
            }
            c = c.next;
        }
        return true;
    }

}
