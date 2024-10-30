package grey.algorithm;

// 数组实现不超过固定大小的队列
// 设计循环队列
// https://leetcode.com/problems/design-circular-queue/
// 笔记：https://www.cnblogs.com/greyzeng/p/16631644.html
public class Code_0012_LeetCode_0622_DesignCircularQueue {
	class MyCircularQueue {
		private final int[] arr;
		private int head; // 队列头部指针
		private int tail; // 队列尾部元素的下一个位置指针
		private int size; // 非常重要，标识现在的有效元素有几个，用于判断队列是否满/空

		public MyCircularQueue(int k) {
			arr = new int[k];
		}

		public boolean enQueue(int value) {
			if (isFull()) {
				return false;
			}
			size++;
			arr[tail] = value;
			tail = next(tail);
			return true;
		}

		public boolean deQueue() {
			if (isEmpty()) {
				return false;
			}
			size--;
			head = next(head);
			return true;
		}

		public int Front() {
			return isEmpty() ? -1 : arr[head];
		}

		public int Rear() {
			return isEmpty() ? -1 : arr[pre(tail)];
		}

		public boolean isEmpty() {
			return size == 0;
		}

		public boolean isFull() {
			return size == arr.length;
		}

		// 判断下一个位置，模拟环行为
		private int next(int cur) {
			return cur == arr.length - 1 ? 0 : (cur + 1);
		}

		private int pre(int cur) {
			return cur == 0 ? arr.length - 1 : cur - 1;
		}

	}
}
