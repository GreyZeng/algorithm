package mergesort;

import java.util.Arrays;

/**
 * 归并排序
 * <p>
 * 1）整体是递归，左边排好序+右边排好序+merge让整体有序
 * <p>
 * 2）让其整体有序的过程里用了排外序方法
 * <p>
 * 3）利用master公式来求解时间复杂度
 * <p>
 * T(N) = 2*T(N/2) + O(N^1)
 * <p>
 * 根据master可知时间复杂度为O(N*logN)
 * <p>
 * merge过程需要辅助数组，所以额外空间复杂度为O(N)
 * <p>
 * 归并排序的实质是把比较行为变成了有序信息并传递，比O(N^2)的排序快
 */
// 笔记：https://www.cnblogs.com/greyzeng/p/16653063.html
public class Code_MergeSort {
	// 递归方法实现
	public static void mergeSort1(int[] arr) {
		if (null == arr || arr.length <= 1) {
			return;
		}
		 p(arr, 0, arr.length - 1);
	}
	public static void p(int[] arr, int l, int r) {
		if(l < r) {
			int m = l + (( r - l)>>1);
			p(arr, l, m);
			p(arr, m + 1, r);
			merge(arr, l,m, r);
		}
	}
	public static void merge(int[] arr, int l,int m, int r) {
		int[] help = new int[r - l + 1];
		int i = 0;
		int ls = l;
		int rs = m + 1;
		while (ls <= m && rs <= r) {
			if (arr[ls] <= arr[rs]) {
				help[i++] = arr[ls++];
			} else {
				help[i++] = arr[rs++];
			}
		}
		while (ls <= m) {
			help[i++] = arr[ls++];
		}
		while (rs <= r) {
			help[i++] = arr[rs++];
		}
		for (i = 0; i < help.length; i++) {
			arr[l + i] = help[i];
		}
	}

	// 非递归方法实现
	public static void mergeSort2(int[] arr) {
		if(null != arr && arr.length > 1) {
			int group = 1;
			int len = arr.length;
			while (group < len) {
				int ls = 0;
				while (ls < len) {
					if (ls + group >= len) {
						break;
					}
					int mid = ls + group - 1;
					int le = Math.min(mid + group, len - 1);
					merge(arr, ls, mid, le);
					ls = le + 1;
				}
				if (group * 2 > len) {
					break;
				}
				group <<= 1;
			}
		}
	}

	// for test
	public static int[] generateRandomArray(int maxSize, int maxValue) {
		int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
		}
		return arr;
	}

	// for test
	public static int[] copyArray(int[] arr) {
		if (arr == null) {
			return null;
		}
		int[] res = new int[arr.length];
		System.arraycopy(arr, 0, res, 0, arr.length);
		return res;
	}

	// for test
	public static boolean isEqual(int[] arr1, int[] arr2) {
		if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
			return false;
		}
		if (arr1 == null && arr2 == null) {
			return true;
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

	// for test
	public static void printArray(int[] arr) {
		if (arr == null) {
			return;
		}
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	// for test
	public static void main(String[] args) {
		int testTime = 500000;
		int maxSize = 100;
		int maxValue = 100;
		System.out.println("测试开始");
		for (int i = 0; i < testTime; i++) {
			int[] arr1 = generateRandomArray(maxSize, maxValue);
			int[] arr2 = copyArray(arr1);
			int[] arr3 = copyArray(arr2);
			mergeSort1(arr1);
			mergeSort2(arr2);
			// 注：这里一定要用另外一种排序算法验证，
			// 因为mergesort的递归和非递归都调用了merge方法，可能导致递归和非递归的结果都是错的，但是却可以通过对数器
			Arrays.sort(arr3);
			if (!isEqual(arr1, arr2) || !isEqual(arr1, arr3) || !isEqual(arr2, arr3)) {
				System.out.println("出错了！");
				printArray(arr1);
				printArray(arr2);
				printArray(arr3);
				break;
			}
		}
		System.out.println("测试结束");
	}
}