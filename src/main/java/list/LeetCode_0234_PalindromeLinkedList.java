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

    
    // 最优解
    // 空间O(1)
    // 时间O(N)
    public boolean isPalindrome(ListNode head) {
    	// 0 ~ 1 节点
    	if (null == head || head.next == null) {
    		return true;
    	}
    	// 2 个节点
    	if (head.next.next == null) {
    		return head.val == head.next.val;
    	}
    	// 3 个节点
    	if (head.next.next.next == null) {
    		return head.val == head.next.next.val;
    	}
    	int len = lenOfList(head);
    	// 到这里就是4个节点了
    	ListNode pre = null;
    	ListNode slow = head;
    	ListNode cur = slow;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            pre = slow;
            slow = slow.next;
            cur = slow;
        }
        // 从 pre 和 cur 之间断开链接，所以需要先保存好 cur 的位置，便于恢复链表
        ListNode backupNode = cur;
        // 断开
        pre.next = null;
        // 从 head 到 pre 开始反转链表
        ListNode newHead = reverse(head);
        ListNode newHeadBackup = newHead;
        if ((len & 1)!=0) {
        	// 奇数
        	cur = cur.next;
        } 
        boolean result = true;
        while (cur != null) {
        	if (cur.val != newHead.val) {
        		result = false;
        	}
        	cur = cur.next;
        	newHead = newHead.next;
        }
        
        ListNode preHeadBackup = reverse(newHeadBackup);
        while (preHeadBackup.next != null) {
        	preHeadBackup = preHeadBackup.next;
        }
        preHeadBackup.next = backupNode;
        return result;
    }
    public ListNode reverse(ListNode head) {
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
    public int lenOfList(ListNode head) {
    	int len = 0;
    	while (head != null) {
    		head = head.next;
    		len++;
    	}
    	return len;
    }
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(0);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(1);
        head.next.next.next.next = new ListNode(0);
        head.next.next.next.next.next = new ListNode(1);
        boolean palindrome2 = new LeetCode_0234_PalindromeLinkedList().isPalindrome(head);
        System.out.println(palindrome2);
    }
    
}