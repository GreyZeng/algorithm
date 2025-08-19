package grey.algorithm.class10_quicksort;

import java.util.Arrays;

// 快速排序
// 笔记：https://www.cnblogs.com/greyzeng/p/16739515.html 快速排序
// partition过程
// 给定一个数组arr，和一个整数num。请把小于num的数放在数组的左边，等于num的数放在中间，大于num的数放在数组的右边。
// 要求额外空间复杂度O(1)，时间复杂度O(N)
// 快速排序3.0(随机快排+荷兰国旗技巧优化)
// 在arr[L..R]范围上，进行快速排序的过程：
// 0）在这个范围上，随机选一个数记为num，
// 1）用num对该范围做partition，< num的数在左部分，== num的数中间，>num的数在右部分。假设== num的数所在范围是[a,b]
// 2）对arr[L..a-1]进行快速排序(递归)
// 3）对arr[b+1..R]进行快速排序(递归) 因为每一次partition都会搞定一批数的位置且不会再变动，所以排序能完成
// 1）通过分析知道，划分值越靠近中间，性能越好；越靠近两边，性能越差
// 2）随机选一个数进行划分的目的就是让好情况和差情况都变成概率事件
// 3）把每一种情况都列出来，会有每种情况下的时间复杂度，但概率都是1/N
// 4）那么所有情况都考虑，时间复杂度就是这种概率模型下的长期期望！
// 时间复杂度O(N*logN)，额外空间复杂度O(logN)都是这么来的。
// 测评链接：https://www.lintcode.com/problem/464
public class Code_0015_QuickSort {
	public static void sortIntegers2(int[] arr) {
		if (arr == null || arr.length <= 1) {
			return;
		}
		quickSort(arr, 0, arr.length - 1);
	}

	/**
	 * 递归进行快速排序
	 * 
	 * @param arr 待排序数组
	 * @param l   左边界
	 * @param r   右边界
	 */
	public static void quickSort(int[] arr, int l, int r) {
		if (l >= r) {
			return;
		}
		// 随机选择一个基准值
		int pivotIndex = l + (int) (Math.random() * (r - l + 1));
		int pivot = arr[pivotIndex];

		// partition 操作，返回等于区域的左右边界
		int[] equalArea = partition(arr, l, r, pivot);

		// 递归排序小于区域
		quickSort(arr, l, equalArea[0] - 1);
		// 递归排序大于区域
		quickSort(arr, equalArea[1] + 1, r);
	}

	/**
	 * 荷兰国旗问题分区 对 arr[l...r] 范围进行分区，分为 < pivot, == pivot, > pivot 三个部分
	 * 
	 * @param arr   数组
	 * @param l     左边界
	 * @param r     右边界
	 * @param pivot 基准值
	 * @return 一个包含两个元素的数组，arr[0] 为等于区域的左边界，arr[1] 为等于区域的右边界
	 */
	public static int[] partition(int[] arr, int l, int r, int pivot) {
		int s = l;
		int e = r;
		int i = l;
		while (i <= e) {
			if (arr[i] < pivot) {
				swap(arr, i++, s++);
			} else if (arr[i] == pivot) {
				i++;
			} else {
				swap(arr, i, e--);
			}
		}
		return new int[] { s, e };
	}

	/**
	 * 交换数组中两个位置的元素
	 * 
	 * @param arr 数组
	 * @param i   索引 i
	 * @param j   索引 j
	 */
	public static void swap(int[] arr, int i, int j) {
		if (i != j) {
			int tmp = arr[i];
			arr[i] = arr[j];
			arr[j] = tmp;
		}
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
			int[] copyArray = copyArray(arr);
			Arrays.sort(arr);
			sortIntegers2(copyArray);
			if (!sameValue(arr, copyArray)) {
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
		for (int i = 0; i < copyArray.length; i++) {
			copyArray[i] = arr[i];
		}
		return copyArray;
	}
}
