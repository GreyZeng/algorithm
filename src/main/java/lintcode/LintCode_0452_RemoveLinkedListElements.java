package lintcode;

public class LintCode_0452_RemoveLinkedListElements {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public ListNode removeElements(ListNode head, int val) {
        ListNode t = head;
        while (t != null) {
            if (t.val == val) {
                t = t.next;
            } else {
                break;
            }
        }
        if (t == null) {
            return null;
        }
        // TODO
        return null;
    }
}
