package leetcode.easy;

// You are climbing a stair case. It takes n steps to reach to the top.
//
// Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
//
// Example 1:
//
// Input: 2
// Output: 2
// Explanation: There are two ways to climb to the top.
// 1. 1 step + 1 step
// 2. 2 steps
// Example 2:
//
// Input: 3
// Output: 3
// Explanation: There are three ways to climb to the top.
// 1. 1 step + 1 step + 1 step
// 2. 1 step + 2 steps
// 3. 2 steps + 1 step
//
//
// Constraints:
//
// 1 <= n <= 45
// 斐波那契数列问题：https://www.cnblogs.com/greyzeng/p/15388178.html
public class LeetCode_0070_ClimbingStairs {

    // Fn = Fn-1 + Fn-2
    public static int climbStairs(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        if (n == 3) {
            return 3;
        }
        int[][] base = {{1, 1}, {1, 0}};
        int[][] res = matrixPow(base, n - 2);
        return 2 * res[0][0] + 1 * res[1][0];
    }

    public static int[][] matrixPow(int[][] t, int n) {
        int[][] matrix1 = new int[t.length][t[0].length];
        for (int i = 0; i < matrix1.length; i++) {
            matrix1[i][i] = 1;
        }
        int[][] tmp = t;// 矩阵1次方
        for (; n != 0; n >>= 1) {
            if ((n & 1) != 0) {
                matrix1 = muliMatrix(matrix1, tmp);
            }
            tmp = muliMatrix(tmp, tmp);
        }
        return matrix1;
    }

    public static int[][] muliMatrix(int[][] m1, int[][] m2) {
        int[][] res = new int[m1.length][m2[0].length];
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m2[0].length; j++) {
                for (int k = 0; k < m2.length; k++) {
                    res[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }
        return res;
    }
}
