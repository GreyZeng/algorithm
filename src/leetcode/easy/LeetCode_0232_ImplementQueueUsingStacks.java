package leetcode.easy;

import java.util.ArrayDeque;
import java.util.Deque;

// 三个原则
// 一次性导完
// 只有pop stack空了才能导数据
// pop stack不为空不用导数据
// ["MyQueue","push","push","peek","pop","empty"]
// [[],[1],[2],[],[],[]]
public class LeetCode_0232_ImplementQueueUsingStacks {
    static class MyQueue {
        private final Deque<Integer> push;
        private final Deque<Integer> pop;

        public MyQueue() {
            push = new ArrayDeque<>();
            pop = new ArrayDeque<>();
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
