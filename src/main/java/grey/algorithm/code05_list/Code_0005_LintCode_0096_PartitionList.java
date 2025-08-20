package grey.algorithm.code05_list;

// 链表上实现 Partition（注：要保持相对顺序）
// https://leetcode.com/problems/partition-list/
// https://www.lintcode.com/problem/96/
// 笔记：https://www.cnblogs.com/greyzeng/p/16923068.html
public class Code_0005_LintCode_0096_PartitionList {

    // 仅做Partition
    // 注：要保持相对秩序
    public ListNode partition(ListNode head, int x) {
         ListNode sH = null; // 所有小于x的元素组成的链表的头结点
        ListNode sT = null; // 所有小于x的元素组成的链表的尾节点
        ListNode bH = null; // 所有大于等于x的元素组成的链表的头结点
        ListNode bT = null; // 所有大于等于x的元素组成的链表的尾结点
        ListNode cur = head;
        while (cur != null) {
            int val = cur.val;
            if (val < x) {
                if (sH == null) {
                    sH = cur;
                    sT = cur;
                } else {
                    // sH 不为 null，sT肯定不为null
                    sT.next = cur;
                    sT = cur;
                }
            } else {
                // val > x
                if (bH == null) {
                    bH = cur;
                    bT = cur;
                } else {
                    bT.next = cur;
                    bT = cur;
                }
            }
            cur = cur.next;
        }
        
        if (sH != null) {
            sT.next = bH;
            if (bT != null) {
                // 防止形成环
                bT.next = null;
            }
            return sH;
        }
        return bH;
    }

    public class ListNode {
        int val;
        ListNode next;
    }
}
