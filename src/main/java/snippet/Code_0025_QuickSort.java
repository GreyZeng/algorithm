package snippet;

import java.util.*;

/**
 * 快速排序 给定一个数组arr，和一个整数num。请把小于num的数放在数组的左边，等于num的数放在中间，大于num的数放在数组的右边。
 * <p>
 * 要求额外空间复杂度O(1)，时间复杂度O(N)
 * <p>
 * 快速排序3.0(随机快排+荷兰国旗技巧优化) 在arr[L..R]范围上，进行快速排序的过程： 1）在这个范围上，随机选一个数记为num，
 * 1）用num对该范围做partition，< num的数在左部分，== num的数中间，>num的数在右部分。假设== num的数所在范围是[a,b]
 * 2）对arr[L..a-1]进行快速排序(递归) 3）对arr[b+1..R]进行快速排序(递归)
 * 因为每一次partition都会搞定一批数的位置且不会再变动，所以排序能完成 1）通过分析知道，划分值越靠近中间，性能越好；越靠近两边，性能越差
 * 2）随机选一个数进行划分的目的就是让好情况和差情况都变成概率事件 3）把每一种情况都列出来，会有每种情况下的时间复杂度，但概率都是1/N
 * 4）那么所有情况都考虑，时间复杂度就是这种概率模型下的长期期望！
 * <p>
 * 时间复杂度O(N*logN)，额外空间复杂度O(logN)都是这么来的。
 */
// 测评：https://www.lintcode.com/problem/464
public class Code_0025_QuickSort {

	// 递归方法
	public static void quickSort(int[] arr) {
		if (null == arr || arr.length < 2) {
			return;
		}
		process(arr, 0, arr.length - 1);
	}

	private static void process(int[] arr, int L, int R) {
		if (L >= R) {
			return;
		}
		swap(arr, L + (int) (Math.random() * (R - L + 1)), R);
		int[] range = sortColors(arr, L, R);
		process(arr, L, range[0] - 1);
		process(arr, range[1] + 1, R);
	}

	public static void swap(int[] arr, int i, int j) {
		if (i != j) {
			arr[i] = arr[i] ^ arr[j];
			arr[j] = arr[i] ^ arr[j];
			arr[i] = arr[i] ^ arr[j];
		}
	}

	public static int[] sortColors(int[] arr, int L, int R) {
		int target = arr[R];
		int less = L - 1;
		int more = R + 1;
		int index = L;
		while (index < more) {
			if (arr[index] > target) {
				swap(arr, index, --more);
			} else if (arr[index] < target) {
				swap(arr, index++, ++less);
			} else {
				index++;
			}
		}
		return new int[] { less + 1, more - 1 }; 
	}

}
