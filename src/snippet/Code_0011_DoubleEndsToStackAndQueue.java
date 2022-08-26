package snippet;

// 双向链表实现栈和队列
public class Code_0011_DoubleEndsToStackAndQueue {
    private final static class Node<T> {
        public T data;
        public Node<T> next;
        public Node<T> last;

        public Node(T data) {
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

        public MyStack() {
            queue = new DoubleEndsQueue<T>();
        }

        public void push(T value) {
            queue.addFromHead(value);
        }

        public T pop() {
            if (null == queue || isEmpty()) {
                return null;
            }
            return queue.popFromHead();
        }

        public boolean isEmpty() {
            return queue.isEmpty();
        }

    }

    public final static class MyQueue<T> {
        private DoubleEndsQueue<T> queue;

        public MyQueue() {
            queue = new DoubleEndsQueue<>();
        }

        public void push(T value) {
            if (null == queue) {
                return;
            }
            queue.addFromHead(value);
        }

        public T poll() {
            if (isEmpty()) {
                return null;
            }
            return queue.popFromBottom();
        }

        public boolean isEmpty() {
            return queue.isEmpty();
        }
    }
}
