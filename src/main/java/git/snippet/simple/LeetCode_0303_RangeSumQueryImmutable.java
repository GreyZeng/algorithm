package git.snippet.simple;

// 实现前缀和
// 303. 区域和检索 - 数组不可变
// 笔记：https://www.cnblogs.com/greyzeng/p/17118195.html
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
            // 0 位置弃而不用
            // 在判断 rangeSum 的时候可以省去一个 if
            preSum = new int[nums.length + 1];
            for (int i = 1; i < preSum.length; i++) {
                preSum[i] = nums[i - 1] + preSum[i - 1];
            }
        }

        // preSum 1 = nums 0 + preSum 0
        // preSum 2 = nums 1 + preSum 1
        // preSum 3 = nums 2 + preSum 2
        // preSum 4 = nums 3 + preSum 3
        //      0 1 2 3
        // nums 1 2 3 4
        // pres 0 1 3 6 10
        public int sumRange(int left, int right) {
            // 1 ~ 2 => preSum 3 - preSum 1 => nums 2 + nums 1 + nums 0 - nums 0
            return preSum[right + 1] - preSum[left];
        }
    }
}
