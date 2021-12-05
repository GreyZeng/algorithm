package leetcode.medium;

//Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.
//
//        Example 1:
//
//        Input: [2,3,-2,4]
//        Output: 6
//        Explanation: [2,3] has the largest product 6.
//        Example 2:
//
//        Input: [-2,0,-1]
//        Output: 0
//        Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
public class LeetCode_0152_MaximumProductSubarray {

    // 最大累积有可能需要前一步的最小累积
    public static int maxProduct(int[] nums) {
        int preMax = nums[0];
        int preMin = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int t1 = nums[i];
            int t2 = nums[i] * preMax;
            int t3 = nums[i] * preMin;
            preMax = Math.max(Math.max(t1, t2), t3);
            preMin = Math.min(Math.min(t1, t2), t3);
            max = Math.max(preMax, max);
        }
        return max;
    }
}
