// Given an integer array nums, you need to find one continuous subarray that if you only sort this subarray in ascending order, then the whole array will be sorted in ascending order.

// Return the shortest such subarray and output its length.

// Example 1:

// Input: nums = [2,6,4,8,10,9,15]
// Output: 5
// Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.
// Example 2:

// Input: nums = [1,2,3,4]
// Output: 0
// Example 3:

// Input: nums = [1]
// Output: 0

// Constraints:

// 1 <= nums.length <= 10^4
// -10^5 <= nums[i] <= 10^5
package leetcode.medium;

// https://www.nowcoder.com/questionTerminal/fccb5d14b44b4b99b34839bdf20588e9
//tips:
//左max 划过部分的最大值，从1开始
//当前数 > 左max 画x号  当前数 <= 左max 画对号
//从左边往右遍历，得到最右边画x的位置，假设为x
//从右边往左遍历，同理，得到最左边画x的位置，假设为y
//[x,y]就是最小需要排序的数组
//1,2,6,5,4,3,8,9
public class LeetCode_0581_ShortestUnsortedContinuousSubarray {
	public static void main(String[] args) {
		int[] arr = { 5, 6, 7, 9, 9, 12, 14, 15 };
		System.out.println(findUnsortedSubarray(arr));
	}

	public static int findUnsortedSubarray(int[] nums) {
		if (nums == null || nums.length < 2) {
			return 0;
		}
		int N = nums.length;
		int right = -1;
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			if (max > nums[i]) {
				right = i;
			}
			max = Math.max(max, nums[i]);
		}
		int min = Integer.MAX_VALUE;
		int left = N;
		for (int i = N - 1; i >= 0; i--) {
			if (min < nums[i]) {
				left = i;
			}
			min = Math.min(min, nums[i]);
		}
		return Math.max(0, right - left + 1);
	}

}
