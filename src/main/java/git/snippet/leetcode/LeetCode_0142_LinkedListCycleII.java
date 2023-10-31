package git.snippet.leetcode;

// 笔记：https://www.cnblogs.com/greyzeng/p/16753140.html
// https://leetcode.cn/problems/linked-list-cycle-ii/
// 链表有环返回第一个入环节点
public class LeetCode_0142_LinkedListCycleII {
  public static class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
      val = x;
      next = null;
    }
  }

  public static ListNode detectCycle(ListNode head) {
    if (head == null || head.next == null || head.next.next == null) {
      return null;
    }
    // 1. 快指针一次走两步，慢指针一次走一步
    // 2. 如果无环，快指针一定会走到空
    // 3. 如果有环，快指针和慢指针一定会在某处相遇。
    // 4. 相遇后，快指针回到原点，慢指针保持在原地
    // 5. 快慢指针同时每次走一步，一定在入环处相遇
    ListNode fast = head;
    ListNode slow = head;
    while (fast != null && fast.next != null) {
      fast = fast.next.next;
      slow = slow.next;
      if (fast == slow) {
        break;
      }
    }
    if (fast == null || fast.next == null) {
      return null;
    }
    if (fast == slow) {
      fast = head;
    }
    while (fast != slow) {
      fast = fast.next;
      slow = slow.next;
    }
    return fast;
  }
}
