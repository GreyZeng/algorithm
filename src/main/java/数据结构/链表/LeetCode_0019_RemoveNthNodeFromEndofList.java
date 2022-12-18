package 数据结构.链表;

// note 数够n+1个，然后依次遍历到尾部
// https://www.cnblogs.com/greyzeng/p/14675184.html
// 和 https://www.lintcode.com/problem/174/ 一样
public class LeetCode_0019_RemoveNthNodeFromEndofList {

  public static class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
      this.val = val;
    }
  }

  public static ListNode removeNthFromEnd(ListNode head, int n) {
    ListNode cur = head;
    ListNode pre = null;
    while (cur != null) {
      n--;
      if (n == -1) {
        // 遍历到n+1位置后，记录一下pre的位置
        pre = head;
      }
      if (n < -1) {
        pre = pre.next;
      }
      cur = cur.next;
    }
    if (pre == null) {
      // 删除的节点就是头部节点
      return head.next;
    }
    pre.next = pre.next.next;
    return head;
  }
}
