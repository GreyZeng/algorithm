//Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
//
//You should preserve the original relative order of the nodes in each of the two partitions.
//
//Example:
//
//Input: head = 1->4->3->2->5->2, x = 3
//Output: 1->2->2->4->3->5
package leetcode.medium;

public class LeetCode_0086_PartitionList {

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

    // 仅做Partition
    public static ListNode partition(ListNode head, int x) {
        ListNode lessStart = null;
        ListNode lessEnd = null;
        ListNode biggerStart = null;
        ListNode biggerEnd = null;
        ListNode cur = head;
        while (cur != null) {
            if (cur.val < x) {
                if (lessStart == null) {
                    lessStart = cur;
                } else {
                    lessEnd.next = cur;
                }
                lessEnd = cur;
            } else {
                // cur.val >= x
                // 都放到大于等于区域
                if (biggerStart == null) {
                    biggerStart = cur;
                } else {
                    biggerEnd.next = cur;
                }
                biggerEnd = cur;
            }
            cur = cur.next;
        }
        if (biggerEnd != null) {
            biggerEnd.next = null;
        }
        if (lessEnd != null) {
            lessEnd.next = null;
        }
        if (lessStart == null) {
            return biggerStart;
        }
        lessEnd.next = biggerStart;
        return lessStart;
    }

    public static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(4);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(2);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(2);
        printList(head);
        ListNode t = partition(head, 3);
        printList(t);
    }
}
