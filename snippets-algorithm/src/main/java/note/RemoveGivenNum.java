package note;

public class RemoveGivenNum {
    public static class Node {
        public Node next;
        public int v;

        public Node(int v) {
            this.v = v;
        }
    }
    
    public static Node removeGivenNum(Node head, int num) {
        if (head == null) {
            return null;
        }
        // 找到第一个不需要删除的节点
        // 防止要删的节点就是头节点以及头节点下面的一批节点
        while (head != null) {
            if (head.v != num) {
                break;
            }
            head = head.next;
        }
        Node pre = head;
        Node cur = head;
        while (cur != null) {
            if (cur.v == num) {
                pre.next = cur.next;
                pre = cur;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }

    public static void printList(Node head) {
        while (head != null) {
            System.out.print(head.v + "  ");
            head = head.next;
        }
        System.out.println();
    }
    public static void main(String[] args) {
        // Node head = new Node(1);
        // head.next = new Node(2);
        // head.next.next = new Node(3);
        // head.next.next.next = new Node(4);
        // head.next.next.next.next = new Node(5);
        // head.next.next.next.next.next = new Node(6);
        // printList(head);
        // removeGivenNum(head, 3);
        // printList(head);



        Node head = new Node(1);
        head.next = new Node(1);
        head.next.next = new Node(1);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);
        printList(head);
        head = removeGivenNum(head, 1);
        printList(head);

    }
}
