package heap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

// 笔记：https://www.cnblogs.com/greyzeng/p/16933830.html
// 什么是完全二叉树 如果一个树是满的，它是完全二叉树，即便不是满的，也是从左到右依次变满的
// 堆结构
//1）堆结构就是用数组实现的完全二叉树结构
//2）完全二叉树中如果每棵子树的最大值都在顶部就是大根堆
//3）完全二叉树中如果每棵子树的最小值都在顶部就是小根堆
//4）堆结构的heapInsert与heapify操作
//5）堆结构的增大和减少
//6）Java中的PriorityQueue，就是堆结构
// 用数组表示堆的两种情况
// 第一种情况：如果使用数组0位置，对于i位置来说，它的：
// 左孩子 2 * i + 1
//右孩子 2 * i + 2
//父节点 （i - 1）/ 2
// 第二种情况：如果不用0位置，对于i位置来说，它的：
//左孩子 2 * i 即：i << 1
//右孩子 2 * i + 1 即：i << 1 | 1
//父节点 i / 2 即：i >> 1
// 大根堆：完全二叉树中，每棵树的最大值都是头节点的值
// heapify和heapInsert都是logN级别的复杂度，因为N个节点的二叉树高度是logN
public class Code_Heap {

	public static class MaxHeap {
		private final int[] heap;
		// private final int limit; limit == heap.length
		private int heapSize;

		public MaxHeap(int limit) {
			heap = new int[limit];
			// this.limit = limit;
			heapSize = 0;
		}

		public void push(int value) {
			if (!isFull()) {
				heap[heapSize] = value;
				// value heapSize
				heapInsert();
				heapSize++;
			}
		}

		public int pop() {
			int ans = heap[0];
			swap(heap, 0, --heapSize);
			heapify();
			return ans;

		}

		private void heapInsert() {
			int i = heapSize;
			while (heap[i] > heap[(i - 1) / 2]) {
				swap(heap, i, (i - 1) / 2);
				i = (i - 1) / 2;
			}
		}

		private void heapify() {
			int index = 0;
			int left = index * 2 + 1;
			while (left < heapSize) {
				int largest // bigger index
						= left + 1 < heapSize // right child exist
								&& heap[left + 1] > heap[left] // compare left child and right child
										? left + 1
										: left;
				largest = heap[largest] > heap[index] ? largest : index;
				if (largest == index) {
					break;
				}
				swap(heap, largest, index);
				index = largest;
				left = index * 2 + 1;
			}
		}

		public boolean isEmpty() {
			return heapSize == 0;
		}

		public boolean isFull() {
			return heapSize == heap.length;
		}

		private void swap(int[] arr, int i, int j) {
			int tmp = arr[i];
			arr[i] = arr[j];
			arr[j] = tmp;
		}
	}

	public static class RightMaxHeap {
		private int[] arr;
		private final int limit;
		private int size;

		public RightMaxHeap(int limit) {
			arr = new int[limit];
			this.limit = limit;
			size = 0;
		}

		public boolean isEmpty() {
			return size == 0;
		}

		public boolean isFull() {
			return size == limit;
		}

		public void push(int value) {
			if (size == limit) {
				throw new RuntimeException("heap is full");
			}
			arr[size++] = value;
		}

		public int pop() {
			int maxIndex = 0;
			for (int i = 1; i < size; i++) {
				if (arr[i] > arr[maxIndex]) {
					maxIndex = i;
				}
			}
			int ans = arr[maxIndex];
			arr[maxIndex] = arr[--size];
			return ans;
		}
	}

	// 加强堆
	// 笔记：https://www.cnblogs.com/greyzeng/p/16936506.html
	public static class HeapGreater<T> {

		private ArrayList<T> heap;
		private HashMap<T, Integer> indexMap; // 元素在堆中的位置
		private int heapSize; // 和heap配合使用
		private Comparator<? super T> comp;

		public HeapGreater(Comparator<T> c) {
			heap = new ArrayList<>();
			indexMap = new HashMap<>();
			comp = c;
		}

		public boolean isEmpty() {
			return heapSize == 0;
		}

		public int size() {
			return heapSize;
		}

		public boolean contains(T obj) {
			return indexMap.containsKey(obj);
		}

		public T peek() {
			return heap.get(0);
		}

		public void push(T obj) {
			heap.add(obj);
			indexMap.put(obj, heapSize);
			heapInsert(heapSize++);
		}

		public T pop() {
			T ans = heap.get(0);
			swap(0, heapSize - 1);
			indexMap.remove(ans);
			heap.remove(--heapSize);
			heapify(0);
			return ans;
		}

		public void remove(T obj) {
			T replace = heap.get(heapSize - 1);
			int index = indexMap.get(obj);
			indexMap.remove(obj);
			heap.remove(--heapSize);
			if (obj != replace) { // obj == replace表示删掉的是最后一个位置的数据，此时不需要进行resign操作
				heap.set(index, replace);
				indexMap.put(replace, index);
				resign(replace);
			}
		}

		public void resign(T obj) {
			heapInsert(indexMap.get(obj));
			heapify(indexMap.get(obj));
		}

		// 请返回堆上的所有元素
		public List<T> getAllElements() {
			List<T> ans = new ArrayList<>();
			for (T c : heap) {
				ans.add(c);
			}
			return ans;
		}

		private void heapInsert(int index) {
			while (comp.compare(heap.get(index), heap.get((index - 1) / 2)) < 0) {
				swap(index, (index - 1) / 2);
				index = (index - 1) / 2;
			}
		}

		private void heapify(int index) {
			int left = index * 2 + 1;
			while (left < heapSize) {
				int best = left + 1 < heapSize && comp.compare(heap.get(left + 1), heap.get(left)) < 0 ? (left + 1)
						: left;
				best = comp.compare(heap.get(best), heap.get(index)) < 0 ? best : index;
				if (best == index) {
					break;
				}
				swap(best, index);
				index = best;
				left = index * 2 + 1;
			}
		}

		private void swap(int i, int j) {
			T o1 = heap.get(i);
			T o2 = heap.get(j);
			heap.set(i, o2);
			heap.set(j, o1);
			indexMap.put(o2, i);
			indexMap.put(o1, j);
		}
	}

	public static void testHeap() {
		int value = 1000;
		int limit = 100;
		int testTimes = 1000000;
		for (int i = 0; i < testTimes; i++) {
			int curLimit = (int) (Math.random() * limit) + 1;
			MaxHeap my = new MaxHeap(curLimit);
			RightMaxHeap test = new RightMaxHeap(curLimit);
			int curOpTimes = (int) (Math.random() * limit);
			for (int j = 0; j < curOpTimes; j++) {
				if (my.isEmpty() != test.isEmpty()) {
					System.out.println("Oops!");
				}
				if (my.isFull() != test.isFull()) {
					System.out.println("Oops!");
				}
				if (my.isEmpty()) {
					int curValue = (int) (Math.random() * value);
					my.push(curValue);
					test.push(curValue);
				} else if (my.isFull()) {
					if (my.pop() != test.pop()) {
						System.out.println("Oops!");
					}
				} else {
					if (Math.random() < 0.5) {
						int curValue = (int) (Math.random() * value);
						my.push(curValue);
						test.push(curValue);
					} else {
						if (my.pop() != test.pop()) {
							System.out.println("Oops!");
						}
					}
				}
			}
		}
		System.out.println("finish!");
	}

	// 加强堆测试
	public static void testGreaterHeap() {
		int value = 1000;
		int limit = 100;
		int testTimes = 1000000;
		for (int i = 0; i < testTimes; i++) {
			int curLimit = (int) (Math.random() * limit) + 1;
			HeapGreater<Integer> my = new HeapGreater<>(new Comparator<Integer>() {
				@Override
				public int compare(Integer o1, Integer o2) {
					return o2 - o1;
				}
			});
			RightMaxHeap test = new RightMaxHeap(curLimit);
			int curOpTimes = (int) (Math.random() * limit);
			for (int j = 0; j < curOpTimes; j++) {
				if (test.isEmpty()) {
					int curValue = (int) (Math.random() * value);
					my.push(curValue);
					test.push(curValue);
				} else if (test.isFull()) {
					if (my.pop() != test.pop()) {
						System.out.println("Oops!");
					}
				} else {
					if (Math.random() < 0.5) {
						int curValue = (int) (Math.random() * value);
						my.push(curValue);
						test.push(curValue);
					} else {
						if (my.pop() != test.pop()) {
							System.out.println("Oops!");
						}
					}
				}
			}
		}
		System.out.println("finish!");
	}

	public static void main(String[] args) {
		testHeap();
		testGreaterHeap();
	}
}
