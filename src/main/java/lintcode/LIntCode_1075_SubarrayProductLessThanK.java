package lintcode;

// https://www.lintcode.com/problem/1075/
public class LIntCode_1075_SubarrayProductLessThanK {
  public int numSubarrayProductLessThanK(int[] nums, int k) {
    int l = 0;
    int r = 0;
    int len = nums.length;
    int count = 0;
    int base = 1;
    while (r < len) {
      base *= nums[r];
      while (base >= k) {
        base /= nums[l++];
      }
      count += (r - l + 1);
      r++;
    }
    return count;
  }
}
