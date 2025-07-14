package grey.algorithm;

// merge two sorted lists
// leetcode: https://leetcode.com/problems/merge-two-sorted-lists
// lintcode: https://www.lintcode.com/problem/165/
// https://www.cnblogs.com/greyzeng/p/7551789.html
public class Code_0005_LeetCode_0021_MergeTwoSortedLists {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return l1 == null ? l2 : l1;
        }
        ListNode head = l1.val > l2.val ? l2 : l1;
        ListNode pre = head;
        ListNode cur1 = head == l1 ? l1.next : l1;
        ListNode cur2 = cur1 == l1 ? l2.next : l2;
        while (cur1 != null && cur2 != null) {
            if (cur1.val < cur2.val) {
                pre.next = cur1;
                cur1 = cur1.next;
            } else {
                pre.next = cur2;
                cur2 = cur2.next;
            }
            pre = pre.next;
        }
        pre.next = cur1!=null?cur1:cur2;
        return head;
    }

    public static class ListNode {

        int val;
        ListNode next;
    }
}
