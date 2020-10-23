package leetcode;

public class LeetCode_0138_CopyListWithRandomPointer {

    public static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
 
    public static Node copyRandomList(Node head) {
        if (null == head) {
            return null;
        }

        Node c = head;
        // 1. 把每个copy节点依次连在每个节点的后面 ： a->b-c => a->a'->b-b'->c-c'
        while (c != null) {
            Node t = c.next;
            Node m = new Node(c.val);
            m.next = c.next;
            c.next = m;
            c = t;
        }
        // 依次连random指针
        c = head;
        while (c != null) {
            c.next.random = c.random == null ? null : c.random.next;
            c = c.next.next;
        }

        Node r = head.next;
        Node n = r;
        c = head;
        // 拆
        while (c != null && n != null) {
            Node x = c.next.next;
            Node y = null;
            if (x != null) {
                y = c.next.next.next;
            }
            c.next = x;
            n.next = y;
            c = c.next;
            n = n.next;
        }
        // 拆
        return r;

    }

    public static void main(String[] args) {
        Node c = new Node(3);
        c.next = new Node(4);
        c.next.next = new Node(5);
        c.next.next.next = new Node(6);
        Node node = copyRandomList(c);
        while (node != null) {
            System.out.println(node.val);
            node = node.next;
        }

    }

}
