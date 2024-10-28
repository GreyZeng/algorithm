package grey.algorithm;

import java.util.ArrayList;

// 数组实现栈和队列
// 笔记：https://www.cnblogs.com/greyzeng/p/16631644.html
public class Code_0011_ArrayToStackAndQueue {

    public static final class MyStack<T> {
        private ArrayList<T> queue;

        public MyStack() {
            queue = new ArrayList<>();
        }

        public void push(T value) {
            queue.add(value);
        }

        public T pop() {
            if (null == queue || isEmpty()) {
                return null;
            }
            return queue.remove(queue.size() - 1);
        }

        public boolean isEmpty() {
            return queue.isEmpty();
        }
    }

    public static final class MyQueue<T> {
        private ArrayList<T> queue;

        public MyQueue() {
            queue = new ArrayList<>();
        }

        public void push(T value) {
            queue.add(value);
        }

        public T poll() {
            if (isEmpty()) {
                return null;
            }
            return queue.remove(0);
        }

        public boolean isEmpty() {
            return queue.isEmpty();
        }
    }
}
