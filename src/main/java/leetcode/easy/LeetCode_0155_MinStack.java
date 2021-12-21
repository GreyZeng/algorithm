package leetcode.easy;

import java.util.Stack;

// https://leetcode-cn.com/problems/min-stack
public class LeetCode_0155_MinStack {
    static class MinStack {
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

    static class MinStackO1 {
        private final long offset;
        private final Stack<Long> stack;

        public MinStackO1() {
            // 二进制：11111111111111111111111111111111
            offset = 4294967295L;
            stack = new Stack<>();
        }

        public boolean isEmpty() {
            return stack.isEmpty();
        }

        public void push(int num) {
            long left = ((long) (num) << 32);
            long right;
            if (isEmpty()) {
                right = ((long) (num) & offset);
            } else {
                int min = Math.min(num, getMin());
                right = ((long) (min) & offset);
            }
            // left 存 stack1中的值
            // right为对应的stack2中的值，即min值
            stack.push((left | right));
        }

        public int pop() {
            long out = stack.pop();
            return (int) (out >>> 32);
        }

        public int top() {
            long peek = stack.peek();
            return (int) (peek >>> 32);
        }

        public int getMin() {
            long peek = stack.peek();
            return (int) (peek & offset);
        }
    }

    public static void main(String[] args) {
        long offset = 4294967295L;
        System.out.println(Long.toBinaryString(offset));
    }

}
