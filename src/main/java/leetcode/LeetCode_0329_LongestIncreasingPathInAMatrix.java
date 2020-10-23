/*Given an integer matrix, find the length of the longest increasing path.

        From each cell, you can either move to four directions: left, right, up or down. You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).

        Example 1:

        Input: nums =
        [
        [9,9,4],
        [6,6,8],
        [2,1,1]
        ]
        Output: 4
        Explanation: The longest increasing path is [1, 2, 6, 9].
        Example 2:

        Input: nums =
        [
        [3,4,5],
        [3,2,6],
        [2,2,1]
        ]
        Output: 4
        Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.*/
package leetcode;


public class LeetCode_0329_LongestIncreasingPathInAMatrix {

    public static int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int M = matrix.length;
        int N = matrix[0].length;
        int[][] dp = new int[M][N];
        int ans = 0;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                ans = Math.max(ans, process(matrix, M, N, i, j, dp));
            }
        }
        return ans;
    }

    private static int process(int[][] matrix, int M, int N, int i, int j, int[][] dp) {
        if (dp[i][j] != 0) {
            return dp[i][j];
        }
        int next = 0;
        if (i > 0 && i <= M - 1 && j <= N - 1 && matrix[i][j] < matrix[i - 1][j]) {
            next = Math.max(next, process(matrix, M, N, i - 1, j, dp));
        }
        if (i < M - 1 && j <= N - 1 && matrix[i][j] < matrix[i + 1][j]) {
            next = Math.max(next, process(matrix, M, N, i + 1, j, dp));
        }
        if (i <= M - 1 && j > 0 && j <= N - 1 && matrix[i][j] < matrix[i][j - 1]) {
            next = Math.max(next, process(matrix, M, N, i, j - 1, dp));
        }
        if (i <= M - 1 && j < N - 1 && matrix[i][j] < matrix[i][j + 1]) {
            next = Math.max(next, process(matrix, M, N, i, j + 1, dp));
        }
        dp[i][j] = next + 1;
        return dp[i][j];
    }


}
