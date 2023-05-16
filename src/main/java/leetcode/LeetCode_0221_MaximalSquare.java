/*
 * Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and
 * return its area.
 *
 * Example:
 *
 * Input:
 *
 * 1 0 1 0 0 1 0 1 1 1 1 1 1 1 1 1 0 0 1 0
 *
 * Output: 4
 */
package leetcode;

// 笔记：https://www.cnblogs.com/greyzeng/p/16976877.html
// tips 正方形必须以i,j作为右下角情况，哪个正方形内部都是1且最大
// 依赖 左边一个位置，上面一个位置，左上角位置
// https://leetcode.cn/problems/maximal-square/
public class LeetCode_0221_MaximalSquare {

    public int maximalSquare(char[][] matrix) {
        if (null == matrix || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int max = 0;
        // tips 正方形必须以i,j作为右下角情况，哪个正方形内部都是1且最大
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            dp[i][0] = matrix[i][0] == '1' ? 1 : 0;
            max = Math.max(dp[i][0], max);
        }
        for (int i = 0; i < n; i++) {
            dp[0][i] = matrix[0][i] == '1' ? 1 : 0;
            max = Math.max(dp[0][i], max);
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = matrix[i][j] == '1'
                        ? Math.min(Math.min(dp[i - 1][j - 1], dp[i - 1][j]), dp[i][j - 1]) + 1
                        : 0;
                max = Math.max(dp[i][j], max);
            }
        }
        return max * max;
    }
}
