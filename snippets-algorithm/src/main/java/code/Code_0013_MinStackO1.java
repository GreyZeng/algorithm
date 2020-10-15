package code;

import java.util.Stack;

// TODO
// 待理解 左神写法
//实现一个特殊的栈，在基本功能的基础上，再实现返回栈中最小元素的功能，值范围是[-100000,100000]
//
//1. pop、push、getMin操作的时间复杂度都是 O(1)。
//2. 设计的栈类型可以使用现成的栈结构。
//3. 空间复杂度O(1)
public class Code_0013_MinStackO1 {
    private class MinStack {
        private final long offset;
        private Stack<Long> stack;

        public MinStack() {
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
