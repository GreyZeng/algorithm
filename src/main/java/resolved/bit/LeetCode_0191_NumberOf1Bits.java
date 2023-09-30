package resolved.bit;

// TODO
// 编写一个函数，输入是一个无符号整数（以二进制串的形式），返回其二进制表达式中数字位数为 '1' 的个数（也被称为汉明重量）。
// 示例 1：
// 输入：00000000000000000000000000001011
// 输出：3
// 解释：输入的二进制串 00000000000000000000000000001011中，共有三位为 '1'。
// 示例 2：
// 输入：00000000000000000000000010000000
// 输出：1
// 解释：输入的二进制串 00000000000000000000000010000000中，共有一位为 '1'。
// 示例 3：
// 输入：11111111111111111111111111111101
// 输出：31
// 解释：输入的二进制串 11111111111111111111111111111101 中，共有 31 位为 '1'。
// 提示：
// 输入必须是长度为 32 的 二进制串 。
// 进阶：
// 如果多次调用这个函数，你将如何优化你的算法？
// LeetCode: https://leetcode.com/problems/number-of-1-bits/
public class LeetCode_0191_NumberOf1Bits {
  // TODO
  // 最优解
  public int hammingWeight(int n) {
    n = (n & 0x55555555) + ((n >>> 1) & 0x55555555);
    n = (n & 0x33333333) + ((n >>> 2) & 0x33333333);
    n = (n & 0x0f0f0f0f) + ((n >>> 4) & 0x0f0f0f0f);
    n = (n & 0x00ff00ff) + ((n >>> 8) & 0x00ff00ff);
    n = (n & 0x0000ffff) + ((n >>> 16) & 0x0000ffff);
    return n;
  }

  // 常规解法
  // 一个数的二进制有多少个1
  // 最右侧的1 （N&-N）
  public int hammingWeight2(int n) {
    int num = 0;
    while (n != 0) {
      int mostRightOne = (n) & ((~n) + 1);
      if ((mostRightOne & n) != 0) {
        num++;
      }
      n ^= mostRightOne;
    }
    return num;
  }
}
