package queueandstack;

// 用双向链表实现双端队列
// notes: https://www.cnblogs.com/greyzeng/p/16631644.html
// https://leetcode.com/problems/design-circular-deque
public class LeetCode_0641_DesignCircularDeque {

    class MyCircularDeque {
        // 双向链表
        class DoubleNode {
            public int val;
            public DoubleNode next;
            public DoubleNode last;

            public DoubleNode(int v) {
                val = v;
            }
        }

        // 头节点
        DoubleNode head;
        // 尾节点
        DoubleNode tail;
        // 当前元素数量
        int size;
        // 容量
        int capacity;

        public MyCircularDeque(int k) {
            capacity = k;
        }

        // 头部进
        public boolean insertFront(int value) {
            if (isFull()) {
                return false;
            }
            if (isEmpty()) {
                // 链表是空的，先初始化
                head = tail = new DoubleNode(value);
            } else {
                // 新建节点
                DoubleNode newHead = new DoubleNode(value);
                newHead.next = head;
                head.last = newHead;
                head = newHead;
            }
            size++;
            return true;
        }

        // 尾部进
        public boolean insertLast(int value) {
            if (isFull()) {
                return false;
            }
            if (isEmpty()) {
                head = tail = new DoubleNode(value);
            } else {
                DoubleNode rearNode = new DoubleNode(value);
                tail.next = rearNode;
                rearNode.last = tail;
                tail = rearNode;
            }
            size++;
            return true;
        }

        // 头部出
        public boolean deleteFront() {
            if (isEmpty()) {
                return false;
            }
            if (size == 1) {
                head = tail = null;
            } else {
                // 不止一个元素
                DoubleNode newHead = head.next;
                newHead.last = null;
                head = newHead;
            }
            size--;
            return true;
        }

        // 尾部出
        public boolean deleteLast() {
            if (isEmpty()) {
                return false;
            }
            if (size == 1) {
                head = tail = null;
            } else {
                DoubleNode newRear = tail.last;
                newRear.next = null;
                tail = newRear;
            }
            size--;
            return true;
        }

        // 获取头部元素
        public int getFront() {
            if (isEmpty()) {
                return -1;
            } else {
                return head.val;
            }
        }

        // 获取尾部元素
        public int getRear() {
            if (isEmpty()) {
                return -1;
            } else {
                return tail.val;
            }
        }

        // 判断链表是否为空
        public boolean isEmpty() {
            return size == 0;
        }

        // 判断链表是否满
        public boolean isFull() {
            return size == capacity;
        }
    }
}
