package note;

import java.util.Stack;

//实现一个特殊的栈，在基本功能的基础上，再实现返回栈中最小元素的功能，值范围是[-100000,100000]
//
//1. pop、push、getMin操作的时间复杂度都是 O(1)。
//2. 设计的栈类型可以使用现成的栈结构。
//3. 空间复杂度O(1)
public class Code_0013_MinStackO1 {
    private class MinStack {
        int m;
        Stack<Integer> n;

        public MinStack() {
            n = new Stack<>();
        }

        public void push(int x) {
            if (n.isEmpty()) {
                m = x;
                n.push(0);
            } else {
                n.push(x - m);
                m = x - m < 0 ? x : m;
            }
        }

        public int pop() {
            // TODO
            return -1;
        }


        public int getMin() {
            return m;
        }
    }
}
