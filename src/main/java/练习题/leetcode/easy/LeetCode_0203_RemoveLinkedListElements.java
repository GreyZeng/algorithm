
package 练习题.leetcode.easy;

// 删除链表的指定节点
// https://leetcode.cn/problems/remove-linked-list-elements/
// 笔记：https://www.cnblogs.com/greyzeng/p/16629407.html
public class LeetCode_0203_RemoveLinkedListElements {
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

  public static ListNode removeElements(ListNode head, int val) {
    if (null == head) {
      return null;
    }
    while (head != null && head.val == val) {
      head = head.next;
    }
    if (head == null) {
      return null;
    }
    ListNode pre = head;
    ListNode cur = pre.next;
    while (cur != null) {
      if (cur.val == val) {
        pre.next = cur.next;
        cur = cur.next;
      } else {
        pre = cur;
        cur = pre.next;
      }
    }
    return head;
  }
}
