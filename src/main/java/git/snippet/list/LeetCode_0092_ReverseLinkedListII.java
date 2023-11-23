package git.snippet.list;

// 值得反复练习的习题
// 反转单链表一部分
//The number of nodes in the list is n.
//1 <= n <= 500
//-500 <= Node.val <= 500
//1 <= left <= right <= n
// https://leetcode.cn/problems/reverse-linked-list-ii/
// 笔记：https://www.cnblogs.com/greyzeng/p/17852345.html
public class LeetCode_0092_ReverseLinkedListII {
    // 非递归解法
    // 链表开始位置是从 1 开始
    public ListNode reverseBetween2(ListNode head, int left, int right) {
        if (head.next == null || left == right) {
            // 只有一个节点，怎么反转都一样
            // 只要反转一个节点，反转前后链表还是一样的
            return head;
        }
        if (left == 1) {
            // 需要换头
            ListNode pre = null;
            ListNode end = head;
            ListNode cur = head;
            int gap = right - left + 1;
            while (gap != 0) {
                ListNode tmp = cur.next;
                cur.next = pre;
                pre = cur;
                cur = tmp;
                gap--;
            }
            end.next = cur;
            return pre;
        } else {
            ListNode pre = null;
            for (int i = 1; i < left; i++) {
                pre = pre == null ? head : pre.next;
            }
            ListNode end = pre;
            ListNode cur = pre == null ? head : pre.next;
            ListNode last = cur;
            int gap = right - left + 1;
            while (gap != 0) {
                ListNode tmp = cur.next;
                cur.next = pre;
                pre = cur;
                cur = tmp;
                gap--;
            }

            if (end != null) end.next = pre;
            if (last != null) last.next = cur;
            // 不需要换头，返回原先的头节点
            return head;
        }
    }

    // 递归解法
    public static ListNode reverseBetween(ListNode head, int left, int right) {
        if (left == 1) {
            return reverseN(head, right);
        }
        head.next = reverseBetween(head.next, left - 1, right - 1);
        return head;
    }

    // 反转链表前N个节点
    public static ListNode reverseN(ListNode head, int n) {
        if (n == 1) {
            successor = head.next;
            return head;
        }
        ListNode last = reverseN(head.next, n - 1);
        head.next.next = head;
        head.next = successor;
        return last;
    }

    static ListNode successor = null;

    // for debug
    public static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        printList(head);
        ListNode newHead = new LeetCode_0092_ReverseLinkedListII().reverseBetween(head, 2, 3);
        printList(newHead);
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
