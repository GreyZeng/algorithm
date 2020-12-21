package code;

/**
 * 堆结构 1）堆结构就是用数组实现的完全二叉树结构 2）完全二叉树中如果每棵子树的最大值都在顶部就是大根堆
 * 3）完全二叉树中如果每棵子树的最小值都在顶部就是小根堆 4）堆结构的heapInsert与heapify操作 5）堆结构的增大和减少
 * 6）优先级队列结构，就是堆结构
 */
public class Code_0026_MaxHeap {
	public static class MyMaxHeap {
		private int[] heap;
		private final int limit;
		private int heapSize;

		public MyMaxHeap(int limit) {
			heap = new int[limit];
			this.limit = limit;
			heapSize = 0;
		}

		public boolean isEmpty() {
			return heapSize == 0;
		}

		public boolean isFull() {
			return heapSize == limit;
		}

		public void push(int value) {
			if (isFull()) {
				throw new RuntimeException("heap is full");
			}
			heap[heapSize] = value;
			heapInsert(heap, heapSize++);
		}

		public int pop() {
			if (isEmpty()) {
				throw new RuntimeException("heap is empty");
			}
			int max = heap[0];
			swap(heap, 0, --heapSize);
			heapify(heap, 0, heapSize);
			return max;
		}

		private void heapInsert(int[] arr, int index) {
			while (arr[(index - 1) / 2] < arr[index]) {
				swap(arr, (index - 1) / 2, index);
				index = (index - 1) / 2;
			}
		}

		private void heapify(int[] arr, int index, int heapSize) {
			int left = 2 * index + 1;
			while (left < heapSize) {
				int largest = left + 1 < heapSize ? Math.max(arr[left], arr[left + 1]) : arr[left];
				if (arr[index] >= largest) {
					break;
				}
				largest = (largest == arr[left]) ? left : left + 1;
				swap(arr, index, largest);
				index = largest;
				left = 2 * index + 1;
			}
		}

		private void swap(int[] arr, int i, int j) {
			int tmp = arr[i];
			arr[i] = arr[j];
			arr[j] = tmp;
		}
	}
}
