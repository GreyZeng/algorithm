package 数据结构.链表;

/**
 * https://www.lintcode.com/problem/167/
 *
 * @author <a href="mailto:410486047@qq.com">Grey</a>
 * @date 2021/4/14
 * @since
 */
public class LintCode_0167_AddTwoNumbers {
  /**
   * @param l1: the first list
   * @param l2: the second list
   * @return: the sum list of l1 and l2
   */
  public ListNode addLists(ListNode l1, ListNode l2) {
    if (l1 == null) {
      return l2;
    }
    if (l2 == null) {
      return l1;
    }
    // l1 和 l2 的长度一定一样
    int sum = l1.val + l2.val;
    int val = getValue(sum);
    int move = getMove(sum);
    ListNode result = new ListNode(val);
    ListNode c = result;
    ListNode t1 = l1.next;
    ListNode t2 = l2.next;
    while (t1 != null || t2 != null || move != 0) {
      sum = valOrDefault(t1) + valOrDefault(t2) + move;
      c.next = new ListNode(getValue(sum));
      move = getMove(sum);
      c = c.next;
      if (t1 != null) {
        t1 = t1.next;
      }
      if (t2 != null) {
        t2 = t2.next;
      }
    }
    return result;
  }

  public static int valOrDefault(ListNode t) {
    return t == null ? 0 : t.val;
  }

  public static int getMove(int sum) {
    return sum / 10;
  }

  public static int getValue(int sum) {
    return sum % 10;
  }

  public static class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
      val = x;
      next = null;
    }
  }
}
