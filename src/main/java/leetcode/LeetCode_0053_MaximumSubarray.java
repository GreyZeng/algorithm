package leetcode;

// 子数组的最大累加和
// 1. 简单方法
// 2. 动态规划的方法
public class LeetCode_0053_MaximumSubarray {
    public static int maxSubArray(int[] arr) {
        int N = arr.length;
        if (N == 1) {
            return arr[0];
        }
        //int[] dp = new int[N];
        int pre = arr[0];
        int max = pre;
        int cur;
        for (int i = 1; i < N; i++) {
            cur = arr[i];
            if (pre >= 0) {
                cur += pre;
            }
            pre = cur;
            max = Math.max(cur, max);
        }
        return max;
    }

    // TODO 选组合，不能相邻
    public static int maxSubArray3(int[] nums) {
        return -1;
    }
    public static int maxSubArray2(int[] arr) {
        int N = arr.length;
        if (N == 1) {
            return arr[0];
        }
        int[] dp = new int[N];
        dp[0] = arr[0];
        int max = dp[0];
        for (int i = 1; i < N; i++) {
            if (dp[i - 1] >= 0) {
                dp[i] = dp[i - 1] + arr[i];
            } else {
                dp[i] = arr[i];
            }
            max = Math.max(dp[i], max);
        }
        return max;
    }



}
