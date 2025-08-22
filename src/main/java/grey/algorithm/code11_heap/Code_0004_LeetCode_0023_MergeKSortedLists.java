package grey.algorithm.code11_heap;

import java.util.PriorityQueue;

// 测评链接：https://leetcode.com/problems/merge-k-sorted-lists/
// 把每个链表的头节点加入到小根堆中
// 然后弹出一个元素X，然后从这个弹出元素的下一个元素开始和堆顶元素比
// 如果X比堆顶元素大，则堆顶元素弹出，X进入小根堆
// 如果X比堆顶元素小，则直接不需要进入堆顶，作为结果链表
// https://www.cnblogs.com/greyzeng/p/7551789.html
public class Code_0004_LeetCode_0023_MergeKSortedLists {

    public ListNode mergeKLists(ListNode[] lists) {
        if (null == lists || lists.length < 1) {
            return null;
        }
        PriorityQueue<ListNode> heap = new PriorityQueue<>((o1, o2) -> (o1.val - o2.val));
        for (ListNode node : lists) {
            if (null != node) {
                heap.offer(node);
            }
        }
        if (heap.isEmpty()) {
            return null;
        }
        ListNode head = heap.poll();
        if (head.next != null) {
            heap.offer(head.next);
        }
        ListNode cur = head;
        while (!heap.isEmpty()) {
            ListNode node = heap.poll();
            cur.next = node;
            if (node.next != null) {
                heap.offer(node.next);
            }
            cur = cur.next;
        }
        return head;
    }

    public class ListNode {
        int val;
        ListNode next;
    }
}
