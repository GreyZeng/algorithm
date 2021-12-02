package leetcode.medium;

// https://leetcode.com/problems/add-two-numbers/
public class LeetCode_0002_AddTwoNumbers {
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

    public static class Node {
        // 当前值
        public int v;
        // 进位值（只能是0或者1）
        public int n;
    }

    // l1 和 l2 非空
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = new ListNode();
        Node start = add(l1.val, l2.val);
        result.val = start.v;
        l1 = l1.next;
        l2 = l2.next;
        ListNode c = result;
        while (l1 != null && l2 != null) {
            start = add(l1.val + l2.val, start.n);
            c.next = new ListNode(start.v);
            c = c.next;
            l1 = l1.next;
            l2 = l2.next;
        }
        while (l1 != null) {
            start = add(l1.val, start.n);
            c.next = new ListNode(start.v);
            c = c.next;
            l1 = l1.next;
        }

        while (l2 != null) {
            start = add(l2.val, start.n);
            c.next = new ListNode(start.v);
            c = c.next;
            l2 = l2.next;
        }
        if (start.n != 0) {
            c.next = new ListNode(1);
        }
        return result;
    }

    private static Node add(int v1, int v2) {
        Node n = new Node();
        n.v = (v1 + v2) % 10;
        n.n = (v1 + v2) / 10;
        return n;
    }


}


