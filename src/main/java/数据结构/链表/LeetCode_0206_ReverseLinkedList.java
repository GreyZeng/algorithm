package 数据结构.链表;

// 单链表反转
// 笔记：https://www.cnblogs.com/greyzeng/p/16629407.html
// https://leetcode-cn.com/problems/reverse-linked-list/
public class LeetCode_0206_ReverseLinkedList {
  public static class ListNode {
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
  public ListNode reverseList(ListNode cur) {
    if (cur == null || cur.next == null) {
      return cur;
    }
    ListNode pre = null;
    while (cur != null) {
      ListNode tmp = cur.next;
      cur.next = pre;
      pre = cur;
      cur = tmp;
    }
    return pre;
  }

  // 递归版本
  public ListNode reverseList2(ListNode head) {
    return reverse(head);
  }

  // 反转head为头的链表，并把反转后的头节点返回
  public ListNode reverse(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }
    ListNode pre = reverse(head.next);
    head.next.next = head;
    head.next = null;
    return pre;
  }
}
