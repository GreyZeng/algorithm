package grey.algorithm;

// 数组实现不超过固定大小的队列
// 设计循环队列
// https://leetcode.com/problems/design-circular-queue/
// 笔记：https://www.cnblogs.com/greyzeng/p/16631644.html
public class Code_0011_LeetCode_0622_DesignCircularQueue {

    class MyCircularQueue {

        private int[] arr;
        private int h; // 头部
        private int t; // 尾部元素所在位置的下一个位置
        private int size;

        public MyCircularQueue(int k) {
            arr = new int[k];
        }

        public boolean enQueue(int value) {
            if (isFull()) {
                return false;
            }
            arr[t] = value;
            t = next(t);
            size++;
            return true;
        }

        public boolean deQueue() {
            if (isEmpty()) {
                return false;
            }
            h = next(h);
            size--;
            return true;
        }

        public int Front() {
            return isEmpty() ? -1 : arr[h];
        }

        public int Rear() {
            return isEmpty() ? -1 : arr[pre(t)];
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == arr.length;
        }

        private int next(int t) {
            return t == arr.length - 1 ? 0 : t + 1;
        }

        private int pre(int t) {
            return t == 0 ? arr.length - 1 : t - 1;
        }

    }
}
