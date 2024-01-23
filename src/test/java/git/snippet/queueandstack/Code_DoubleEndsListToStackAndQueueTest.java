package git.snippet.queueandstack;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import static git.snippet.common.Generator.isEqual;

@DisplayName("用双链表实现栈和队列")
public class Code_DoubleEndsListToStackAndQueueTest {
    @Test
    @DisplayName("用双链表实现栈和队列")
    public void testInAndOut() {
        int oneTestDataNum = 100;
        int value = 10000;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            Code_DoubleEndsListToStackAndQueue.MyStack<Integer> myStack = new Code_DoubleEndsListToStackAndQueue.MyStack<>();
            Code_DoubleEndsListToStackAndQueue.MyQueue<Integer> myQueue = new Code_DoubleEndsListToStackAndQueue.MyQueue<>();
            Stack<Integer> stack = new Stack<>();
            Queue<Integer> queue = new LinkedList<>();
            for (int j = 0; j < oneTestDataNum; j++) {
                int nums = (int) (Math.random() * value);
                if (stack.isEmpty()) {
                    myStack.push(nums);
                    stack.push(nums);
                } else {
                    if (Math.random() < 0.5) {
                        myStack.push(nums);
                        stack.push(nums);
                    } else {
                        if (!isEqual(myStack.pop(), stack.pop())) {
                            Assertions.fail();
                        }
                    }
                }
                int numq = (int) (Math.random() * value);
                if (queue.isEmpty()) {
                    myQueue.push(numq);
                    queue.offer(numq);
                } else {
                    if (Math.random() < 0.5) {
                        myQueue.push(numq);
                        queue.offer(numq);
                    } else {
                        if (!isEqual(myQueue.poll(), queue.poll())) {
                            Assertions.fail();
                        }
                    }
                }
            }
        }
    }
}