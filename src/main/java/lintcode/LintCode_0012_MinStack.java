package lintcode;

import java.util.Stack;

//实现一个特殊的栈，在基本功能的基础上，再实现返回栈中最小元素的功能(LeetCode 155)
//
//1. pop、push、getMin操作的时间复杂度都是 O(1)。
//2. 设计的栈类型可以使用现成的栈结构。
// 解决方案: 使用两个栈
public class LintCode_0012_MinStack {
    public static class MinStack {
        private Stack<Integer> stack;
        private Stack<Integer> min;
        public MinStack() {
            stack = new Stack<>();
            min = new Stack<>();
        }
    
        /*
         * @param number: An integer
         * @return: nothing
         */
        public void push(int number) {
            stack.push(number);
            if (min.isEmpty() ) {
                min.push(number);
            } else if (min.peek() <= number) {
                min.push(min.peek());
            } else {
                min.push(number);
            }
        }
    
        /*
         * @return: An integer
         */
        public int pop() {
            min.pop();
            return stack.pop();
        }
    
        /*
         * @return: An integer
         */
        public int min() {
            return min.peek();
        }
    }
    public static void main(String[] args) {
        MinStack stack = new MinStack();
        stack.push(1);
        stack. pop();
        stack.push(2);
        stack.push(3);
        stack.min();
        stack.push(1);
        stack.min();

    }
}
