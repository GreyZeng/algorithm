package code;


/**
 * 给定两个可能有环也可能无环的单链表，头节点head1和head2。请实现一个函数，如果两个链表相交，请返回相交的 第一个节点。如果不相交，返回null
 * 【要求】
 * 如果两个链表长度之和为N，时间复杂度请达到O(N)，额外空间复杂度 请达到O(1)。
 */
public class Code_0037_FindFirstIntersectNode {
    public static class Node {
        public int value;
        public Node next;

        public Node(int v) {
            value = v;
        }
    }

    public static Node getIntersectNode(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }

        // 两个均无环
        Node loop1 = getLoopNode(head1);
        Node loop2 = getLoopNode(head2);
        if (loop1 == null && loop2 == null) {
            return noLoop(head1, head2);
        }
        // 两个均有环
        if (loop1 != null && loop2 != null) {
            return bothLoop(head1, loop1, head2, loop2);
        }

        // 一个有环一个无环 ,不可能相交
        return null;
    }

    // 找到链表第一个入环节点，如果无环，返回null
    public static Node getLoopNode(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        // 慢指针 在第一个节点位置
        Node slow = head.next;
        // 快指针，在第二个节点的位置
        Node fast = head.next.next;

        while (slow != fast) {
            if (fast.next == null || fast.next.next == null) {
                return null;
            }
            // 快指针每次走两步
            fast = fast.next.next;
            // 慢指针每次走一步
            slow = slow.next;
        }
        // 两个指针遇上了，说明有环

        // 让快指针回到头部， 慢指针停在原地
        fast = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }


        // 快指针每次走一步，慢指针每次走一步，遇上后，就是入环节点处
        return slow;
    }

    // 如果两个链表都无环，返回第一个相交节点，如果不想交，返回null
    public static Node noLoop(Node head1, Node head2) {
        if (head1 == null || head1 == null) {
            return null;
        }
        // 判断两个链表的长度


        int n = 0;
        Node t1 = head1;
        Node t2 = head2;
        while (t1.next != null) {
            n++;
            t1 = t1.next;
        }


        while (t2.next != null) {
            n--;
            t2 = t2.next;
        }

        // 两个链表的末节点不相等
        if (t2 != t1) {
            return null;
        }
        // 记录长的链表头节点
        Node longer = n > 0 ? head1 : head2;
        // 记录短的链表头节点
        Node shorter = longer == head1 ? head2 : head1;
        // 先让长链表走一段距离（这段的长度就是长链表和短链表的长度差）
        int gap = Math.abs(n);
        while (gap != 0) {
            gap--;
            longer = longer.next;
        }
        // 然后长链表和短链表同时开始走，直到相等的节点即为交点
        while (longer != shorter) {
            longer = longer.next;
            shorter = shorter.next;
        }
        return shorter;
    }

    // 两个有环链表，返回第一个相交节点，如果不想交返回null
    public static Node bothLoop(Node head1, Node loop1, Node head2, Node loop2) {
        // 只有两种情况

        if (loop1 == loop2) {
            // 1. 未入环就相交
            // 这种情况下，两个链表的入环节点是一样
            int n = 0;
            Node t1 = head1;
            Node t2 = head2;
            while (t1 != loop1) {
                n++;
                t1 = t1.next;
            }
            while (t2 != loop2) {
                n--;
                t2 = t2.next;
            }

            Node longer = n > 0 ? head1 : head2;
            Node shorter = longer == head1 ? head2 : head1;
            n = Math.abs(n);
            while (n != 0) {
                n--;
                longer = longer.next;
            }
            while (longer != shorter) {
                longer = longer.next;
                shorter = shorter.next;
            }
            return shorter;
        } else {
            // 2. 共用环，不在入环处相交，随便一个链表的入环点就是交点
            // loop1 != loop2
            // 从loop1开始，转一圈回到loop1
            // 如果都没有遇到loop2，则不相交
            // 如果遇到了loop1，则交点为loop1或者loop2都可以

            Node t1 = loop1.next;
            while (t1!=loop1) {
                if (t1 == loop2) {
                    return loop1;
                }
                t1 = t1.next;
            }
            return null;

        }
    }

    public static void main(String[] args) {
        // 1->2->3->4->5->6->7->null
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);

        // 0->9->8->6->7->null
        Node head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).value);

        // 1->2->3->4->5->6->7->4...
        head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);
        head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

        // 0->9->8->2...
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next; // 8->2
        System.out.println(getIntersectNode(head1, head2).value);

        // 0->9->8->6->4->5->6..
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).value);

    }
}
