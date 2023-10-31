package git.snippet.queueandstack;

import java.util.Stack;

// 最小栈
// 笔记：https://www.cnblogs.com/greyzeng/p/16631644.html
// https://leetcode.com/problems/min-stack
public class LeetCode_0155_MinStack {
  static class MinStack {
    Stack<Integer> minStack;
    Stack<Integer> valStack;

    public MinStack() {
      minStack = new Stack<>();
      valStack = new Stack<>();
    }

    public void push(int val) {
      valStack.push(val);
      if (!minStack.isEmpty()) {
        if (minStack.peek() < val) {
          minStack.push(minStack.peek());
        } else {
          minStack.push(val);
        }
      } else {
        minStack.push(val);
      }
    }

    public void pop() {
      if (!valStack.isEmpty()) {
        valStack.pop();
        minStack.pop();
      }
    }

    public int top() {
      return valStack.peek();
    }

    public int getMin() {
      return minStack.peek();
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
