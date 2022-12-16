package 练习题.leetcode.medium;

// 给你两个非空 的链表，表示两个非负的整数。它们每位数字都是按照逆序的方式存储的，并且每个节点只能存储一位数字。
//
// 请你将两个数相加，并以相同形式返回一个表示和的链表。
//
// 你可以假设除了数字 0 之外，这两个数都不会以 0开头。
//
// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/add-two-numbers
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
// 注意是从左往右加，最右侧要注意进位信息
// 笔记：https://www.cnblogs.com/greyzeng/p/16629407.html
public class LeetCode_0002_AddTwoNumbers {
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

  public static class Node {
    // 当前值
    public int v;
    // 进位值（只能是0或者1）
    public int n;
  }

  // l1 和 l2 非空
  public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    ListNode result = new ListNode();
    Node start = add(l1.val, l2.val);
    result.val = start.v;
    l1 = l1.next;
    l2 = l2.next;
    ListNode c = result;
    while (l1 != null && l2 != null) {
      start = add(l1.val + l2.val, start.n);
      c.next = new ListNode(start.v);
      c = c.next;
      l1 = l1.next;
      l2 = l2.next;
    }
    while (l1 != null) {
      start = add(l1.val, start.n);
      c.next = new ListNode(start.v);
      c = c.next;
      l1 = l1.next;
    }

    while (l2 != null) {
      start = add(l2.val, start.n);
      c.next = new ListNode(start.v);
      c = c.next;
      l2 = l2.next;
    }
    if (start.n != 0) {
      c.next = new ListNode(1);
    }
    return result;
  }

  private static Node add(int v1, int v2) {
    Node n = new Node();
    n.v = (v1 + v2) % 10;
    n.n = (v1 + v2) / 10;
    return n;
  }
}


