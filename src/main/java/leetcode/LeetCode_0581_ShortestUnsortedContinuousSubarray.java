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
package leetcode;

public class LeetCode_0581_ShortestUnsortedContinuousSubarray {


    public static int findUnsortedSubarray(int[] nums) {
        int N = nums.length;
        int max = Integer.MIN_VALUE;
        // i位置左边最大值是max，i来到满足nums[i]<max的最右位置（再右就不需要排序了）
        int right = -1;
        for (int i = 0; i < N; i++) {
            if (max > nums[i]) {
                right = i;
            }
            max = Math.max(max, nums[i]);
        }

        int min = Integer.MAX_VALUE;
        // i位置右边边最小值是min，i来到满足nums[i]>min的最左边位置（再左就不需要排序了）
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
