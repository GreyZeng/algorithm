// Given an array of integers with possible duplicates, randomly output the index of a given target
// number.
// You can assume that the given target number must exist in the array.
//
// Note:
// The array size can be very large. Solution that uses too much extra space will not pass the
// judge.
//
// Example:
//
// int[] nums = new int[] {1,2,3,3,3};
// Solution solution = new Solution(nums);
//
// pick(3) should return either index 2, 3, or 4 randomly. Each index should have equal probability
// of returning.
// solution.pick(3);
//
// pick(1) should return 0. Since in the array only nums[0] is equal to 1.
// solution.pick(1);
package leetcode.medium;

import java.util.Random;

public class LeetCode_0398_RandomPickIndex {
    class Solution {
        int[] nums;
        Random random;

        public Solution(int[] nums) {
            this.nums = nums;
            random = new Random();
        }

        public int pick(int target) {
            int count = 0;
            int index = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == target) {
                    count++;
                    // 如果只有一个的话，count=1, random.nextInt(count)必为0,所以index至少一定可以抓到第一个值
                    if (random.nextInt(count) == 0) {
                        index = i;
                    }
                }
            }
            return index;
        }
    }
}
