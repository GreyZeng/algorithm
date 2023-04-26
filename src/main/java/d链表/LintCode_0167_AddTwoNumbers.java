package d链表;

// 两个链表相加问题
//给定两个链表的头节点head1和head2，
//        认为从左到右是某个数字从低位到高位，返回相加之后的链表
//        例子     4 -> 3 -> 6        2 -> 5 -> 3
//        返回     6 -> 8 -> 9
//        解释     634 + 352 = 986
// https://www.lintcode.com/problem/167/
// 笔记见：https://www.cnblogs.com/greyzeng/p/16629407.html
public class LintCode_0167_AddTwoNumbers {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public ListNode addLists(ListNode head1, ListNode head2) {
        int len1 = listLength(head1);
        int len2 = listLength(head2);
        ListNode l = len1 >= len2 ? head1 : head2;
        ListNode s = l == head1 ? head2 : head1;
        ListNode curL = l;
        ListNode curS = s;
        int carry = 0;
        ListNode preEnd = l;
        int sum = 0;
        while (curS != null) {
            sum = curL.val + curS.val + carry;
            carry = sum / 10;
            curL.val = sum % 10;
            preEnd = curL;
            curL = curL.next;
            curS = curS.next;
        }
        while (curL != null) {
            sum = curL.val + carry;
            carry = sum / 10;
            curL.val = sum % 10;
            preEnd = curL;
            curL = curL.next;
        }
        if (carry != 0) {
            preEnd.next = new ListNode(carry);
        }
        return l;
    }

    // 求链表长度
    public int listLength(ListNode head) {
        int len = 0;
        while (head != null) {
            len++;
            head = head.next;
        }
        return len;
    }

}
