package d栈和队列;


import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 自定义的双向链表实现栈和队列
 *
 * @link <a href="https://www.cnblogs.com/greyzeng/p/16631644.html">笔记</a>
 * @see LeetCode_0641_DesignCircularDeque
 */
public class Code_DoubleEndsToStackAndQueue {
    private final static class Node<V> {
        public V data;
        public Node<V> next;
        public Node<V> last;

        public Node(V data) {
            this.data = data;
        }
    }

    public final static class DoubleEndsQueue<T> {
        public Node<T> head;
        public Node<T> tail;

        public void addFromHead(T value) {
            Node<T> node = new Node<>(value);
            if (head == null) {
                tail = node;
            } else {
                node.next = head;
                head.last = node;
            }
            head = node;
        }

        public void addFromBottom(T value) {
            Node<T> node = new Node<>(value);
            if (tail == null) {
                head = node;
            } else {
                tail.next = node;
                node.last = tail;
            }
            tail = node;
        }

        public T popFromHead() {
            if (null == head || tail == null) {
                return null;
            }
            T data = head.data;
            if (head == tail) {
                head = null;
                tail = null;
                return data;
            }
            head = head.next;
            head.last = null;

            return data;

        }

        public T popFromBottom() {
            if (tail == null || head == null) {
                return null;
            }
            T data = tail.data;
            if (tail == head) {
                tail = null;
                head = null;
                return data;
            }
            tail = tail.last;
            tail.next = null;

            return data;
        }

        public boolean isEmpty() {
            return head == null || tail == null;
        }

    }

    public final static class MyStack<T> {
        private DoubleEndsQueue<T> queue;
        private int size;

        public MyStack() {
            size = 0;
            queue = new DoubleEndsQueue<>();
        }

        public void push(T value) {
            size++;
            queue.addFromHead(value);
        }

        public T pop() {
            if (null == queue || isEmpty()) {
                return null;
            }
            size--;
            return queue.popFromHead();
        }

        public int size() {
            return size;
        }

        public boolean isEmpty() {
            return size() == 0;
        }

        public T peek() {
            if (isEmpty()) {
                return null;
            }
            return queue.head.data;
        }
    }

    public final static class MyQueue<T> {
        private DoubleEndsQueue<T> queue;
        private int size;

        public MyQueue() {
            size = 0;
            queue = new DoubleEndsQueue<>();
        }


        public void offer(T value) {
            if (null == queue) {
                return;
            }
            size++;
            queue.addFromHead(value);
        }

        public T poll() {
            if (isEmpty()) {
                return null;
            }
            size--;
            return queue.popFromBottom();
        }

        public T peek() {
            if (isEmpty()) {
                return null;
            }
            return queue.tail.data;
        }

        public int size() {
            return size;
        }

        public boolean isEmpty() {
            return size() == 0;
        }
    }

    public static void testQueue() {
        MyQueue<Integer> myQueue = new MyQueue<>();
        Queue<Integer> test = new LinkedList<>();
        int testTime = 5000000;
        int maxValue = 200000000;
        System.out.println("测试开始！");
        for (int i = 0; i < testTime; i++) {
            if (myQueue.isEmpty() != test.isEmpty()) {
                System.out.println("Oops!");
            }
            if (myQueue.size() != test.size()) {
                System.out.println("Oops!");
            }
            double decide = Math.random();
            if (decide < 0.33) {
                int num = (int) (Math.random() * maxValue);
                myQueue.offer(num);
                test.offer(num);
            } else if (decide < 0.66) {
                if (!myQueue.isEmpty()) {
                    int num1 = myQueue.poll();
                    int num2 = test.poll();
                    if (num1 != num2) {
                        System.out.println("Oops!");
                    }
                }
            } else {
                if (!myQueue.isEmpty()) {
                    int num1 = myQueue.peek();
                    int num2 = test.peek();
                    if (num1 != num2) {
                        System.out.println("Oops!");
                    }
                }
            }
        }
        if (myQueue.size() != test.size()) {
            System.out.println("Oops!");
        }
        while (!myQueue.isEmpty()) {
            int num1 = myQueue.poll();
            int num2 = test.poll();
            if (num1 != num2) {
                System.out.println("Oops!");
            }
        }
        System.out.println("测试结束！");
    }

    public static void testStack() {
        MyStack<Integer> myStack = new MyStack<>();
        Stack<Integer> test = new Stack<>();
        int testTime = 5000000;
        int maxValue = 200000000;
        System.out.println("测试开始！");
        for (int i = 0; i < testTime; i++) {
            if (myStack.isEmpty() != test.isEmpty()) {
                System.out.println("Oops!");
            }
            if (myStack.size() != test.size()) {
                System.out.println("Oops!");
            }
            double decide = Math.random();
            if (decide < 0.33) {
                int num = (int) (Math.random() * maxValue);
                myStack.push(num);
                test.push(num);
            } else if (decide < 0.66) {
                if (!myStack.isEmpty()) {
                    int num1 = myStack.pop();
                    int num2 = test.pop();
                    if (num1 != num2) {
                        System.out.println("Oops!");
                    }
                }
            } else {
                if (!myStack.isEmpty()) {
                    int num1 = myStack.peek();
                    int num2 = test.peek();
                    if (num1 != num2) {
                        System.out.println("Oops!");
                    }
                }
            }
        }
        if (myStack.size() != test.size()) {
            System.out.println("Oops!");
        }
        while (!myStack.isEmpty()) {
            int num1 = myStack.pop();
            int num2 = test.pop();
            if (num1 != num2) {
                System.out.println("Oops!");
            }
        }
        System.out.println("测试结束！");
    }

    public static void main(String[] args) {
        testQueue();
        testStack();
    }
}
