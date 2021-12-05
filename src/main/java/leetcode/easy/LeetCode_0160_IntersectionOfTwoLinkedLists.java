package leetcode.easy;

// tips：最后一个节点是否相等，不相等则一定不相交
// 如果一个长度是100，另外一个长度是80， 则让100的节点先走20个节点，然后开始两个链表开始走。
public class LeetCode_0160_IntersectionOfTwoLinkedLists {

    public class ListNode {
        int val;
        ListNode next;
    }

    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (null == headA && null == headB) {
            return null;
        }
        if (null == headA) {
            return null;
        }
        if (null == headB) {
            return null;
        }
        if (headA.next == null && headB.next == null) {
            return headA == headB ? headA : null;
        }
        int lenOfA = getLen(headA);
        int lenOfB = getLen(headB);
        ListNode bigger = lenOfA > lenOfB ? headA : headB;
        ListNode smaller = bigger == headA ? headB : headA;
        int gap = Math.abs(lenOfA - lenOfB);
        while (gap != 0) {
            bigger = bigger.next;
            gap--;
        }
        while (bigger != null && smaller != null) {
            if (bigger == smaller) {
                return bigger;
            }
            bigger = bigger.next;
            smaller = smaller.next;
        }
        return null;
    }

    public static int getLen(ListNode head) {
        int N = 0;
        while (head != null) {
            N++;
            head = head.next;
        }
        return N;
    }

}
