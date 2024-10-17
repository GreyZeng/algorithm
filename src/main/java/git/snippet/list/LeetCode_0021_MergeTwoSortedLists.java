package git.snippet.list;

import java.util.List;

// merge two sorted lists
// leetcode: https://leetcode.com/problems/merge-two-sorted-lists
// lintcode: https://www.lintcode.com/problem/165/
// https://www.cnblogs.com/greyzeng/p/7551789.html
public class LeetCode_0021_MergeTwoSortedLists {

    // Constraints:
    // The number of nodes in both lists is in the range [0, 50].
    // -100 <= Node.val <= 100
    // Both list1 and list2 are sorted in non-decreasing order.
    // eg:
    // list1: 1 -> 3 -> 6 -> 9
    // list2: 2 -> 6 -> 8 -> 12 -> 64
    // 1 -> 2 -> 3 -> 6 -> 6 -> 8 -> 9 -> 12 -> 64
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null || list2 == null) {
            return list1 == null ? list2 : list1;
        }
        ListNode newHead = list1.val > list2.val ? list2 : list1;
        if (newHead == list1) {
            list1 = list1.next;
        } else {
            list2 = list2.next;
        }
        ListNode cur = newHead;
        while (list1 != null && list2 != null) {
            if (list1.val > list2.val) {
                cur.next = list2;
                cur = cur.next;
                list2 = list2.next;
            } else {
                cur.next = list1;
                cur = cur.next;
                list1 = list1.next;
            }
        }
        while (list1 != null) {
            cur.next = list1;
            cur = cur.next;
            list1 = list1.next;
        }
        while (list2 != null) {
            cur.next = list2;
            cur = cur.next;
            list2 = list2.next;
        }
        return newHead;
    }


    public static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }
}
