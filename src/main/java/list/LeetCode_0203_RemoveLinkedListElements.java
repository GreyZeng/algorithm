package list;

import java.util.List;

// 删除链表的指定节点
// https://leetcode.com/problems/remove-linked-list-elements/
// 笔记：https://www.cnblogs.com/greyzeng/p/16629407.html
public class LeetCode_0203_RemoveLinkedListElements {
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

  public ListNode removeElements(ListNode head, int val) {
    if (head == null) {
      return head;
    }
    while (head != null && head.val == val) {
      head = head.next;
    }
    // 元素已经被删除光了
    if (head == null) {
      return head;
    }
    ListNode newHead = head;
    ListNode cur = newHead;
    ListNode dummy = new ListNode(val, newHead);
    while (cur != null) {
      if (cur.val == val) {
        cur = cur.next;
        dummy.next = cur;
      } else {
        cur = cur.next;
        dummy = dummy.next;
      }
    }
    return newHead;
  }
}
