package lintcode;

import java.util.Stack;
// 三个原则
// 一次性导完
// 只有pop stack空了才能导数据
// pop stack不为空不用导数据
public class LintCode_0040_ImplementQueueByTwoStacks {
    public class MyQueue {

        private Stack<Integer> popStack;
        private Stack<Integer> pushStack;

        public MyQueue() {
            popStack = new Stack<>();
            pushStack = new Stack<>();
        }

        public void push(int element) {
            pushStack.push(element);
        }

        public int pop() {
            int result = 0;
            while (!pushStack.isEmpty()) {
                result = pushStack.pop();
                if (!pushStack.isEmpty()) {
                    popStack.push(result);
                }
            }
            while (!popStack.isEmpty()) {
                pushStack.push(popStack.pop());
            }
            return result;
        }

        public int top() {
            int result = 0;
            while (!pushStack.isEmpty()) {
                result = pushStack.pop();
                popStack.push(result);
            }
            while (!popStack.isEmpty()) {
                pushStack.push(popStack.pop());
            }
            return result;
        }
    }
}
