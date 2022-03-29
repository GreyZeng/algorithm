package leetcode.easy;

// 子数组的最大累加和
// 1. 简单方法
// 2. 动态规划的方法:子数组必须以i结尾的时候，所有可以得到的子数组中，最大累加和是多少？
// https://leetcode-cn.com/problems/maximum-subarray/
public class LeetCode_0053_MaximumSubarray {
	public static int maxSubArray(int[] arr) {
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
