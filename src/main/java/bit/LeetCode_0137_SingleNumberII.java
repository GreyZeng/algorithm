package bit;

// Given an integer array nums where every element appears three times except for one,
// which appears exactly once. Find the single element and return it.
// You must implement a solution with a linear runtime complexity and use only constant extra space.
// https://www.cnblogs.com/greyzeng/p/15385402.html
public class LeetCode_0137_SingleNumberII {
  public int singleNumber(int[] nums) {
    int[] bit = new int[32];
    for (int n : nums) {
      for (int i = 31; i >= 0; i--) {
        bit[i] += ((n >>> i) & 1);
      }
    }
    int ans = 0;
    for (int i = 0; i < 32; i++) {
      if (bit[i] % 3 != 0) {
        ans |= (1 << i);
      }
    }
    return ans;
  }
}
