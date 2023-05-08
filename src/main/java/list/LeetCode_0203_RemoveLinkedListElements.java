package list;

// 删除链表的指定节点
// https://leetcode.com/problems/remove-linked-list-elements/
// 笔记：https://www.cnblogs.com/greyzeng/p/16629407.html
public class LeetCode_0203_RemoveLinkedListElements {
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

    public ListNode removeElements(ListNode head, int val) {
        ListNode newHead = head;
        while (newHead != null && newHead.val == val) {
            newHead = newHead.next;
        }
        if (newHead == null) {
            return null;
        }
        ListNode pre = newHead;
        ListNode cur = newHead.next;
        while (cur != null) {
            if (cur.val == val) {
                pre.next = cur.next;
                cur = cur.next;
            } else {
                pre = pre.next;
                cur = cur.next;
            }
        }
        return newHead;
    }


}
