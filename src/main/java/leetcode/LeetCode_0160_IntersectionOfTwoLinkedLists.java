package leetcode;

// tips：最后一个节点是否相等，不相等则一定不相交
// 如果一个长度是100，另外一个长度是80， 则让100的节点先走20个节点，然后开始两个链表开始走。
public class LeetCode_0160_IntersectionOfTwoLinkedLists {

    public class ListNode {
        int val;
        ListNode next;
    }

    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null) {
            return null;
        }
        if (headB == null) {
            return null;
        }
        ListNode cur1 = headA;
        int L1 = 0;
        ListNode cur2 = headB;
        int L2 = 0;
        while (cur1.next != null) {
            L1++;
            cur1 = cur1.next;
        }
        while (cur2.next != null) {
            L2++;
            cur2 = cur2.next;
        }
        L1++;
        L2++;
        if (cur1 != cur2) {
            // 结尾点不一样，则一定不相交
            return null;
        }
        int L = Math.abs(L1 - L2);
        cur1 = headA;
        cur2 = headB;
        while (L > 0) {
            if (L1 > L2) {
                cur1 = cur1.next;
            } else {
                cur2 = cur2.next;
            }
            L--;
        }
        while (cur1 != cur2) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }

}
