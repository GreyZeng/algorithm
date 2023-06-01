package list;

// 复制带随机指针的链表
// https://leetcode.com/problems/copy-list-with-random-pointer
// 笔记：https://www.cnblogs.com/greyzeng/p/16750999.html
public class LeetCode_0138_CopyListWithRandomPointer {

    public class Node {

        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public Node copyRandomList(Node head) {
        if (head == null) {
            return head;
        }
        Node cur = head;
        // 复制节点接在当前节点的后面
        while (cur != null) {
            Node next = cur.next;
            Node copy = new Node(cur.val);
            cur.next = copy;
            copy.next = next;
            cur = next;
        }
        // 设置复制节点的random指针
        cur = head;
        while (cur != null) {
            cur.next.random = cur.random == null ? null : cur.random.next;
            cur = cur.next.next;
        }
        cur = head;
        // 切割原链表和复制链表
        Node newHead = cur.next;
        cur = head;
        Node copyCur = newHead;
        while (cur != null) {
            cur.next = cur.next.next;
            copyCur.next = copyCur.next == null ? null:copyCur.next.next;
            cur = cur.next;
            copyCur = copyCur.next;
        }
        return newHead;
    }

}
