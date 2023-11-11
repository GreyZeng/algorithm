package git.snippet.list;

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
            return list1 != null ? list1 : list2;
        }
        ListNode start = list1.val > list2.val ? list2 : list1;
        ListNode l1 = start == list1 ? list1.next : list1;
        ListNode l2 = start == list1 ? list2 : list2.next;

        ListNode cur = start;
        while (l1 != null && l2 != null) {
            if (l1.val > l2.val) {
                cur.next = l2;
                l2 = l2.next;
            } else {
                cur.next = l1;
                l1 = l1.next;
            }
            cur = cur.next;
        }
        while (l1 != null) {
            cur.next = l1;
            cur = cur.next;
            l1 = l1.next;
        }

        while (l2 != null) {
            cur.next = l2;
            cur = cur.next;
            l2 = l2.next;
        }
        return start;
    }

    public class ListNode {
        int val;
        ListNode next;
    }
}
