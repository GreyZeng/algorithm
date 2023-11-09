package git.snippet.list;

// 链表上实现 Partition（注：要保持相对顺序）
// https://leetcode.com/problems/partition-list/
// 笔记：https://www.cnblogs.com/greyzeng/p/16923068.html
public class LeetCode_0086_PartitionList {

    // 仅做Partition
    // 注：要保持相对秩序
    public ListNode partition(ListNode head, int x) {
        ListNode smallHead = null;
        ListNode smallTail = null;
        ListNode bigHead = null;
        ListNode bigTail = null;
        ListNode cur = head;
        while (cur != null) {
            if (cur.val < x) {
                if (smallHead == null) {
                    smallHead = cur;
                    smallTail = cur;
                } else {
                    // smallTail 一定不为 null
                    smallTail.next = cur;
                    smallTail = smallTail.next;
                }
            } else {
                if (bigHead == null) {
                    bigHead = cur;
                    bigTail = cur;
                } else {
                    // bigTail 一定不为 null
                    bigTail.next = cur;
                    bigTail = bigTail.next;
                }
            }
            cur = cur.next;
        }
        if (bigTail != null) {
            // 这个分支非常重要，防止形成环！
            bigTail.next = null;
        }
        if (smallTail != null) {
            smallTail.next = bigHead;
            return smallHead;
        }
        return bigHead;
    }

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
}
