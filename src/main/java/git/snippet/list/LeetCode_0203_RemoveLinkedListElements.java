package git.snippet.list;

// 删除链表的指定节点
// https://leetcode.com/problems/remove-linked-list-elements/
// 笔记：https://www.cnblogs.com/greyzeng/p/16629407.html
public class LeetCode_0203_RemoveLinkedListElements {

    public static ListNode removeElements(ListNode head, int val) {
        while (head != null && head.val == val) {
            head = head.next;
        }
        if (head == null) {
            // 所有节点都删除光了
            return null;
        }
        ListNode newHead = head;
        ListNode cur = head.next;
        ListNode pre = head;
        while (cur != null) {
            if (cur.val == val) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return newHead;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
