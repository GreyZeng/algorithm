package git.snippet.queueandstack;

// 数组实现不超过固定大小的队列
// 设计循环队列
// https://leetcode.com/problems/design-circular-queue/
// 笔记：https://www.cnblogs.com/greyzeng/p/16631644.html
public class LeetCode_0622_DesignCircularQueue {
    class MyCircularQueue {
        private final int[] arr;
        // private final int limit; // 容量，和arr大小保持一致，可以省略
        private int popIndex; // 队列头部指针（指向队列第一个有效元素)
        private int pushIndex; // 队列尾部的下一个位置（就是待插入元素的位置）
        private int rear; // 队列尾部指针
        private int size; // 非常重要，标识现在的有效元素有几个，用于判断队列是否满/空

        public MyCircularQueue(int k) {
            arr = new int[k];
        }

        public boolean enQueue(int value) {
            if (isFull()) {
                return false;
            }
            size++;
            arr[pushIndex] = value;
            rear = pushIndex;
            pushIndex = next(pushIndex);
            return true;
        }

        public boolean deQueue() {
            if (isEmpty()) {
                return false;
            }
            size--;
            popIndex = next(popIndex);
            return true;
        }

        public int Front() {
            return isEmpty() ? -1 : arr[popIndex];
        }

        public int Rear() {
            return isEmpty() ? -1 : arr[rear];
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == arr.length;
        }

        // 最重要的方法：判断下一个位置，模拟环行为
        private int next(int pre) {
            return pre + 1 < arr.length ? (pre + 1) : 0;
        }
    }
}
