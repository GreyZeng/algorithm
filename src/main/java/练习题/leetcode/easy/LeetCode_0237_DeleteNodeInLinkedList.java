package 练习题.leetcode.easy;

// 不适用
@Deprecated
public class LeetCode_0237_DeleteNodeInLinkedList {
  public class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
      val = x;
    }
  }

  public void deleteNode(ListNode node) {
    node.val = node.next.val;
    node.next = node.next.next;
  }

}
