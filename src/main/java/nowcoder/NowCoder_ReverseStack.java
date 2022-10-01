//链接：https://www.nowcoder.com/questionTerminal/ba7d7f5d1edf4d1690d66e12e951f6ea
//来源：牛客网
//一个栈依次压入1,2,3,4,5那么从栈顶到栈底分别为5,4,3,2,1。
// 将这个栈转置后，从栈顶到栈底为1,2,3,4,5，也就是实现了栈中元素的逆序，
// 请设计一个算法实现逆序栈的操作，但是只能用递归函数来实现，
// 而不能用另外的数据结构。
// 给定一个栈Stack以及栈的大小top，请返回逆序后的栈。
// 测试样例：
// [1,2,3,4,5],5
// 返回：[5,4,3,2,1]
package nowcoder;

import java.util.Stack;

// 笔记：
// 用递归函数和栈操作逆序栈
public class NowCoder_ReverseStack {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4};
        int[] result = reverseStackRecursively(arr, arr.length);
        for (int m : result) {
            System.out.println(m);
        }
    }

    public static int[] reverseStackRecursively(int[] stack, int top) {
        if (top >= 1) {
            // 获取栈底元素
            int bottom = getBottom(stack, top);
            // 逆序
            stack = reverseStackRecursively(stack, --top);
            // 栈底放栈顶
            stack[top] = bottom;
        }
        return stack;
    }

    public static int getBottom(int[] stack, int top) {
        if (top == 1) {
            return stack[0];
        } else {
            int tmp = stack[--top];
            int bottom = getBottom(stack, top);
            stack[--top] = tmp;
            return bottom;
        }
    }

    public static void reverseStackRecursively3(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        int last = f(stack);
        reverseStackRecursively3(stack);
        stack.push(last);
    }

    // 返回栈底元素
    public static int f(Stack<Integer> stack) {
        int result = stack.pop();
        if (stack.isEmpty()) {
            return result;
        } else {
            int last = f(stack);
            stack.push(result);
            return last;
        }
    }
}
