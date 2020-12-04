package code;

import java.util.ArrayList;

/**
 * 数组实现栈和队列
 */ 
public class Code_0006_ArrayToStackAndQueue {

    public final static class MyStack<T> {
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

    public final static class MyQueue<T> {
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
