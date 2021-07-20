package leetcode;

import java.util.LinkedList;
import java.util.Queue;

// 使用队列实现栈
public class LeetCode_0225_ImplementStackUsingQueues {
    class MyStack {
        private Queue<Integer> data;
        private Queue<Integer> help;

        public MyStack() {
            data = new LinkedList<>();
            help = new LinkedList<>();
        }

        public void push(int x) {
            data.offer(x);
        }

        public int pop() {
            while (data.size() > 1) {
                help.offer(data.poll());
            }
            int r = data.poll();
            Queue<Integer> t = data;
            data = help;
            help = t;
            return r;
        }

        public int top() {
            while (data.size() > 1) {
                help.offer(data.poll());
            }
            int r = data.poll();
            help.offer(r);
            Queue<Integer> t = data;
            data = help;
            help = t;
            return r;
        }

        public boolean empty() {
            return data.isEmpty();
        }
    }

}
