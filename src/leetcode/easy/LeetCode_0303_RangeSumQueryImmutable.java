package leetcode.easy;

// 实现前缀和
// https://leetcode-cn.com/problems/range-sum-query-immutable/
public class LeetCode_0303_RangeSumQueryImmutable {

    // 使用前缀和数组加速求区间和
    class NumArray {
        int[] preSum;

        public NumArray(int[] nums) {
            preSum = new int[nums.length];
            preSum[0] = nums[0];
            for (int i = 1; i < nums.length; i++) {
                preSum[i] = preSum[i - 1] + nums[i];
            }
        }

        public int sumRange(int l, int r) {
            if (l == 0) {
                return preSum[r];
            }
            return preSum[r] - preSum[l - 1];
        }
    }
}
