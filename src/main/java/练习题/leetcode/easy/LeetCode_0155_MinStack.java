package 练习题.leetcode.easy;

import java.util.Stack;

// 最小栈
// 笔记：https://www.cnblogs.com/greyzeng/p/16631644.html
// https://leetcode-cn.com/problems/min-stack
public class LeetCode_0155_MinStack {
  static class MinStack {
    Stack<Integer> stack;
    Stack<Integer> min;

    public MinStack() {
      stack = new Stack<>();
      min = new Stack<>();
    }

    public void push(int val) {
      stack.push(val);
      if (min.isEmpty()) {
        min.push(val);
      } else {
        min.push(Math.min(min.peek(), val));
      }
    }

    public void pop() {
      stack.pop();
      min.pop();
    }

    public int top() {
      return stack.peek();
    }

    public int getMin() {
      return min.peek();
    }
  }

  // TODO
  static class MinStackO1 {
    private final long offset;
    private final Stack<Long> stack;

    public MinStackO1() {
      // 二进制：11111111111111111111111111111111
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
      // left 存 stack1中的值
      // right为对应的stack2中的值，即min值
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

  public static void main(String[] args) {
    long offset = 4294967295L;
    System.out.println(Long.toBinaryString(offset));
  }

}
