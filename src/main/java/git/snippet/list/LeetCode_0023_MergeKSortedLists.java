package git.snippet.list;

import java.util.Comparator;
import java.util.PriorityQueue;

// 把每个链表的头节点加入到小根堆中
// 然后弹出一个元素X，然后从这个弹出元素的下一个元素开始和堆顶元素比
// 如果X比堆顶元素大，则堆顶元素弹出，X进入小根堆
// 如果X比堆顶元素小，则直接不需要进入堆顶，作为结果链表
// https://www.cnblogs.com/greyzeng/p/7551789.html
public class LeetCode_0023_MergeKSortedLists {
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode() {}
     * ListNode(int val) { this.val = val; }
     * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (null == lists || lists.length == 0) {
            return null;
        }
        if (lists.length == 1) {
            return lists[0];
        }
        PriorityQueue<ListNode> heap = new PriorityQueue<>((o1, o2) -> (o1.val - o2.val));
        for (ListNode list : lists) {
            if (list != null) {
                heap.offer(list);
            }
        }
        ListNode newHead = heap.poll();
        ListNode cur = newHead;
        while (!heap.isEmpty()) {
            ListNode next = cur.next;
            if (next == null) {
                // 某个链表走到头了
                cur.next = heap.poll();
                cur = cur.next;
            } else {
                if (next.val <= heap.peek().val) {
                    cur = cur.next;
                } else {
                    cur.next = heap.poll();
                    cur = cur.next;
                    heap.offer(next);
                }
            }
        }
        return newHead;
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
