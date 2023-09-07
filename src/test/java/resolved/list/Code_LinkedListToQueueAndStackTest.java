package resolved.list;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

@DisplayName("单链表实现栈和队列")
public class Code_LinkedListToQueueAndStackTest {
    @Test
    void testQueue() {
        Code_LinkedListToQueueAndStack.MyQueue<Integer> myQueue = new Code_LinkedListToQueueAndStack.MyQueue<>();
        Queue<Integer> test = new LinkedList<>();
        int testTime = 5000000;
        int maxValue = 200000000;
        System.out.println("测试开始！");
        for (int i = 0; i < testTime; i++) {
            if (myQueue.isEmpty() != test.isEmpty()) {
                System.out.println("Oops!");
            }
            if (myQueue.size() != test.size()) {
                System.out.println("Oops!");
            }
            double decide = Math.random();
            if (decide < 0.33) {
                int num = (int) (Math.random() * maxValue);
                myQueue.offer(num);
                test.offer(num);
            } else if (decide < 0.66) {
                if (!myQueue.isEmpty()) {
                    int num1 = myQueue.poll();
                    int num2 = test.poll();
                    if (num1 != num2) {
                        System.out.println("Oops!");
                    }
                }
            } else {
                if (!myQueue.isEmpty()) {
                    int num1 = myQueue.peek();
                    int num2 = test.peek();
                    if (num1 != num2) {
                        System.out.println("Oops!");
                    }
                }
            }
        }
        if (myQueue.size() != test.size()) {
            System.out.println("Oops!");
        }
        while (!myQueue.isEmpty()) {
            int num1 = myQueue.poll();
            int num2 = test.poll();
            if (num1 != num2) {
                System.out.println("Oops!");
            }
        }
        System.out.println("测试结束！");
    }

    @Test
    void testStack() {
        Code_LinkedListToQueueAndStack.MyStack<Integer> myStack = new Code_LinkedListToQueueAndStack.MyStack<>();
        Stack<Integer> test = new Stack<>();
        int testTime = 5000000;
        int maxValue = 200000000;
        System.out.println("测试开始！");
        for (int i = 0; i < testTime; i++) {
            if (myStack.isEmpty() != test.isEmpty()) {
                System.out.println("Oops!");
            }
            if (myStack.size() != test.size()) {
                System.out.println("Oops!");
            }
            double decide = Math.random();
            if (decide < 0.33) {
                int num = (int) (Math.random() * maxValue);
                myStack.push(num);
                test.push(num);
            } else if (decide < 0.66) {
                if (!myStack.isEmpty()) {
                    int num1 = myStack.pop();
                    int num2 = test.pop();
                    if (num1 != num2) {
                        System.out.println("Oops!");
                    }
                }
            } else {
                if (!myStack.isEmpty()) {
                    int num1 = myStack.peek();
                    int num2 = test.peek();
                    if (num1 != num2) {
                        System.out.println("Oops!");
                    }
                }
            }
        }
        if (myStack.size() != test.size()) {
            System.out.println("Oops!");
        }
        while (!myStack.isEmpty()) {
            int num1 = myStack.pop();
            int num2 = test.pop();
            if (num1 != num2) {
                System.out.println("Oops!");
            }
        }
        System.out.println("测试结束！");
    }


}