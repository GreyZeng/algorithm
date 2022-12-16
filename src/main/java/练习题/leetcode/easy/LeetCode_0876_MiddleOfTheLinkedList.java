package 练习题.leetcode.easy;

// https://leetcode.cn/problems/middle-of-the-linked-list/
// 使用快慢指针
// 奇数返回中点，偶数返回下中点
// 笔记：https://www.cnblogs.com/greyzeng/p/16629407.html
public class LeetCode_0876_MiddleOfTheLinkedList {
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

  // [1,2,3,4,5] --> 3
  // [1,2,3,4,5,6] --> 4
  // 奇数返回中点，偶数返回下中点
  public static ListNode middleNode(ListNode head) {
    ListNode slow = head;
    ListNode fast = head;
    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }
    return slow;
  }
}
