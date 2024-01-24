package git.snippet.queueandstack;

import java.util.LinkedList;
import java.util.Queue;

// 使用一个队列实现栈
// 测评链接：https://leetcode.com/problems/implement-stack-using-queues/
// 笔记：https://www.cnblogs.com/greyzeng/p/16631644.html
public class LeetCode_0225_ImplementStackUsingQueues {
    class MyStack {
        // only use poll() and offer()
        private final Queue<Integer> data;

        public MyStack() {
            data = new LinkedList<>();
        }

        // O(n)
        public void push(int x) {
            int size = data.size();
            data.offer(x);
            // 其他元素都移动到 x 后面
            for (int i = 0; i < size; i++) {
                data.offer(data.poll());
            }
        }

        public int pop() {
            return data.poll();
        }

        public int top() {
            return data.peek();
        }

        public boolean empty() {
            return data.isEmpty();
        }
    }
}
