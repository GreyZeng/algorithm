package grey.algorithm;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

// 用单链表实现栈和队列
public class Code_0011_LinkedListToQueueAndStack {

	// 单链表的定义
	public static class Node<V> {
		public V val;
		public Node<V> next;

		public Node(V v) {
			val = v;
		}
	}

	// eg
	// a,b,c,d
	// a <- b <- c <- d
	// 其中
	// tail 指针指向 d
	// head 指针指向 a
	// 用单链表实现自定义队列
	// 头部进，尾部出
	public static class MyQueue<V> {
		private Node<V> h;
		private Node<V> t;
		private int size;

		public boolean isEmpty() {
			return size() == 0;
		}

		public int size() {
			return size;
		}

		// 头部进!!!
		public void offer(V value) {
			if (isEmpty()) {
				t = new Node<>(value);
				h = t;
			} else {
				Node<V> node = new Node<>(value);
				h.next = node;
				h = node;
			}
			size++;
		}

		// 尾部出
		public V poll() {
			if (!isEmpty()) {
				V r = t.val;
				t = t.next;
				size--;
				return r;
			}
			return null;
		}

		// 查看尾部数据
		public V peek() {
			return isEmpty() ? null : t.val;
		}
	}

	// 用单链表实现自定义栈
	// 头部进，头部出
	public static class MyStack<V> {
		private Node<V> h;
		private int size;

		public boolean isEmpty() {
			return size() == 0;
		}

		public int size() {
			return size;
		}

		public void push(V v) {

			if (isEmpty()) {
				h = new Node<>(v);
			} else {
				Node<V> newHead = new Node<>(v);
				newHead.next = h;
				h = newHead;

			}
			size++;
		}

		public V pop() {
			if (!isEmpty()) {
				size--;
				V r = h.val;
				h = h.next;
				return r;
			}
			return null;
		}

		public V peek() {
			if (!isEmpty()) {
				return h.val;
			}
			return null;
		}
	}

	// for test
	public static void main(String[] args) {
		testQueue();
		testStack();
	}

	static void testQueue() {
		Code_0011_LinkedListToQueueAndStack.MyQueue<Integer> myQueue = new Code_0011_LinkedListToQueueAndStack.MyQueue<>();
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

	static void testStack() {
		Code_0011_LinkedListToQueueAndStack.MyStack<Integer> myStack = new Code_0011_LinkedListToQueueAndStack.MyStack<>();
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
