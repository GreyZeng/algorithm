package git.snippet.list;

// 笔记：https://www.cnblogs.com/greyzeng/p/16753140.html
// https://leetcode.com/problems/intersection-of-two-linked-lists/
// 找到两个链表相交的起始节点
// tips：最后一个节点是否相等，不相等则一定不相交
// 如果一个长度是100，另外一个长度是80， 则让100的节点先走20个节点，然后开始两个链表开始走。
public class LeetCode_0160_IntersectionOfTwoLinkedLists {

    //
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (null == headA || null == headB) {
            return null;
        }
        if (headA == headB) {
            return headA;
        }
        int lenOfA = getLen(headA);
        int lenOfB = getLen(headB);
        int maxLen = Math.max(lenOfA, lenOfB);
        int gap = Math.abs(lenOfA - lenOfB);
        ListNode longList = maxLen == lenOfA ? headA : headB;
        ListNode shortList = longList == headA ? headB : headA;
        for (int i = 0; i < gap; i++) {
            longList = longList.next;
        }
        if (longList == shortList) {
            return longList;
        }
        while (shortList != null) {
            shortList = shortList.next;
            longList = longList.next;
            if (shortList == longList) {
                return shortList;
            }
        }
        return null;
    }

    public int getLen(ListNode head) {
        int len = 0;
        while (head != null) {
            len++;
            head = head.next;
        }
        return len;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
