package leetcode;


import java.util.Stack;

// 使用栈来实现队列
public class LeetCode_0232_ImplementQueueUsingStacks {
    class MyQueue {
        private Stack<Integer> push;
        private Stack<Integer> pop;

        /**
         * Initialize your data structure here.
         */
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


        /**
         * Push element x to the back of queue.
         */
        public void push(int x) {
            push.push(x);
            pushToPop();
        }

        /**
         * Removes the element from in front of queue and returns that element.
         */
        public int pop() {
            pushToPop();
            return pop.pop();
        }

        /**
         * Get the front element.
         */
        public int peek() {
            pushToPop();
            return pop.peek();
        }

        /**
         * Returns whether the queue is empty.
         */
        public boolean empty() {
            return push.isEmpty() && pop.isEmpty();
        }
    }

}
