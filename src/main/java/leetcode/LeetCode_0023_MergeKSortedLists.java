package leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

//  把每个链表的头节点加入到小根堆中
// 然后弹出一个元素X，然后从这个弹出元素的下一个元素开始和堆顶元素比
// 如果X比堆顶元素大，则堆顶元素弹出，X进入小根堆
// 如果X比堆顶元素小，则直接不需要进入堆顶，作为结果链表
public class LeetCode_0023_MergeKSortedLists {

    public static class ListNode {
        int val;
        ListNode next;
    }

    public static class MyComparator implements Comparator<ListNode> {
        @Override
        public int compare(ListNode o1, ListNode o2) {
            return o1.val - o2.val;
        }
    }

    public static ListNode mergeKLists(ListNode[] lists) {
        if (null == lists) {
            return null;
        }
        if (1 == lists.length) {
            return lists[0];
        }
        PriorityQueue<ListNode> queue = new PriorityQueue<>(new MyComparator());
        for (ListNode list : lists) {
            if (null != list) {
                queue.add(list);
            }
        }
        ListNode res = queue.poll();
        ListNode head = res;
        while (!queue.isEmpty()) {
            if (res != null) {
                ListNode n = res.next;
                if (n == null) {
                    res.next = queue.poll();
                    res = res.next;
                } else if (n.val > queue.peek().val) {
                    res.next = queue.poll();
                    res = res.next;
                    queue.add(n);
                } else {
                    res.next = n;
                    res = res.next;
                }
            }
        }
        return head;

    }


}
