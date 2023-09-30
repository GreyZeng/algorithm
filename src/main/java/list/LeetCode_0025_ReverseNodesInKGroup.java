package list;

// notes: 值得反复练习的问题
// The number of nodes in the list is n.
// 1 <= k <= n <= 5000
// 0 <= Node.val <= 1000
// K个节点的组内逆序调整问题
// 测试链接：https://leetcode.com/problems/reverse-nodes-in-k-group/
// 笔记：https://www.cnblogs.com/greyzeng/p/16629407.html
public class LeetCode_0025_ReverseNodesInKGroup {

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

  // 不超过k个就保持原样
  public ListNode reverseKGroup(ListNode head, int k) {
    ListNode start = head;
    ListNode end = getKGroupEnd(start, k);
    if (end == null) {
      return head;
    }
    // 第一组已经凑够了，所以直接返回head
    head = end;
    reverseBetween(start, end);
    // 上一组的结尾节点
    ListNode lastEnd = start;
    while (lastEnd.next != null) {
      start = lastEnd.next;
      end = getKGroupEnd(start, k);
      if (end == null) {
        return head;
      }
      reverseBetween(start, end);
      lastEnd.next = end;
      lastEnd = start;
    }
    return head;
  }

  // 从start开始，数够k个，然后返回。
  // 例如：
  // start->b->c->d->e
  // k = 3
  // 从start开始，数够3个，所以返回c节点
  // start->b->c
  // k = 6
  // 不够6个，所以返回 null
  public ListNode getKGroupEnd(ListNode start, int k) {
    ListNode cur = start;
    while (cur != null && k != 1) {
      k--;
      cur = cur.next;
    }
    return cur;
  }

  // ....->a->b->c->d->e....
  // 假设start = a, end = d
  // 经过如下方法，会变成
  // ...d->c->b->a->e.....
  public void reverseBetween(ListNode start, ListNode end) {
    end = end.next;
    ListNode pre = null;
    ListNode cur = start;
    while (cur != end) {
      ListNode t = cur.next;
      cur.next = pre;
      pre = cur;
      cur = t;
    }
    start.next = end;
  }
}
