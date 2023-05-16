
package leetcode;

// 链表上实现 Partition（注：要保持相对顺序）
// https://leetcode.cn/problems/partition-list/
// 笔记：https://www.cnblogs.com/greyzeng/p/16923068.html
public class LeetCode_0086_PartitionList {

    public static class ListNode {

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

    // 仅做Partition
    public static ListNode partition(ListNode head, int x) {
        ListNode sH = null;
        ListNode sT = null;
        ListNode mH = null;
        ListNode mT = null;
        ListNode cur = head;
        while (cur != null) {
            if (cur.val < x) {
                if (sH == null) {
                    sH = cur;
                } else {
                    sT.next = cur;
                }
                sT = cur;
            } else {
                // cur.val >= x
                // 都放到大于等于区域
                if (mH == null) {
                    mH = cur;
                } else {
                    mT.next = cur;
                }
                mT = cur;
            }
            cur = cur.next;
        }
        if (mT != null) {
            mT.next = null;
        }
        if (sT != null) {
            sT.next = null;
        }
        if (sH == null) {
            return mH;
        }
        sT.next = mH;
        return sH;
    }

    public static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(4);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(2);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(2);
        printList(head);
        ListNode t = partition(head, 3);
        printList(t);
    }
}
