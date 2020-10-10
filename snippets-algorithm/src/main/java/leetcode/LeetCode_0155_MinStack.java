package leetcode;

import java.util.Stack;

/*
 * 在leetcode上提交时，把文字替换成下面的代码
 * 然后把类名、构造方法名从Problem_0155_MinStack改为MinStack即可
 */
public class LeetCode_0155_MinStack {
    // 准备两个栈
    // 一个是最小栈，同步增长


    class MinStack {
        Stack<Integer> n;
        Stack<Integer> m;
 
        public MinStack() {
            n = new Stack<>();
            m = new Stack<>();
        }

        public void push(int x) {
            if (m.isEmpty()) {
                m.add(x);
            } else {
                m.add(m.peek() > x ? x : m.peek());
            }
            n.add(x);
        }

        public void pop() {
            n.pop();
            m.pop();
        }

        public int top() {
            return n.peek();
        }

        public int getMin() {
            return m.peek();
        }
    }

}
