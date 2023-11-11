package git.snippet.simple;

// 实现前缀和
// 303. 区域和检索 - 数组不可变
// https://leetcode.com/problems/range-sum-query-immutable/
public class LeetCode_0303_RangeSumQueryImmutable {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5};
        NumArray array = new NumArray(nums);
        int sum = array.sumRange(0, 2); // 6
        System.out.println(sum);
        sum = array.sumRange(2, 4); // 12
        System.out.println(sum);
    }

    static class NumArray {
        int[] preSum;

        public NumArray(int[] nums) {
            preSum = new int[nums.length];
            preSum[0] = nums[0];
            for (int i = 1; i < nums.length; i++) {
                preSum[i] = nums[i] + preSum[i - 1];
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
