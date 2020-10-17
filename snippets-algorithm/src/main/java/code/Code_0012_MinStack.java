package code;

import java.util.Stack;

//实现一个特殊的栈，在基本功能的基础上，再实现返回栈中最小元素的功能(LeetCode 155)
//
//1. pop、push、getMin操作的时间复杂度都是 O(1)。
//2. 设计的栈类型可以使用现成的栈结构。
// 解决方案: 使用两个栈
public class Code_0012_MinStack {
    public static class MinStack {
        private Stack<Integer> m;
        private Stack<Integer> n;

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
