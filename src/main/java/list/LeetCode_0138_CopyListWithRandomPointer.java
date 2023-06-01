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
        
        // 切割原链表和复制链表
        Node newHead = cur.next;
        // FIXME
        return newHead;
    }
//    public Node copyRandomList(Node head) {
//        if (head == null) {
//            return null;
//        }
//        // 以下方法将每个节点的复制节点都在这个节点后面
//        // 如果链表是:1->2->3->4 复制以后，会变成： 1->1->2->2->3->3->4->4
//        Node cur = head;
//        while (cur != null) {
//            Node tmp = cur.next;
//            Node copy = new Node(cur.val);
//            cur.next = copy;
//            copy.next = tmp;
//            cur = tmp;
//        }
//
//        // 以下方法是设置每个复制节点的random指针
//        cur = head;
//        while (cur != null) {
//            cur.next.random = cur.random == null ? null : cur.random.next;
//            // 无须判断cur.next是否空指针，因为cur永远是原链表的位置，cur只要不为null
//            // cur.next必为复制节点，所以cur只要不为空，cur.next一定存在
//            cur = cur.next.next;
//        }
//
//        // 以下方法是断开原链表和复制链表,注意最后一个节点
//        cur = head;
//        Node copyHead = head.next;
//        Node copyCur = copyHead;
//        Node start = copyHead.next;
//        head.next = null;
//        int i = 1;
//        while (start != null) {
//            Node next = start.next;
//            if ((i & 1) == 1) {
//                cur.next = start;
//                cur = start;
//            } else {
//                copyCur.next = start;
//                copyCur = start;
//            }
//            i++;
//            start = next;
//        }
//        cur.next = null;
//        copyCur.next = null;
//        return copyHead;
//    }

}
