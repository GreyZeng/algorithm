/*Given an integer array nums, find the contiguous subarray (containing at least one number)
which has the largest sum and return its sum.

        Follow up: If you have figured out the O(n) solution,
        try coding another solution using the divide and conquer approach, which is more subtle.



        Example 1:

        Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
        Output: 6
        Explanation: [4,-1,2,1] has the largest sum = 6.
        Example 2:

        Input: nums = [1]
        Output: 1
        Example 3:

        Input: nums = [0]
        Output: 0
        Example 4:

        Input: nums = [-1]
        Output: -1
        Example 5:

        Input: nums = [-2147483647]
        Output: -2147483647


        Constraints:

        1 <= nums.length <= 2 * 10^4
        -2^31 <= nums[i] <= 2^31 - 1*/
package leetcode;

// 子数组的最大累加和
// 1. 简单方法
// 2. 动态规划的方法
// TODO 选组合，不能相邻
public class LeetCode_0053_MaximumSubarray {

    public static int maxSubArray(int[] arr) {
        int pre = arr[0];
        int max = pre;
        int cur;
        for (int i = 1; i < arr.length; i++) {
            cur = arr[i] + (Math.max(pre, 0));
            max = Math.max(max, cur);
            pre = cur;
        }
        return max;
    }
}
