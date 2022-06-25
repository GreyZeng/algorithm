/*You are given an n x n grid representing a field of cherries, each cell is one of three possible integers.

        0 means the cell is empty, so you can pass through,
        1 means the cell contains a cherry that you can pick up and pass through, or
        -1 means the cell contains a thorn that blocks your way.
        Return the maximum number of cherries you can collect by following the rules below:

        Starting at the position (0, 0) and reaching (n - 1, n - 1) by moving right or down through valid path cells (cells with value 0 or 1).
        After reaching (n - 1, n - 1), returning to (0, 0) by moving left or up through valid path cells.
        When passing through a path cell containing a cherry, you pick it up, and the cell becomes an empty cell 0.
        If there is no valid path between (0, 0) and (n - 1, n - 1), then no cherries can be collected.*/

package leetcode.hard;

public class LeetCode_0741_CherryPickup {
    public static int cherryPickup(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][][] dp = new int[m][n][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < m; k++) {
                    dp[i][j][k] = Integer.MIN_VALUE;
                }
            }
        }
        return Math.max(0, p(matrix, m, n, 0, 0, 0, dp));
    }

    // 第一个点：(x1,y1), 第二个点(x2,y2)
    // 重要限制：来到同一个位置时，只获得一份
    public static int p(int[][] matrix, int m, int n, int x1, int y1, int x2, int[][][] dp) {
        if (dp[x1][y1][x2] != Integer.MIN_VALUE) {
            return dp[x1][y1][x2];
        }
        if (x1 == m - 1 && y1 == n - 1) {
            dp[m - 1][n - 1][x2] = matrix[x1][y1];
            return dp[m - 1][n - 1][x2];
        }
        int y2 = getY2(m, n, x1, y1, x2);
        int next = -1;
        // 下右
        if (x1 < m - 1 && y2 < n - 1 && matrix[x1 + 1][y1] != -1 && matrix[x2][y2 + 1] != -1) {
            next = Math.max(next, p(matrix, m, n, x1 + 1, y1, x2, dp));
        }
        // 右下
        if (y1 < n - 1 && x2 < m - 1 && matrix[x1][y1 + 1] != -1 && matrix[x2 + 1][y2] != -1) {
            next = Math.max(next, p(matrix, m, n, x1, y1 + 1, x2 + 1, dp));
        }
        // 右右
        if (y1 < n - 1 && y2 < n - 1 && matrix[x1][y1 + 1] != -1 && matrix[x2][y2 + 1] != -1) {
            next = Math.max(next, p(matrix, m, n, x1, y1 + 1, x2, dp));
        }
        // 下下
        if (x1 < m - 1 && x2 < m - 1 && matrix[x1 + 1][y1] != -1 && matrix[x2 + 1][y2] != -1) {
            next = Math.max(next, p(matrix, m, n, x1 + 1, y1, x2 + 1, dp));
        }
        if (matrix[x1][y1] == -1 || matrix[x2][y2] == -1 || next == -1) {
            dp[x1][y1][x2] = -1;
            return dp[x1][y1][x2];
        }
        if (x1 == x2) {
            dp[x1][y1][x2] = matrix[x1][y1] + next;
            return dp[x1][y1][x2];
        }
        dp[x1][y1][x2] = matrix[x1][y1] + matrix[x2][y2] + next;
        return dp[x1][y1][x2];
    }

    private static int getY2(int m, int n, int x1, int y1, int x2) {
        return x1 + y1 - x2;
    }
}
