package leetcode.easy;

import java.util.Stack;

// 使用栈实现队列（先入先出队列）
// 三个原则
// 一次性导完
// 只有pop stack空了才能导数据
// pop stack不为空不用导数据
// ["MyQueue","push","push","peek","pop","empty"]
// [[],[1],[2],[],[],[]]
// https://leetcode.cn/problems/implement-queue-using-stacks/
// 笔记：https://www.cnblogs.com/greyzeng/p/16631644.html
public class LeetCode_0232_ImplementQueueUsingStacks {
    class MyQueue {
        private final Stack<Integer> push;
        private final Stack<Integer> pop;

        public MyQueue() {
            push = new Stack<>();
            pop = new Stack<>();
        }

        public void push(int x) {
            push.push(x);
        }

        public int pop() {
            while (!push.isEmpty()) {
                pop.push(push.pop());
            }
            int result = pop.pop();
            while (!pop.isEmpty()) {
                push.push(pop.pop());
            }
            return result;
        }

        public int peek() {
            while (!push.isEmpty()) {
                pop.push(push.pop());
            }
            int result = pop.peek();
            while (!pop.isEmpty()) {
                push.push(pop.pop());
            }
            return result;
        }

        public boolean empty() {
            return push.isEmpty();
        }
    }

}
