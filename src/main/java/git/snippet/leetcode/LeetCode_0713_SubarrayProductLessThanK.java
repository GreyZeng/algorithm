package git.snippet.leetcode;

// 乘积小于 K 的子数组
// 给你一个整数数组 nums 和一个整数 k ，请你返回子数组内所有元素的乘积严格小于 k 的连续子数组的数目。
// https://leetcode.cn/problems/subarray-product-less-than-k/
public class LeetCode_0713_SubarrayProductLessThanK {
  public int numSubarrayProductLessThanK(int[] nums, int k) {
    if (k <= 1) {
      return 0;
    }
    int L = 0;
    int R = 0;
    int count = 0;
    int base = 1;
    while (R < nums.length) {
      base *= nums[R];
      while (base >= k) {
        base /= nums[L++];
      }
      count += (R - L + 1);
      R++;
    }
    return count;
  }
}
