package git.snippet.queueandstack;

// 用双向链表实现双端队列
// notes: https://www.cnblogs.com/greyzeng/p/16631644.html
// https://leetcode.com/problems/design-circular-deque
public class LeetCode_0641_DesignCircularDeque {
	// TODO
	// 使用固定数组来实现环状队列
	// 这比使用双链表来实现更好
	class MyCircularDeque {
		private int[] data;
		private int size, head, tail;

		public MyCircularDeque(int k) {
			head = 0;
			tail = 0;
			size = 0;
			data = new int[k];
		}

		public boolean insertFront(int value) {
			if (!isFull()) {

			}
			return false;
		}

		private int pre(int cur) {
			return cur == 0 ? data.length - 1 : cur - 1;
		}

		private int next(int cur) {
			return cur == data.length - 1 ? 0 : cur + 1;
		}

		public boolean insertLast(int value) {
			if (!isFull()) {

			}
			return false;
		}

		public boolean deleteFront() {
			if (!isEmpty()) {

			}
			return false;
		}

		public boolean deleteLast() {
			if (!isEmpty()) {

			}
			return false;
		}

		public int getFront() {
			if (!isEmpty()) {

			}
			return -1;
		}

		public int getRear() {
			if (!isEmpty()) {

			}
			return -1;
		}

		public boolean isEmpty() {
			return size == 0;
		}

		public boolean isFull() {
			return size == data.length;
		}
	}

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
		private int cap; // 容量
		private int size; // 当前大小

		public MyCircularDeque1(int k) {
			cap = k;
		}

		public boolean insertFront(int value) {
			if (isFull()) {
				return false;
			}
			size++;
			if (head == null) {
				head = new Node(value);
				tail = head;
			} else {
				Node newHead = new Node(value);
				newHead.next = head;
				head.last = newHead;
				head = newHead;
			}
			return true;
		}

		public boolean insertLast(int value) {
			if (isFull()) {
				return false;
			}
			size++;
			if (head == null) {
				head = new Node(value);
				tail = head;
			} else {
				Node newTail = new Node(value);
				tail.next = newTail;
				newTail.last = tail;
				tail = newTail;
			}
			return true;
		}

		public boolean deleteFront() {
			if (size == 0 || cap == 0) {
				return false;
			}
			size--;
			if (size == 0) {
				head = null;
				tail = null;
			} else {
				head = head.next;
				head.last = null;
			}
			return true;
		}

		public boolean deleteLast() {
			if (size == 0 || cap == 0) {
				return false;
			}
			size--;
			if (size == 0) {
				head = null;
				tail = null;
			} else {
				tail = tail.last;
				tail.next = null;
			}
			return true;
		}

		public int getFront() {
			if (size == 0) {
				return -1;
			}
			return head.v;
		}

		public int getRear() {
			if (size == 0) {
				return -1;
			}
			return tail.v;
		}

		public boolean isEmpty() {
			return size == 0;
		}

		public boolean isFull() {
			return size == cap;
		}
	}

}
