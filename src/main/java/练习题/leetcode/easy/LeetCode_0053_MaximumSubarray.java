package 练习题.leetcode.easy;

// 子数组的最大累加和
// 1. 简单方法
// 2. 动态规划的方法:子数组必须以i结尾的时候，所有可以得到的子数组中，最大累加和是多少？
// https://leetcode-cn.com/problems/maximum-subarray/
// 笔记：https://www.cnblogs.com/greyzeng/p/16326526.html
public class LeetCode_0053_MaximumSubarray {
    public static int maxSubArray1(int[] arr) {
        // dp[i]表示：子数组必须以i结尾的情况下，最大累加和是多少
        int[] dp = new int[arr.length];
        int max = arr[0];
        dp[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            dp[i] = arr[i] + (Math.max(dp[i - 1], 0));
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    public static int maxSubArray2(int[] arr) {
        int pre = arr[0];
        int max = pre;
        for (int i = 1; i < arr.length; i++) {
            int cur = arr[i] + (Math.max(pre, 0));
            max = Math.max(max, cur);
            pre = cur;
        }
        return max;
    }
}
