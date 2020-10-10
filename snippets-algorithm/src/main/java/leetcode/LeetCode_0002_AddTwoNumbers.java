package leetcode;


public class LeetCode_0002_AddTwoNumbers {
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode result = new ListNode();
        ListNode cur = result;
        int move = 0;
        while (l1 != null || l2 != null || move != 0) {
            int v1 = l1 == null ? 0 : l1.val;
            int v2 = l2 == null ? 0 : l2.val;
            int t = move;
            move = (v1 + v2 + move) >= 10 ? 1 : 0;
            cur.val = move == 1 ? (v1 + v2 + t - 10) : (v1 + v2 + t);
            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
            if (l1 != null || l2 != null || move != 0) {
                ListNode next = new ListNode();
                cur.next = next;
                cur = next;
            }
        }

        return result;
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

    public static void printListNode(ListNode t) {
        while (t != null) {
            System.out.print(t.val + " ");
            t = t.next;
        }
    }

    public static void main(String[] args) {
        System.out.println("begin totest");
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);
        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);
        // ListNode l1 = new ListNode(5);
        // ListNode l2 = new ListNode(5);
        ListNode l3 = addTwoNumbers(l1, l2);
        printListNode(l3);
        // System.out.println(l3);
    }

}


