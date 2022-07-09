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
    public static int maxProduct(int[] arr) {
        int ans = arr[0];
        int max = arr[0];
        int min = arr[0];
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int c = arr[i];
            int p1 = min * c;
            int p2 = max * c;
            max = Math.max(c, Math.max(p1, p2));
            min = Math.min(c, Math.min(p1, p2));
            ans = Math.max(ans, max);
        }
        return ans;
    }
}
