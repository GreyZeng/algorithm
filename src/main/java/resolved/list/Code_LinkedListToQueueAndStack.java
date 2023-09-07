package resolved.list;

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
            return size() == 0;
        }

        public int size() {
            return size;
        }

        // 头部进!!!
        public void offer(V value) {
            if (isEmpty()) {
                head = tail = new Node<>(value);
            } else {
                Node<V> node = new Node<>(value);
                head.next = node;
                head = node;
            }
            size++;
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

        public void push(V v) {
            Node<V> newNode = new Node<>(v);
            if (isEmpty()) {
                head = newNode;
                size = 1;
            } else {
                newNode.next = head;
                head = newNode;
                size++;
            }
        }

        public V pop() {
            if (isEmpty()) {
                return null;
            }
            size--;
            V v = head.val;
            // 只有一个节点
            if (head.next == null) {
                head = null;
                return v;
            }
            head = head.next;
            return v;
        }

        public V peek() {
            return isEmpty() ? null : head.val;
        }

    }


}
