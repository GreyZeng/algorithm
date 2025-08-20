package grey.algorithm.code09_mergesort;

//归并排序
//1）整体是递归，左边排好序+右边排好序+merge让整体有序
//2）让其整体有序的过程里用了排外序方法
//3）利用master公式来求解时间复杂度
//T(N) = 2*T(N/2) + O(N^1)
//根据master可知时间复杂度为O(N*logN)
//merge过程需要辅助数组，所以额外空间复杂度为O(N)
//归并排序的实质是把比较行为变成了有序信息并传递，比O(N^2)的排序快
//笔记：https://www.cnblogs.com/greyzeng/p/16653063.html
//测评链接：https://www.luogu.com.cn/problem/P1177 
// 或者：https://leetcode.com/problems/sort-an-array/
//acm测评风格，注意输入输出的处理
import java.util.Arrays;

public class Code_0009_MergeSort {

	// merge sort的递归写法
	public static void sort(int[] arr) {
		mergeSort(arr, 0, arr.length - 1);
	}

	// 递归解法
	public static void mergeSort(int[] arr, int l, int r) {
		if (l >= r) {
			// 终止条件
			return;
		}
		int mid = l + ((r - l) >> 1);
		mergeSort(arr, l, mid);
		mergeSort(arr, mid + 1, r);
		merge(arr, l, mid, r);
	}

	public static void merge(int[] arr, int l, int m, int r) {
		int[] help = new int[r - l + 1];
		int s = l;
		int e = m + 1;
		int i = 0;
		while (s <= m && e <= r) {
			if (arr[s] <= arr[e]) {
				help[i++] = arr[s++];
			} else {
				help[i++] = arr[e++];
			}
		}
		while (s <= m) {
			help[i++] = arr[s++];
		}
		while (e <= r) {
			help[i++] = arr[e++];
		}
		for (i = 0; i < help.length; i++) {
			arr[l++] = help[i];
		}
	}

	// 迭代版本的merge sort
	public static void sort2(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		int n = arr.length;
		for (int subSize = 1; subSize < n; subSize <<= 1) {
			// 每组1,2,4,8这样扩
			for (int leftIndex = 0; leftIndex < n; leftIndex = leftIndex + (subSize * 2)) {
				// 第一组的起始位置
				int m = leftIndex + subSize - 1;
				if (m >= n - 1) {
					// 没有右组，已经排好了
					break;
				}
				int rightEnd = Math.min(leftIndex + (subSize * 2) - 1, n - 1);
				merge(arr, leftIndex, m, rightEnd);
			}
		}
	}

	public static void swap(int[] arr, int i, int j) {
		if (null == arr || arr.length <= 1) {
			return;
		}
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	public static void main(String[] args) {

		// 数组长度1~500，等概率随机
		int num = 500;
		// 每个值的大小在1~1024，等概率随机
		int value = 1024;
		// 测试次数
		int testTimes = 50000;
		System.out.println("测试开始");
		for (int i = 0; i < testTimes; i++) {
			int[] arr = generateArray(num, value);
			int[] copyArray1 = copyArray(arr);
			int[] copyArray2 = copyArray(arr);
			Arrays.sort(arr);
			sort(copyArray1);
			sort2(copyArray2);
			if (!sameValue(arr, copyArray1)) {
				System.out.println("出错了！");
				break;
			}
			if (!sameValue(arr, copyArray2)) {
				System.out.println("出错了！");
				break;
			}
		}
		System.out.println("测试结束");
	}

	private static boolean sameValue(int[] arr1, int[] arr2) {
		if (null == arr1) {
			return null != arr2;
		}
		if (null == arr2) {
			return null != arr1;
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

	private static int[] generateArray(int num, int value) {
		int[] arr = new int[(int) (Math.random() * num) + 1];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * value) + 1;
		}
		return arr;
	}

	private static int[] copyArray(int[] arr) {
		int[] copyArray = new int[arr.length];
		System.arraycopy(arr, 0, copyArray, 0, copyArray.length);
		return copyArray;
	}

}
