/*
 * Given an integer, write a function to determine if it is a power of three.
 *
 * Example 1:
 *
 * Input: 27 Output: true Example 2:
 *
 * Input: 0 Output: false Example 3:
 *
 * Input: 9 Output: true Example 4:
 *
 * Input: 45 Output: false Follow up: Could you do it without using any loop / recursion?
 */
package bit;

// https://leetcode.cn/problems/power-of-three/submissions/
public class LeetCode_0326_PowerOfThree {
    /// 1162261467 = 3^19 整型范围内3的最大次幂值
    public boolean isPowerOfThree(int n) {
        return (n > 0 && 1162261467 % n == 0);
    }
}
