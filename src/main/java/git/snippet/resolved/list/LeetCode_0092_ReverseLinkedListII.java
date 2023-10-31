package git.snippet.resolved.list;

// 值得反复练习的习题
// 反转单链表一部分
// https://leetcode.cn/problems/reverse-linked-list-ii/
// 笔记：https://www.cnblogs.com/greyzeng/p/16629407.html
public class LeetCode_0092_ReverseLinkedListII {
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

  public ListNode reverseBetween(ListNode h, int m, int n) {
    if (m == n || h == null || h.next == null) {
      return h;
    }
    ListNode dummy = new ListNode(0, h);
    ListNode startPre = dummy;
    ListNode start = h;
    for (int i = 1; i < m; i++) {
      startPre = startPre.next;
      start = start.next;
    }
    ListNode pre = null;
    ListNode cur = start;
    for (int i = m; i <= n; i++) {
      ListNode tmp = cur.next;
      cur.next = pre;
      pre = cur;
      cur = tmp;
    }
    if (m != 1) {
      startPre.next = pre;
      start.next = cur;
      return h;
    } else {
      start.next = cur;
      return pre;
    }
  }

  public static ListNode reverseBetweenRecursive(ListNode head, int m, int n) {
    if (m == 1) {
      return reverseN(head, n);
    }
    head.next = reverseBetweenRecursive(head.next, m - 1, n - 1);
    return head;
  }

  static ListNode successor = null;

  // 反转链表前N个节点
  public static ListNode reverseN(ListNode head, int n) {
    if (n == 1) {
      successor = head.next;
      return head;
    }
    ListNode last = reverseN(head.next, n - 1);
    head.next.next = head;
    head.next = successor;
    return last;
  }
}
