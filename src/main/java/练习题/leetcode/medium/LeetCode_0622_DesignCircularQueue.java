package 练习题.leetcode.medium;

// 数组实现不超过固定大小的队列
// 设计循环队列
// https://leetcode.cn/problems/design-circular-queue/
// 笔记：https://www.cnblogs.com/greyzeng/p/16631644.html
public class LeetCode_0622_DesignCircularQueue {
    class MyCircularQueue {
        private final int[] arr;
        private int popIndex;
        private int pushIndex;
        private int rear;
        private int size;
        private final int limit;

        public MyCircularQueue(int k) {
            limit = k;
            arr = new int[limit];
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
            return size == limit;
        }

        private int next(int pre) {
            return pre < limit - 1 ? pre + 1 : 0;
        }
    }
}
