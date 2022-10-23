package leetcode.easy;

import java.util.LinkedList;
import java.util.Queue;

// 使用队列实现栈
// 笔记：https://www.cnblogs.com/greyzeng/p/16631644.html
public class LeetCode_0225_ImplementStackUsingQueues {
    class MyStack {
        private Queue<Integer> data;
        private Queue<Integer> help;

        public MyStack() {
            data = new LinkedList<>();
            help = new LinkedList<>();
        }

        // 从尾部进
        public void push(int x) {
            data.offer(x);
        }

        public int pop() {
            int result = 0;
            while (!data.isEmpty()) {
                int x = data.poll();
                if (data.isEmpty()) {
                    result = x;
                } else {
                    help.offer(x);
                }
            }
            Queue<Integer> t = data;
            data = help;
            help = t;
            return result;
        }

        public int top() {
            int result = 0;
            while (!data.isEmpty()) {
                int x = data.poll();
                help.offer(x);
                if (data.isEmpty()) {
                    result = x;
                }
            }
            Queue<Integer> t = data;
            data = help;
            help = t;
            return result;
        }

        public boolean empty() {
            return data.isEmpty();
        }
    }
}
