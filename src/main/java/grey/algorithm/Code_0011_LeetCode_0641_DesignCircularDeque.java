package grey.algorithm;

// 用双向链表实现双端队列
// notes: https://www.cnblogs.com/greyzeng/p/16631644.html
// https://leetcode.com/problems/design-circular-deque
public class Code_0011_LeetCode_0641_DesignCircularDeque {

	// 使用固定数组来实现环状队列
	class MyCircularDeque {

		private final int[] arr;
		private int head;
		private int tail;
		private int size;

		public MyCircularDeque(int k) {
			arr = new int[k];
		}

		public boolean insertFront(int value) {
			if (isFull()) {
				return false;
			}
			if (isEmpty()) {
				arr[head] = value;
				tail = next(tail);
			} else {
				head = pre(head);
				arr[head] = value;
			}
			size++;
			return true;
		}

		public boolean insertLast(int value) {
			if (isFull()) {
				return false;
			}
			if (isEmpty()) {
				arr[head] = value;
				tail = next(tail);
			} else {
				arr[tail] = value;
				tail = next(tail);
			}
			size++;
			return true;
		}

		public boolean deleteFront() {
			if (isEmpty()) {
				return false;
			}
			size--;
			head = next(head);
			return true;
		}

		public boolean deleteLast() {
			if (isEmpty()) {
				return false;
			}
			size--;
			tail = pre(tail);
			return true;
		}

		public int getFront() {
			if (isEmpty()) {
				return -1;
			}

			return arr[head];
		}

		public int getRear() {
			if (isEmpty()) {
				return -1;
			}
			return arr[pre(tail)];
		}

		public boolean isEmpty() {
			return size == 0;
		}

		public boolean isFull() {
			return size == arr.length;
		}

		private int pre(int i) {
			return i == 0 ? arr.length - 1 : i - 1;
		}

		private int next(int i) {
			return i == arr.length - 1 ? 0 : i + 1;
		}
	}

	// 用链表实现
	class MyCircularDeque1 {

		private class Node {

			int v;
			Node last;
			Node next;

			public Node(int val) {
				v = val;
			}
		}

		private Node head; // 头节点
		private Node tail; // 尾部节点
		private final int limit; // 容量
		private int size; // 当前大小

		public MyCircularDeque1(int k) {
			limit = k;
		}

		public boolean insertFront(int value) {
			if (isFull()) {
				return false;
			}
			if (isEmpty()) {
				head = new Node(value);
				tail = head;
			} else {
				Node newNode = new Node(value);
				newNode.next = head;
				head.last = newNode;
				head = newNode;
			}
			size++;
			return true;
		}

		public boolean insertLast(int value) {
			if (isFull()) {
				return false;
			}
			if (isEmpty()) {
				head = new Node(value);
				tail = head;
			} else {
				Node newNode = new Node(value);
				newNode.last = tail;
				tail.next = newNode;
				tail = newNode;
			}
			size++;
			return true;
		}

		public boolean deleteFront() {
			if (isEmpty()) {
				return false;
			}
			head = head.next;
			size--;
			return true;
		}

		public boolean deleteLast() {
			if (isEmpty()) {
				return false;
			}
			tail = tail.last;
			size--;
			return true;
		}

		public int getFront() {
			if (isEmpty()) {
				return -1;
			}
			return head.v;
		}

		public int getRear() {
			if (isEmpty()) {
				return -1;
			}
			return tail.v;
		}

		public boolean isEmpty() {
			return size == 0;
		}

		public boolean isFull() {
			return size == limit;
		}
	}

}
