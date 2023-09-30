package queueandstack;

import java.util.Queue;
import java.util.LinkedList;

// 使用队列实现栈
// 笔记：https://www.cnblogs.com/greyzeng/p/16631644.html
public class LeetCode_0225_ImplementStackUsingQueues {
  class MyStack {
    // only use poll() and offer()
    private Queue<Integer> data;
    private Queue<Integer> helper;

    public MyStack() {
      data = new LinkedList<>();
      helper = new LinkedList<>();
    }

    public void push(int x) {
      data.offer(x);
    }

    public int pop() {
      int rv = -1;
      while (!data.isEmpty()) {
        rv = data.poll();
        if (!data.isEmpty()) {
          helper.offer(rv);
        }
      }
      Queue<Integer> tmp = data;
      data = helper;
      helper = tmp;
      return rv;
    }

    public int top() {
      int rv = -1;
      while (!data.isEmpty()) {
        rv = data.poll();
        helper.offer(rv);
      }
      Queue<Integer> tmp = data;
      data = helper;
      helper = tmp;
      return rv;
    }

    public boolean empty() {
      return data.isEmpty();
    }
  }
}
