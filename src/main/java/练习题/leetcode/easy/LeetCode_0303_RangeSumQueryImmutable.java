package 练习题.leetcode.easy;

// 实现前缀和
// 303. 区域和检索 - 数组不可变
// https://leetcode.cn/problems/range-sum-query-immutable/
public class LeetCode_0303_RangeSumQueryImmutable {
	// 在数组不可变的情况下，可以使用前缀和数组加速求区间和
	class NumArray {
		int[] preSum;

		public NumArray(int[] nums) {
			// 0 位置弃而不用，这样方便一些
			preSum = new int[nums.length + 1];
			for (int i = 0; i < nums.length; i++) {
				preSum[i + 1] = nums[i] + preSum[i];
			}
		}

		public int sumRange(int left, int right) {
			return preSum[right + 1] - preSum[left];
		}
	}
}
