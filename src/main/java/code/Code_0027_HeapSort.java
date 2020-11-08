package code;

/**
 * 堆排序 1，先让整个数组都变成大根堆结构，建立堆的过程: 1)从上到下的方法，时间复杂度为O(N*logN) 2)从下到上的方法，时间复杂度为O(N)
 * 2，把堆的最大值和堆末尾的值交换， 然后减少堆的大小之后，再去调整堆， 一直周而复始，时间复杂度为O(N*logN) 3，堆的大小减小成0之后，排序完成
 */
public class Code_0027_HeapSort {
	public static void heapSort(int[] arr) {
		if (null == arr || arr.length < 2) {
			return;
		}
		int heapSize = arr.length;
		// 调整成大根堆
		int i;
		for (i = heapSize - 1; i >= 0; i--) {
			heapify(arr, i, heapSize);
		}
		 
		while (heapSize > 0) { 
			heapify(arr, 0, heapSize);
			swap(arr, 0, --heapSize);
		}
	}

	private static void heapify(int[] arr, int index, int heapSize) {
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

	private static void swap(int[] arr, int i, int j) {
		int t = arr[i];
		arr[i] = arr[j];
		arr[j] = t;
	}
	
 
	public static void main(String[] args) {
		int[] nums= {2,3,5,1,65,87,2,4,6};
		heapSort(nums);
		for (int t : nums) {
			System.out.print(t + " ");
		}
	}
}
