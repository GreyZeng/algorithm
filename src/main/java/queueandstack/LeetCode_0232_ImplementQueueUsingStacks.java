package queueandstack;

import java.util.Stack;

// 使用栈实现队列
// 三个原则
// 1. 一次性导完
// 2. 只有pop stack空了才能导数据
// 3. pop stack不为空不用导数据
// https://leetcode.com/problems/implement-queue-using-stacks/
// 笔记：https://www.cnblogs.com/greyzeng/p/16631644.html
public class LeetCode_0232_ImplementQueueUsingStacks {
    class MyQueue {
        private final Stack<Integer> push;
        private final Stack<Integer> pop;

        public MyQueue() {
            push = new Stack<>();
            pop = new Stack<>();
        }

        public void push(int x) {
            push.push(x);
        }

        public int pop() {
            while (!push.isEmpty()) {
                pop.push(push.pop());
            }
            int result = pop.pop();
            while (!pop.isEmpty()) {
                push.push(pop.pop());
            }
            return result;
        }

        public int peek() {
            while (!push.isEmpty()) {
                pop.push(push.pop());
            }
            int result = pop.peek();
            while (!pop.isEmpty()) {
                push.push(pop.pop());
            }
            return result;
        }

        public boolean empty() {
            return push.isEmpty();
        }
    }

}
