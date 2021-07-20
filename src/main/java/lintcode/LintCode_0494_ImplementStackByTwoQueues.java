package lintcode;

import java.util.LinkedList;
import java.util.Queue;

public class LintCode_0494_ImplementStackByTwoQueues{
    class Stack {
        private Queue<Integer> queue = new LinkedList<>();
        private Queue<Integer> helper = new LinkedList<>();
        public void push(int x) {
            queue.offer(x);
        }
    
        public void pop() {
            if (isEmpty()) {
                return;
            }
            int result = 0;
            while(!isEmpty()) {
                result = queue.poll();
                if (!isEmpty()) {
                    helper.offer(result);
                }
            }
            Queue<Integer> t = queue;
            queue = helper;
            helper = t;
        }
    
        public int top() {
            int result = 0;
            while(!isEmpty()) {
                result = queue.poll();
                helper.offer(result);
            }
            Queue<Integer> t = queue;
            queue = helper;
            helper = t;
            return result;
        }
    
        public boolean isEmpty() {
            return queue.isEmpty();
        }
    }
}