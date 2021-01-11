package snippet;

import java.util.Arrays;

/**
 * 冒泡排序
 * 过程：
 * 在arr[0～N-1]范围上：
 * arr[0]和arr[1]，谁大谁来到1位置；arr[1]和arr[2]，谁大谁来到2位置…arr[N-2]和arr[N-1]，谁大谁来到N-1位置
 * <p>
 * 在arr[0～N-2]范围上，重复上面的过程，但最后一步是arr[N-3]和arr[N-2]，谁大谁来到N-2位置
 * 在arr[0～N-3]范围上，重复上面的过程，但最后一步是arr[N-4]和arr[N-3]，谁大谁来到N-3位置
 * …
 * 最后在arr[0～1]范围上，重复上面的过程，但最后一步是arr[0]和arr[1]，谁大谁来到1位置
 * <p>
 * <p>
 * 估算：
 * 很明显，如果arr长度为N，每一步常数操作的数量，依然如等差数列一般
 * 所以，总的常数操作数量 = a*(N^2) + b*N + c (a、b、c都是常数)
 * <p>
 * 所以冒泡排序的时间复杂度为O(N^2)。
 */
public class Code_0003_BubbleSort {
    public static void bubbleSort(int[] arr) {
        if (arr != null && arr.length >= 2) {
            for (int i = arr.length - 1; i > 0; i--) {
                for (int j = 0; j < i; j++) {
                    if (arr[j] > arr[j + 1]) {
                        swap(arr, j, j + 1);
                    }
                }
            }
        }
    }

    private static void swap(int[] array, int i, int j) {
        if (array == null || array.length < 2 || i == j) {
            return;
        }
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
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
 			bubbleSort(arr1);
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
