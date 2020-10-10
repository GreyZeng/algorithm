package leetcode;

// note 数够n+1个，然后依次遍历到尾部
public class LeetCode_0019_RemoveNthNodeFromEndofList {

    public static class ListNode {
        int val;
        ListNode next;


        ListNode(int val) {
            this.val = val;
        }


    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode cur = head;
        ListNode pre = null;
        while (cur != null) {
            n--;
            if (n == -1) {
                pre = head;
            }
            if (n < -1) {
                pre = pre.next;
            }
            cur = cur.next;
        }
        if (n > 0) {
            return head;
        }
        if (pre == null) {
            return head.next;
        }


        pre.next = pre.next.next;
        return head;
    }

    public static void main(String[] args) {
//		testCase1();
//
//		testCase2();
//
//		testCase3();
        testCase4();
    }

    public static void testCase4() {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        ListNode res = removeNthFromEnd(head, 2);
        while (res != null) {
            System.out.println(res.val);
            res = res.next;
        }
        System.out.println("case4 end");
    }

    public static void testCase3() {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        ListNode res = removeNthFromEnd(head, 1);
        while (res != null) {
            System.out.println(res.val);
            res = res.next;
        }
        System.out.println("case3 end");
    }

    public static void testCase2() {
        ListNode head = new ListNode(1);

        ListNode res = removeNthFromEnd(head, 1);
        while (res != null) {
            System.out.println(res.val);
            res = res.next;
        }

        System.out.println("case2 end");
    }

    public static void testCase1() {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        ListNode res = removeNthFromEnd(head, 2);
        while (res != null) {
            System.out.println(res.val);
            res = res.next;
        }

        System.out.println("case1 end");
    }
}
