package resolved.bit;

// [left, right]区间内所有数按位与后的结果
// https://leetcode.com/problems/bitwise-and-of-numbers-range/
public class LeetCode_0201_BitwiseANDOfNumbersRange {
  public int rangeBitwiseAnd(int left, int right) {
    while (right > left) {
      right -= (right & (-right));
    }
    return right;
  }
}
