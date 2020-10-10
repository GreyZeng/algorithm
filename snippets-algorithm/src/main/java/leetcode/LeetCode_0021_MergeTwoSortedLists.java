package leetcode;

public class LeetCode_0021_MergeTwoSortedLists {

    public static class ListNode {
        public int val;
        public ListNode next;
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (null == l1) {
            return l2;
        }
        if (null == l2) {
            return l1;
        }
        ListNode res = l1.val > l2.val ? l2 : l1;
        ListNode keep = res;
        if (res == l1) {
            l1 = l1.next;
        }
        if (res == l2) {
            l2 = l2.next;
        }
        while (l1 != null && l2 != null) {
            if (l1.val > l2.val) {
                res.next = l2;
                l2 = l2.next;
            } else {
                res.next = l1;
                l1 = l1.next;
            }
        }
        if (l1!=null) {
            res.next = l1;
        }
        if (l2 != null) {
            res.next = l2;
        }
        return keep;
    }

}
