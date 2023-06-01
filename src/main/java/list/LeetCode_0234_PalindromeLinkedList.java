package list;

import java.util.Stack;


// 判断一个链表是否是回文
// 笔记：https://www.cnblogs.com/greyzeng/p/16629407.html
// https://leetcode.com/problems/palindrome-linked-list/
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

    // 使用栈，
    // 非最优解
    // 时间复杂度O(N)
    // 空间复杂度O(N)
    public boolean isPalindrome1(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        Stack<Integer> stack = new Stack<>();
        ListNode cur = head;
        while (cur != null) {
            stack.push(cur.val);
            cur = cur.next;
        }
        cur = head;
        while (!stack.isEmpty()) {
            if (stack.pop() != cur.val) {
                return false;
            }
            cur = cur.next;
        }
        return true;
    }

    // 利用栈
    // 非最优解
    // 只需要 n/2 的栈空间
    // 时间复杂度O(N)
    // 空间复杂度O(N)
    public boolean isPalindrome2(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        if (head.next.next == null) {
            // 两个节点
            return head.val == head.next.val;
        }
        Stack<Integer> stack = new Stack<>();
        // 如果是偶数，就返回下中点
        ListNode mid = middleNode(head);
        while (mid != null) {
            stack.push(mid.val);
            mid = mid.next;
        }
        ListNode cur = head;
        while (!stack.isEmpty()) {
            if (stack.pop() != cur.val) {
                return false;
            }
            cur = cur.next;
        }
        return true;
    }

    // 到这个方法，至少有三个节点
    // 如果是偶数，就返回下中点
    public ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(1);
        boolean palindrome2 = new LeetCode_0234_PalindromeLinkedList().isPalindrome2(head);
        System.out.println(palindrome2);
    }

    // 修改原链表，空间O(1)
    public boolean isPalindrome3(ListNode head) {
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

    private ListNode reverse(ListNode head) {
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
}
