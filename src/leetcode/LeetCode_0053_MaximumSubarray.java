package leetcode;

public class LeetCode_0053_MaximumSubarray {

    // 子数组的最大累加和
    // 1. 简单方法
    // 2. 动态规划的方法
    public static int maxSubArray(int[] nums) {
        int N = nums.length;
        if (N == 1) {
            return nums[0];
        }
        int cur = nums[0];
        int max = nums[0];
        for (int i = 1; i < N; i++) {
            cur = Math.max(cur + nums[i], nums[i]);
            max = Math.max(cur, max);
        }
        return max;
    }


    // 选组合，不能相邻
    public static int maxSubArray2(int[] nums) {
        return -1;
    }
}
