package leetcode;

import java.util.Stack;

// 使用栈来实现队列
public class LeetCode_0232_ImplementQueueUsingStacks {
    class MyQueue {
        private Stack<Integer> push;
        private Stack<Integer> pop;

        public MyQueue() {
            push = new Stack<>();
            pop = new Stack<>();
        }

        private void pushToPop() {
            if (pop.isEmpty()) {
                while (!push.isEmpty()) {
                    pop.push(push.pop());
                }
            }
        }

        public void push(int x) {
            push.push(x);
            pushToPop();
        }

        public int pop() {
            pushToPop();
            return pop.pop();
        }

        public int peek() {
            pushToPop();
            return pop.peek();
        }

        public boolean empty() {
            return push.isEmpty() && pop.isEmpty();
        }
    }

}
