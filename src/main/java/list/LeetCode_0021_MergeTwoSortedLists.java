package list;

// merge two sorted lists
// leetcode: https://leetcode.com/problems/merge-two-sorted-lists
// lintcode: https://www.lintcode.com/problem/165/
// https://www.cnblogs.com/greyzeng/p/7551789.html
public class LeetCode_0021_MergeTwoSortedLists {

    public class ListNode {
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

    // Constraints:
    // The number of nodes in both lists is in the range [0, 50].
    // -100 <= Node.val <= 100
    // Both list1 and list2 are sorted in non-decreasing order.
    // eg:
    // list1: 1 -> 3 -> 6 -> 9
    // list2: 2 -> 6 -> 8 -> 12 -> 64
    // 1 -> 2 -> 3 -> 6 -> 6 -> 8 -> 9 -> 12 -> 64
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (null == list1 || null == list2) {
            return list1 == null ? list2 : list1;
        }
        ListNode start = list1.val < list2.val ? list1 : list2;
        if (start == list1) {
            list1 = list1.next;
        } else {
            list2 = list2.next;
        }
        ListNode cur = start;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                cur.next = list1;
                list1 = list1.next;
            } else {
                cur.next = list2;
                list2 = list2.next;
            }
            cur = cur.next;
        }
        cur.next = list1 != null ? list1 : list2;
        return start;
    }

}
