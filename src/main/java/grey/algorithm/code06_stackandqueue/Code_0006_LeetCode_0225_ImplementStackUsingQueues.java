package grey.algorithm.code06_stackandqueue;

import java.util.LinkedList;
import java.util.Queue;

// 使用一个队列实现栈
// 测评链接：https://leetcode.com/problems/implement-stack-using-queues/
// 笔记：https://www.cnblogs.com/greyzeng/p/16631644.html
public class Code_0006_LeetCode_0225_ImplementStackUsingQueues {

    class MyStack {
        // only use poll() and offer()
        // 尾部进，头部出

        private final Queue<Integer> queue;

        public MyStack() {
            queue = new LinkedList<>();
        }

        public void push(int x) {
            queue.offer(x);
            for (int i = 0; i < queue.size() - 1; i++) {
                queue.offer(queue.poll());
            }
        }

        public int pop() {
            return queue.poll();
        }

        public int top() {
            return queue.peek();
        }

        public boolean empty() {
            return queue.isEmpty();
        }
    }
}
