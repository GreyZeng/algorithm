package leetcode;

// 给你一个整数数组 nums，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
// Leetcode题目 : https://leetcode.com/problems/maximum-product-subarray/
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
