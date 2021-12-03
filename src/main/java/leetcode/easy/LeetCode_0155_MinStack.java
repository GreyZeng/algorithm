package leetcode.easy;

import java.util.Stack;

public class LeetCode_0155_MinStack {
    public class MinStack {
        Stack<Integer> m;
        Stack<Integer> n;

        public MinStack() {
            m = new Stack<>();
            n = new Stack<>();
        }

        public void push(int x) {
            m.push(m.isEmpty() || m.peek() > x ? x : m.peek());
            n.push(x);
        }

        public void pop() {
            m.pop();
            n.pop();
        }

        public int top() {
            return n.peek();
        }

        public int getMin() {
            return m.peek();
        }
    }

}
