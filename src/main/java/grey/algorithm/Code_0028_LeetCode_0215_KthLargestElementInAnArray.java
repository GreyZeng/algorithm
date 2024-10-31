package grey.algorithm;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 无序数组中求第K大的数，K从1开始算
 * 
 * 测试链接 : https://leetcode.com/problems/kth-largest-element-in-an-array/
 * 
 * 
 * 方法0. 大根堆 O(N*logK)
 *
 * <p>
 * 方法1. 快排改进 (概率收敛到O(N), 空间复杂度O(1))
 *
 * <p>
 * 方法2. bfprt算法 (严格收敛到O(N),但是空间复杂度O(N))
 */
public class Code_0028_LeetCode_0215_KthLargestElementInAnArray {

	// 快排改进算法(时间复杂度O(N))
	// 第K小 == 第 nums.length - k + 1 大
	public static int findKthLargest2(int[] nums, int k) {
		return process(nums, 0, nums.length - 1, k - 1);
	}

	// arr 在L...R范围内，如果要从大到小排序，请返回index位置的值
	private static int process(int[] arr, int L, int R, int index) {
		if (L == R) {
			return arr[R];
		}
		int pivot = arr[L + (int) (Math.random() * (R - L + 1))];
		int[] range = partition(arr, L, R, pivot);
		if (index >= range[0] && index <= range[1]) {
			return pivot;
		} else if (index < range[0]) {
			return process(arr, L, range[0] - 1, index);
		} else {
			return process(arr, range[1] + 1, R, index);
		}
	}

	public static int[] partition(int[] arr, int L, int R, int pivot) {
		int less = L - 1;
		int more = R + 1;
		while (L < more) {
			if (arr[L] > pivot) {
				swap(arr, L++, ++less);
			} else if (arr[L] == pivot) {
				L++;
			} else {
				swap(arr, L, --more);
			}
		}
		return new int[] { less + 1, more - 1 };
	}

	public static void swap(int[] nums, int t, int m) {
		int tmp = nums[m];
		nums[m] = nums[t];
		nums[t] = tmp;
	}

	// bfprt算法
	public static int findKthLargest(int[] nums, int k) {
		return bfprt(nums, 0, nums.length - 1, k - 1);
	}

	private static int bfprt(int[] nums, int L, int R, int index) {
		if (L == R) {
			return nums[L];
		}
		int pivot = medianOfMedians(nums, L, R);
		int[] range = partition(nums, L, R, pivot);
		if (index >= range[0] && index <= range[1]) {
			return pivot;
		} else if (index < range[0]) {
			return bfprt(nums, L, range[0] - 1, index);
		} else {
			return bfprt(nums, range[1] + 1, R, index);
		}
	}

	// 将arr分成每五个元素一组，不足一组的补齐一组
	// 对每组进行排序
	// 取出每组的中位数，组成一个新的数组
	// 对新的数组求其中位数，这个中位数就是我们需要的值
	public static int medianOfMedians(int[] arr, int L, int R) {
		int size = R - L + 1;
		int offSize = size % 5 == 0 ? 0 : 1;
		int[] mArr = new int[size / 5 + offSize];
		for (int i = 0; i < mArr.length; i++) {
			// 每一组的第一个位置
			int teamFirst = L + i * 5;
			int median = getMedian(arr, teamFirst, Math.min(R, teamFirst + 4));
			mArr[i] = median;
		}
		return bfprt(mArr, 0, mArr.length - 1, (mArr.length - 1) / 2);
	}

	public static int getMedian(int[] arr, int L, int R) {
		Arrays.sort(arr, L, R);
		return arr[(R + L) / 2];
	}

	// 第K大 --> 第 (len - K + 1) 小
	// k需要小于nums.length ，否则没有意义
	public static int findKthLargest3(int[] nums, int k) {
		PriorityQueue<Integer> heap = new PriorityQueue<>();
		for (int i = 0; i < k; i++) {
			heap.offer(nums[i]);
		}
		for (int i = k; i < nums.length; i++) {
			heap.offer(nums[i]);
			heap.poll();
		}
		return heap.peek();
	}
}
