package code;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class Code_0029_CustomHeap {

	// 自己手写堆
	public static class MyHeap<T> {
		private ArrayList<T> heap;
		private HashMap<T, Integer> indexMap;
		private int heapSize;
		private Comparator<? super T> comparator;

		public MyHeap(Comparator<? super T> com) {
			heap = new ArrayList<>();
			indexMap = new HashMap<>();
			heapSize = 0;
			comparator = com;
		}

		public boolean isEmpty() {
			return size() == 0;
		}

		public int size() {
			return heapSize;
		}

		public boolean contains(T key) {
			return indexMap.containsKey(key);
		}

		public void push(T value) {
			heap.add(value);
			indexMap.put(value, heapSize);
			heapInsert(heapSize++);
		}

		public T pop() {
			T ans = heap.get(0);
			swap(0, heapSize - 1);
			heap.remove(--heapSize);
			heapify(0, heapSize);
			return ans;
		}

		public void resign(T value) {
			int valueIndex = indexMap.get(value);
			heapInsert(valueIndex);
			heapify(valueIndex, heapSize);
		}

		private void heapInsert(int index) {
			while (comparator.compare(heap.get(index), heap.get((index - 1) / 2)) < 0) {
				swap(index, (index - 1) / 2);
				index = (index - 1) / 2;
			}
		}

		private void heapify(int index, int heapSize) {
			int left = 2 * index + 1;
			while (left < heapSize) {
				T largest = (left + 1 < heapSize)
						? (comparator.compare(heap.get(left), heap.get(left + 1)) > 0) ? heap.get(left)
								: heap.get(left + 1)
						: heap.get(left);
				if (comparator.compare(heap.get(index), largest) > 0) {
					break;
				}
				int largestIndex = largest == heap.get(left) ? left : left + 1;
				swap(index, largestIndex);
				index = largestIndex;
				left = 2 * index + 1;
			}
		}

		private void swap(int i, int j) {
			T a = heap.get(i);
			int indexA = indexMap.get(a);
			T b = heap.get(j);
			int indexB = indexMap.get(b);
			T t = b;
			b = a;
			a = t;
			indexMap.put(a, indexB);
			indexMap.put(b, indexA);
		}

	}

}
