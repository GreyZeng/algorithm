package git.snippet.leetcode;

// Given a non-empty array of digits representing a non-negative integer, increment one to the
// integer.
//
// The digits are stored such that the most significant digit is at the head of the list, and each
// element in the array contains a single digit.
//
// You may assume the integer does not contain any leading zero, except the number 0 itself.
//
//
//
// Example 1:
//
// Input: digits = [1,2,3]
// Output: [1,2,4]
// Explanation: The array represents the integer 123.
// Example 2:
//
// Input: digits = [4,3,2,1]
// Output: [4,3,2,2]
// Explanation: The array represents the integer 4321.
// Example 3:
//
// Input: digits = [0]
// Output: [1]
//
//
// Constraints:
//
// 1 <= digits.length <= 100
// 0 <= digits[i] <= 9
@Deprecated
public class LeetCode_0066_PlusOne {

  public static int[] plusOne(int[] digits) {
    if (null == digits) {
      return null;
    }
    int N = digits.length;
    int p = 1;
    int M = N;
    for (int i = N - 1; i >= 0; i--) {
      if (digits[i] + p != 10) {
        digits[i] += p;
        p = 0;
      } else {
        if (i == 0) {
          M = N + 1;
        }
        p = 1;
        digits[i] = 0;
      }
    }
    if (M == N) {
      return digits;
    } else {
      int[] res = new int[M];
      res[0] = 1;
      System.arraycopy(digits, 0, res, 1, M - 1);
      return res;
    }
  }

  public static void main(String[] args) {
    int[] digits = {9, 9, 9, 9};
    int[] res = plusOne(digits);
    for (int re : res) {
      System.out.print(re + " ");
    }
  }
}
