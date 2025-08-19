package grey.algorithm.class06_stackandqueue;

import java.util.Stack;

// 使用栈实现队列
// https://leetcode.com/problems/implement-queue-using-stacks/
// 笔记：https://www.cnblogs.com/greyzeng/p/16631644.html
public class Code_0011_LeetCode_0232_ImplementQueueUsingStacks {

    class MyQueue {

        private final Stack<Integer> stack1;
        private final Stack<Integer> stack2;

        public MyQueue() {
            stack1 = new Stack<>();
            stack2 = new Stack<>();
        }

        public void push(int x) {
            stack1.push(x);
        }

        public int pop() {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
            int value = stack2.pop();
            while (!stack2.isEmpty()) {
                stack1.push(stack2.pop());
            }
            return value;
        }

        public int peek() {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
            int value = stack2.peek();
            while (!stack2.isEmpty()) {
                stack1.push(stack2.pop());
            }
            return value;
        }

        public boolean empty() {
            return stack1.isEmpty();
        }
    }
}
