package code;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 如何用队列实现栈 经过对数器验证
 * TODO 自己补充对数器
 */
public class Code_0014_QueueToStack {
    public static class MyStack<T> {
        private Queue<T> queue;
        private Queue<T> help;
        private int size;

        public MyStack() {
            queue = new LinkedList<>();
            help = new LinkedList<>();
        }

        public T pop() {
            if (isEmpty()) {
                throw new RuntimeException("no data to pop");
            }
            size--;
            for (int i = 0; i < size; i++) {
                help.offer(queue.poll());
            }
            T result = queue.poll();
            Queue<T> t = queue;
            queue = help;
            help = t;
            return result;
        }

        public void push(T value) {
            queue.offer(value);
            size++;
        }

        public T peek() {
            if (isEmpty()) {
                throw new RuntimeException("no data to peek");
            }
            T ans = null;
            for (int i = 0; i < size; i++) {
                T result = queue.poll();
                if (i == size - 1) {
                    ans = result;
                }
                help.offer(result);
            }
            Queue<T> t = queue;
            queue = help;
            help = t;
            return ans;
        }

        public int size() {
            return size;
        }

        public boolean isEmpty() {
            return size() == 0;
        }
    }
}
