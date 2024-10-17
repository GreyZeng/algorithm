package git.snippet.list;

// https://leetcode.com/problems/middle-of-the-linked-list/
// 使用快慢指针
// 奇数返回中点，偶数返回下中点
// 笔记：https://www.cnblogs.com/greyzeng/p/16629407.html
public class LeetCode_0876_MiddleOfTheLinkedList {
    // [1,2,3,4,5] --> 3
    // [1,2,3,4,5,6] --> 4
    // 奇数返回中点，偶数返回下中点
    public ListNode middleNode(ListNode h) {
        if (null == h || h.next == null) {
            return h;
        }
        ListNode slow = h;
        ListNode fast = h;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    public class ListNode {
        ListNode next;
    }
}
