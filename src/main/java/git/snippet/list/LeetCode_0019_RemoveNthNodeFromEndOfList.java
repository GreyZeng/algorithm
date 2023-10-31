package git.snippet.list;

// note 数够n+1个，然后依次遍历到尾部
// https://www.cnblogs.com/greyzeng/p/14675184.html
// 和 https://www.lintcode.com/problem/174/ 一样
public class LeetCode_0019_RemoveNthNodeFromEndOfList {

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
      cur = cur.next;
      if (n == -1) {
        // 走了 n + 1 步
        // pre 可以开始走了
        pre = head;
      }
      if (n < -1) {
        // 走余下的步
        // pre 和 cur 一起走
        pre = pre.next;
      }
    }
    // cur 走 n 步直接到头了，说明是倒数第 n 个元素一定是头节点
    // 此时会换头
    if (pre == null) {
      return head.next;
    }
    pre.next = pre.next.next;
    return head;
  }
}
