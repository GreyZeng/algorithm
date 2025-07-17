package grey.algorithm;

import java.util.Stack;

// 最小栈
// 笔记：https://www.cnblogs.com/greyzeng/p/16631644.html
// https://leetcode.com/problems/min-stack
public class Code_0011_LeetCode_0155_MinStack {

    class MinStack1 {

        private final Stack<Integer> data;
        private final Stack<Integer> min;

        public MinStack1() {
            data = new Stack<>();
            min = new Stack<>();
        }

        public void push(int val) {
            data.push(val);
            min.push(min.isEmpty() || val <= min.peek() ? val : min.peek());
        }

        public void pop() {
            if (!data.isEmpty()) {
                data.pop();
                // data不为空，min必然不为空
                min.pop();
            }
        }

        public int top() {
            return data.peek();
        }

        public int getMin() {
            return min.peek();
        }
    }

    // 用数组来替换栈，优化性能
    // 前提是必须要确定数据量的大小
    class MinStack2 {

        private int size;
        private int[] data;
        private int[] min;
        // 根据数据量不一样，这个值要定不同的
        private int MAXN = 8001;

        public MinStack2() {
            data = new int[MAXN];
            min = new int[MAXN];
        }

        public void push(int val) {
            if (size == 0) {
                data[0] = val;
                min[0] = val;

            } else {
                data[size] = val;
                min[size] = min[size - 1] < val ? min[size - 1] : val;

            }
            size++;
        }

        // 保证栈里有数据才能调用这个方法
        public void pop() {
            // 调用之前 size一定不能等于0
            size--;
        }

        // 保证栈里有数据才能调用这个方法
        public int top() {
            // 调用之前 size一定不能等于0
            return data[size - 1];
        }

        public int getMin() {
            return min[size - 1];
        }
    }

    // TODO
    // 位运算优化版
    class MinStack01 {

        private final long offset;
        private final Stack<Long> stack;

        public MinStack01() {
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
}
