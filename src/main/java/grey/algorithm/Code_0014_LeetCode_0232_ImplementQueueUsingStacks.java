package grey.algorithm;

import java.util.Stack;

// 使用栈实现队列
// https://leetcode.com/problems/implement-queue-using-stacks/
// 笔记：https://www.cnblogs.com/greyzeng/p/16631644.html
public class Code_0014_LeetCode_0232_ImplementQueueUsingStacks {
    class MyQueue {
        private final Stack<Integer> data;
        private final Stack<Integer> help;

        public MyQueue() {
        	data = new Stack<>();
        	help = new Stack<>();
        }

        public void push(int x) {
        	data.add(x);
        }
        // 均摊的时间复杂度是O(1)
        // 每个位置最多进两次，出两次
        public int pop() {
        	if (empty()) {
        		return -1;
        	}
        	while (!empty()) {
            	help.add(data.pop());
            }
            int value = help.pop();
            while (!help.isEmpty()) {
            	data.add(help.pop());
            }
            return value;
        }

        public int peek() {
        	if (empty()) {
        		return -1;
        	}
        	while (!empty()) {
            	help.add(data.pop());
            }
            int value = help.peek();
            while (!help.isEmpty()) {
            	data.add(help.pop());
            }
            return value;
        }

        public boolean empty() {
            return data.isEmpty();
        }
    }
}
