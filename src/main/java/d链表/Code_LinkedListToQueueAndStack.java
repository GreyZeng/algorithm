package d链表;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

// 用单链表实现栈和队列
public class Code_LinkedListToQueueAndStack {
    // 单链表的定义
    public static class Node<V> {
        public V val;
        public Node<V> next;

        public Node(V v) {
            val = v;
        }
    }

    // eg
    // a,b,c,d
    // a <- b <- c <- d
    // 其中
    // tail 指针指向 d
    // head 指针指向 a
    // 用单链表实现自定义队列
    // 头部进，尾部出
    public static class MyQueue<V> {
        private Node<V> head;
        private Node<V> tail;
        private int size;

        public MyQueue() {
            head = null;
            tail = null;
            size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size;
        }

        // 头部进
        public void offer(V value) {
            Node<V> newNode = new Node<>(value);
            if (isEmpty()) {
                size = 1;
                head = newNode;
                tail = newNode;
                return;
            }
            size++;
            head.next = newNode;
            head = newNode;
        }


        // 尾部出
        public V poll() {
            if (isEmpty()) {
                return null;
            }
            size--;
            V val = tail.val;
            if (head == tail) {
                head = null;
                tail = null;
                return val;
            }
            tail = tail.next;
            return val;
        }

        // 查看尾部数据
        public V peek() {
            return isEmpty() ? null : tail.val;
        }

    }

    // 用单链表实现自定义栈
    // 头部进，头部出
    public static class MyStack<V> {
        private Node<V> head;
        private int size;

        public MyStack() {
            head = null;
            size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size;
        }

        public void push(V value) {
            size++;
            Node<V> node = new Node<>(value);
            node.next = head;
            head = node;
        }

        public V pop() {
            if (isEmpty()) {
                return null;
            }
            Node<V> ans = head;
            size--;
            if (isEmpty()) {
                head = null;
            } else {
                head = head.next;
            }
            return ans.val;
        }

        public V peek() {
            if (isEmpty()) {
                return null;
            }
            return head.val;
        }

    }

    public static void testQueue2() {
        MyQueue<Integer> myQueue = new MyQueue<>();
        myQueue.offer(1);
        myQueue.offer(2);
        myQueue.offer(3);
        myQueue.offer(4);
        System.out.println(myQueue.peek());
        System.out.println(myQueue.poll());
        System.out.println(myQueue.poll());
        System.out.println(myQueue.poll());
        System.out.println(myQueue.poll());
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
