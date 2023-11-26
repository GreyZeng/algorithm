package git.snippet.list;

// 用单链表实现栈和队列
// 测试代码见：
// Code_LinkedListToQueueAndStackTest
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
        private Node<V> h;
        private Node<V> t;
        private int size;

        public boolean isEmpty() {
            return size() == 0;
        }

        public int size() {
            return size;
        }

        // 头部进!!!
        public void offer(V value) {
            if (isEmpty()) {
                t = new Node<>(value);
                h = t;
            } else {
                Node<V> node = new Node<>(value);
                h.next = node;
                h = node;
            }
            size++;
        }

        // 尾部出
        public V poll() {
            if (!isEmpty()) {
                V r = t.val;
                t = t.next;
                size--;
                return r;
            }
            return null;
        }

        // 查看尾部数据
        public V peek() {
            return isEmpty() ? null : t.val;
        }
    }

    // 用单链表实现自定义栈
    // 头部进，头部出
    public static class MyStack<V> {
        private Node<V> h;
        private int size;

        public boolean isEmpty() {
            return size() == 0;
        }

        public int size() {
            return size;
        }

        public void push(V v) {

            if (isEmpty()) {
                h = new Node<>(v);
            } else {
                Node<V> newHead = new Node<>(v);
                newHead.next = h;
                h = newHead;

            }
            size++;
        }

        public V pop() {
            if (!isEmpty()) {
                size--;
                V r = h.val;
                h = h.next;
                return r;
            }
            return null;
        }

        public V peek() {
            if (!isEmpty()) {
                return h.val;
            }
            return null;
        }
    }
}
