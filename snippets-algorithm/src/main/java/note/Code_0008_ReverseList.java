package note;

// 单链表反转
// TODO 补对数器
public class Code_0008_ReverseList {
    public static class Node {
        public Node next;
        public int value;

        public Node(int v, Node n) {
            value = v;
            next = n;
        }

        public Node(int v) {
            value = v;
        }
    }
    
    /**
     * 单链表反转
     * @param head 原链表的头节点
     * @return 新的头部节点
     */
    public static Node reverse(Node head) {
        Node next = null;
        Node pre = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }
}
