package git.snippet.queueandstack;

// 用双向链表实现双端队列
// notes: https://www.cnblogs.com/greyzeng/p/16631644.html
// https://leetcode.com/problems/design-circular-deque
public class LeetCode_0641_DesignCircularDeque {

    class MyCircularDeque {
        Node head;
        Node tail;
        int size;
        int capacity;
        public MyCircularDeque(int k) {
            capacity = k;
        }

        public boolean insertFront(int value) {
            if (!isFull()) {
                if (head == null) {
                    head = new Node(value);
                    tail = head;
                } else {
                    Node newHead = new Node(value);
                    head.last = newHead;
                    newHead.next = head;
                    head = newHead;
                }
                size++;
                return true;
            }
            return false;
        }

        public boolean insertLast(int value) {
            if (!isFull()) {
                if (head == null) {
                    head = new Node(value);
                    tail = head;
                } else {
                    Node newTail = new Node(value);
                    tail.next = newTail;
                    newTail.last = tail;
                    tail = newTail;
                }
                size++;
                return true;
            }
            return false;
        }

        public boolean deleteFront() {
            if (!isEmpty()) {
                if (size == 1) {
                    head = null;
                    tail = null;
                    size = 0;
                    return true;
                } else {
                    Node t = head.next;
                    t.last = null;
                    head = t;
                    size--;
                }
                return true;
            }
            return false;
        }

        public boolean deleteLast() {
            if (!isEmpty()) {
                if (size == 1) {
                    head = null;
                    tail = null;
                    size = 0;
                    return true;
                } else {
                    Node t = tail.last;
                    t.next = null;
                    tail = t;
                    size--;
                }
                return true;
            }
            return false;
        }

        public int getFront() {
            return isEmpty() ? -1 : head.v;
        }

        public int getRear() {
            return isEmpty() ? -1 : tail.v;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return capacity == size;
        }

        class Node {
            public int v;
            public Node next;
            public Node last;

            public Node(int v) {
                this.v = v;
            }
        }
    }
}
