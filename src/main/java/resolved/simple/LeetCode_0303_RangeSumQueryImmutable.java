package resolved.simple;

// 实现前缀和
// 303. 区域和检索 - 数组不可变
// https://leetcode.com/problems/range-sum-query-immutable/
public class LeetCode_0303_RangeSumQueryImmutable {
    // 在数组不可变的情况下，可以使用前缀和数组加速求区间和
    class NumArray {
        int[] preSum;

        public NumArray(int[] nums) {
            preSum = new int[nums.length];
            preSum[0] = nums[0];
            for (int i = 1; i < preSum.length; i++) {
                preSum[i] = preSum[i - 1] + nums[i];
            }
        }

        public int sumRange(int left, int right) {
            if (left == 0) {
                return preSum[right];
            }
            return preSum[right] - preSum[left - 1];
        }
    }
}
