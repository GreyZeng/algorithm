package resolved.list;

// 单链表反转
// 笔记：https://www.cnblogs.com/greyzeng/p/16629407.html
// https://leetcode.com/problems/reverse-linked-list/
// Follow up: A linked list can be reversed either iteratively or recursively. Could you implement
// both?
public class LeetCode_0206_ReverseLinkedList {
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

  // 非递归版本
  public ListNode reverseList2(ListNode h) {
    if (h == null || h.next == null) {
      return h;
    }
    ListNode pre = null;
    ListNode cur = h;
    while (cur != null) {
      ListNode t = cur.next;
      cur.next = pre;
      pre = cur;
      cur = t;
    }
    return pre;
  }

  // 递归版本
  // 反转head为头的链表，并把反转后的头节点返回
  public ListNode reverseList(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }
    ListNode newHead = reverseList(head.next);
    head.next.next = head;
    head.next = null;
    return newHead;
  }
}
