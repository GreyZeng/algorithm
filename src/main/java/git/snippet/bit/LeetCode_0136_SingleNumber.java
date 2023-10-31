package git.snippet.bit;

// 一个数组中有一种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这种数
//
// 备注:
// 时间复杂度O(n),额外空间复杂度O(1)。
// https://www.cnblogs.com/greyzeng/p/15385402.html
// 牛客的测评链接：https://www.nowcoder.com/questionTerminal/d0ef3e33e63a49dd99c90aeef306b0fc
public class LeetCode_0136_SingleNumber {

  public int singleNumber(int[] nums) {
    for (int i = 1; i < nums.length; i++) {
      nums[0] ^= nums[i];
    }
    return nums[0];
  }
}
