package leetcode;

// 笔记：https://www.cnblogs.com/greyzeng/p/16753140.html
// https://leetcode.cn/problems/linked-list-cycle/
// 判断链表是否有环
public class LeetCode_0141_LinkedListCycle {

  private static class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
      val = x;
      next = null;
    }
  }

  public static boolean hasCycle(ListNode head) {
    if (null == head || head.next == null) {
      return false;
    }
    ListNode fast = head.next.next;
    ListNode slow = head.next;
    if (fast == null) {
      return false;
    }
    if (fast.next == null) {
      return false;
    }
    while (fast != slow) {
      fast = fast.next.next;
      if (fast == null || fast.next == null) {
        return false;
      }
      slow = slow.next;
    }
    return true;
  }
}
