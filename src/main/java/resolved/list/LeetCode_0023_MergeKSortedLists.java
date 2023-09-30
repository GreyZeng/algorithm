package resolved.list;

import java.util.Comparator;
import java.util.PriorityQueue;

// 把每个链表的头节点加入到小根堆中
// 然后弹出一个元素X，然后从这个弹出元素的下一个元素开始和堆顶元素比
// 如果X比堆顶元素大，则堆顶元素弹出，X进入小根堆
// 如果X比堆顶元素小，则直接不需要进入堆顶，作为结果链表
// https://www.cnblogs.com/greyzeng/p/7551789.html
public class LeetCode_0023_MergeKSortedLists {
  public class ListNode {
    int val;
    ListNode next;

    ListNode() {}

    ListNode(int val) {
      this.val = val;
    }

    ListNode(int val, ListNode next) {
      this.val = val;
      this.next = next;
    }
  }

  public ListNode mergeKLists(ListNode[] lists) {
    if (lists == null || lists.length == 0) {
      return null;
    }
    if (lists.length == 1) {
      return lists[0];
    }
    PriorityQueue<ListNode> heap = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
    for (ListNode h : lists) {
      if (h != null) {
        heap.offer(h);
      }
    }
    ListNode start = heap.poll();
    ListNode cur = start;
    while (!heap.isEmpty()) {
      ListNode next = cur.next;
      if (next == null) {
        cur.next = heap.poll();
        cur = cur.next;
      } else {
        if (heap.peek().val < next.val) {
          // 先弹出，再入堆
          ListNode poll = heap.poll();
          cur.next = poll;
          cur = cur.next;
          heap.offer(next);
        } else if (heap.peek().val == next.val) {
          cur = next;
        } else {
          cur = next;
        }
      }
    }
    return start;
  }
}
