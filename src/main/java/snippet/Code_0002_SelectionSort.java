package snippet;

import java.util.Arrays;

/**
 * 选择排序 过程： arr[0～N-1]范围上，找到最小值所在的位置，然后把最小值交换到0位置。
 * arr[1～N-1]范围上，找到最小值所在的位置，然后把最小值交换到1位置。 arr[2～N-1]范围上，找到最小值所在的位置，然后把最小值交换到2位置。
 * … arr[N-1～N-1]范围上，找到最小值位置，然后把最小值交换到N-1位置。
 * <p>
 * 估算： 很明显，如果arr长度为N，每一步常数操作的数量，如等差数列一般 所以，总的常数操作数量 = a*(N^2) + b*N + c
 * (a、b、c都是常数)
 * <p>
 * 所以选择排序的时间复杂度为O(N^2)。
 */
public class Code_0002_SelectionSort {

	public static void selectionSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		for (int i = 0; i < arr.length - 1; i++) {
			int min = i;
			for (int j = i + 1; j < arr.length; j++) {
				min = arr[j] < arr[min] ? j : min;
			}
			swap(arr, i, min);
		}
	}

	private static void swap(int[] arr, int i, int j) {
		if (arr == null || arr.length < 2 || i == j) {
			return;
		}
		arr[i] = arr[i] ^ arr[j];
		arr[j] = arr[i] ^ arr[j];
		arr[i] = arr[i] ^ arr[j];
	}

	// for test
	public static void absRight(int[] arr) {
		Arrays.sort(arr);
	}

	private static int[] generateRandomArray(int maxSize, int maxValue) {
		// Math.random() -> [0,1)
		// Math.random() * N -> [0,N)
		// (int)(Math.random()*N) -> [0,N-1]

		int[] arr = new int[(int) (Math.random() * (maxSize + 1))];
		for (int i = 0; i < arr.length; i++) {
			// [-? , +?]
			arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) ((maxValue + 1) * Math.random());
		}
		return arr;
	}

	private static void printArray(int[] arr1) {
		if (null == arr1) {
			return;
		}
		for (int i = 0; i < arr1.length; i++) {
			System.out.print(arr1[i] + " ");
		}
		System.out.println();
	}

	private static boolean isEqual(int[] arr1, int[] arr2) {
		if (arr1 == null && arr2 == null) {
			return true;
		}
		if (arr1 == null) {
			return false;
		}
		if (arr2 == null) {
			return false;
		}
		if (arr1.length != arr2.length) {
			return false;
		}
		for (int i = 0; i < arr1.length; i++) {
			if (arr1[i] != arr2[i]) {
				return false;
			}
		}
		return true;
	}

	private static int[] copyArray(int[] arr1) {
		if (arr1 == null) {
			return null;
		}
		int[] arr2 = new int[arr1.length];
		for (int i = 0; i < arr1.length; i++) {
			arr2[i] = arr1[i];
		}
		return arr2;
	}

	// for test
	public static void main(String[] args) {
		int times = 500000; // 测试的次数
		int maxSize = 100; // 数组的最大长度是100
		int maxValue = 100; // 数组元素的大小[-100,100]
		boolean succeed = true;
		for (int i = 0; i < times; i++) {
			int[] arr1 = generateRandomArray(maxSize, maxValue);
			int[] arr2 = copyArray(arr1);
			selectionSort(arr1);
			absRight(arr2);
			if (!isEqual(arr1, arr2)) {
				succeed = false;
				printArray(arr1);
				printArray(arr2);
				break;
			}
		}
		System.out.println(succeed ? "Nice!" : "Fucking fucked!");
	}
}
