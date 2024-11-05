package grey.algorithm;

import java.util.Comparator;
import java.util.PriorityQueue;

// 测评链接：https://leetcode.com/problems/merge-k-sorted-lists/
// 把每个链表的头节点加入到小根堆中
// 然后弹出一个元素X，然后从这个弹出元素的下一个元素开始和堆顶元素比
// 如果X比堆顶元素大，则堆顶元素弹出，X进入小根堆
// 如果X比堆顶元素小，则直接不需要进入堆顶，作为结果链表
// https://www.cnblogs.com/greyzeng/p/7551789.html
public class Code_0031_LeetCode_0023_MergeKSortedLists {

    public ListNode mergeKLists(ListNode[] lists) {
        if (null == lists || lists.length == 0) return null;
        if (lists.length == 1) return lists[0];
        PriorityQueue<ListNode> heap = new PriorityQueue<>(lists.length, Comparator.comparingInt(l -> l.val));
        for (ListNode list : lists) {
            if (list != null) {
                heap.offer(list);
            }
        }
        if (heap.isEmpty()) return null;
        ListNode head = heap.poll();
        ListNode next = head.next;
        if (next != null) {
            heap.offer(next);
        }
        ListNode cur = head;
        while (!heap.isEmpty()) {
            ListNode c = heap.poll();
            cur.next = c;
            if (c.next != null) {
                heap.offer(c.next);
            }
            cur = cur.next;
        }
        return head;
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
