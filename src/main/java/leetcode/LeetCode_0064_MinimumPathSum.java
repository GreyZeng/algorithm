/*Given a m x n grid filled with non-negative numbers,
find a path from top left to bottom right which minimizes the sum of all numbers along its path.

        Note: You can only move either down or right at any point in time.

        Example:

        Input:
        [
        [1,3,1],
        [1,5,1],
        [4,2,1]
        ]
        Output: 7
        Explanation: Because the path 1→3→1→1→1 minimizes the sum.*/
package leetcode;

// 可以利用空间压缩技巧（一维数组滚动更新）
public class LeetCode_0064_MinimumPathSum {

    public static int minPathSum(int[][] grid) {
        if (null == grid || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int M = grid.length;
        int N = grid[0].length;
        int[][] dp = new int[M][N]; // 到(i,j)点的最短距离->dp[i][j]

        dp[0][0] = grid[0][0];

        // 填第一行
        for (int i = 1; i < N; i++) {
            dp[0][i] = dp[0][i - 1] + grid[0][i];
        }
        // 填第一列
        for (int i = 1; i < M; i++) {
            dp[i][0] = dp[i][0] + dp[i - 1][0] + grid[i][0];
        }

        for (int i = 1; i < M; i++) {
            for (int j = 1; j < N; j++) {
                dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j];
            }
        }
        return dp[M - 1][N - 1];
    }

}
