package bit;

// 一个数组中有两种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这两种数
// https://www.cnblogs.com/greyzeng/p/15385402.html
// https://leetcode.com/problems/single-number-iii/
public class LeetCode_0260_SingleNumberIII {
  public int[] singleNumber(int[] nums) {
    int eor = nums[0];
    for (int i = 1; i < nums.length; i++) {
      eor ^= nums[i];
    }
    // eor = a ^ b;
    int leftOne = eor & (-eor);
    int a = 0;
    for (int i = 0; i < nums.length; i++) {
      if ((nums[i] & leftOne) == 0) {
        a ^= nums[i];
      }
    }
    int b = a ^ eor;
    return new int[] {a, b};
  }
}
