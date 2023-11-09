package git.snippet.queueandstack;

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
        private Stack<Integer> popStack;
        private Stack<Integer> pushStack;

        public MyQueue() {
            popStack = new Stack<>();
            pushStack = new Stack<>();
        }

        public void push(int x) {
            pushStack.push(x);
        }

        public int pop() {
            while (!pushStack.isEmpty()) {
                popStack.push(pushStack.pop());
            }
            int v = popStack.pop();
            while (!popStack.isEmpty()) {
                pushStack.push(popStack.pop());
            }
            return v;
        }

        public int peek() {
            while (!pushStack.isEmpty()) {
                popStack.push(pushStack.pop());
            }
            int v = popStack.peek();
            while (!popStack.isEmpty()) {
                pushStack.push(popStack.pop());
            }
            return v;
        }

        public boolean empty() {
            return pushStack.isEmpty();
        }
    }
}
