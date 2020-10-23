package leetcode;

// 方法1. 快排改进 (概率收敛到O(N))
// 方法2. bfprt算法 (严格收敛到O(N)) TODO
public class LeetCode_0215_KthLargestElementInAnArray {
	// 第K小 == 第 nums.length - k 大
	public static int findKthLargest(int[] nums, int k) {
		return p(nums, 0, nums.length - 1, nums.length - k);
	}

	public static int p(int[] nums, int L, int R, int index) {
		if (L == R) {
			return nums[L];
		}
		int pivot = nums[L + (int) (Math.random() * (R - L + 1))];
		int[] range = partition(nums, L, R, pivot);
		if (index >= range[0] && index <= range[1]) {
			return nums[index];
		} else if (index > range[1]) {
			return p(nums, range[1] + 1, R, index);
		} else {
			return p(nums, L, range[0] - 1, index);
		}
	}

	private static int[] partition(int[] nums, int l, int r, int pivot) {

		int less = l - 1;
		int more = r + 1;
		int c = l;
		while (c < more) {
			if (nums[c] < pivot) {
				swap(nums, c++, ++less);
			} else if (nums[c] > pivot) {
				swap(nums, c, --more);
			} else {
				c++;
			}
		}
		return new int[] { less + 1, more - 1 };
	}

	public static void swap(int[] nums, int R, int L) {
		if (L == R) {
			return;
		}
		nums[R] = nums[R] ^ nums[L];
		nums[L] = nums[R] ^ nums[L];
		nums[R] = nums[R] ^ nums[L];
	}

}
