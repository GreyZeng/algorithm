package leetcode;

//A peak element is an element that is greater than its neighbors.
//
//        Given an input array nums, where nums[i] ≠ nums[i+1], find a peak element and return its index.
//
//        The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
//
//        You may imagine that nums[-1] = nums[n] = -∞.
//
//        Example 1:
//
//        Input: nums = [1,2,3,1]
//        Output: 2
//        Explanation: 3 is a peak element and your function should return the index number 2.
//        Example 2:
//
//        Input: nums = [1,2,1,3,5,6,4]
//        Output: 1 or 5
//        Explanation: Your function can return either index number 1 where the peak element is 2,
//        or index number 5 where the peak element is 6.
//        Follow up: Your solution should be in logarithmic complexity.
// 返回局部高点
// 二分
public class LeetCode_0162_FindPeakElement {

	public static int findPeakElement(int[] nums) {
		if (nums.length == 1) {
			return 0;
		}

		int L = 0;
		int R = nums.length - 1;
		if (nums[L] > nums[L + 1]) {
			return L;
		}
		if (nums[R] > nums[R - 1]) {
			return R;
		}
		int M;
		while (L < R) {
			M = L + ((R - L) >> 1);
			if (nums[M] > nums[M + 1] && nums[M] > nums[M - 1]) {
				return M;
			}
			if (nums[M] < nums[M + 1]) {
				L = M + 1;
			} else if (nums[M] < nums[M - 1]) {
				R = M - 1;
			}
		}
		return L;
	}

}
