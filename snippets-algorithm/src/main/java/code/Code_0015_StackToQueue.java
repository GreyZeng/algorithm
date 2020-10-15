package code;

import java.util.Stack;

/**
 * 如何用栈来实现队列 经过对数器验证
 * TODO 自己补充对数器
 */
public class Code_0015_StackToQueue {
    public static class MyQueue<T> {
        private Stack<T> push;
        private Stack<T> pop;
        private int size;

        public MyQueue() {
            push = new Stack<>();
            pop = new Stack<>();
        }

        public void add(T v) {
            push.push(v);
            size++;
        }

        public T poll() {
            if (isEmpty()) {
                throw new RuntimeException("no data to poll");
            }
            size--;
            for (int i = 0; i < size; i++) {
                pop.push(push.pop());
            }
            T result = push.pop();
            for (int i = 0; i < size; i++) {
                push.push(pop.pop());
            }
            return result;
        }

        public T peek() {
            if (isEmpty()) {
                throw new RuntimeException("no data to peek");
            }

            for (int i = 0; i < size - 1; i++) {
                pop.push(push.pop());
            }
            T result = push.peek();
            pop.push(push.pop());
            for (int i = 0; i < size; i++) {
                push.push(pop.pop());
            }
            return result;
        }

        public int size() {
            return size;
        }

        public boolean isEmpty() {
            return size() == 0;
        }
    }

}
