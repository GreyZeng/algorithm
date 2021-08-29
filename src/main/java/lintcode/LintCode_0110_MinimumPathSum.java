package lintcode;
// 描述

// 给定一个只含非负整数的m*nm∗n网格，找到一条从左上角到右下角的可以使数字和最小的路径。

// 你在同一时间只能向下或者向右移动一步
// https://www.lintcode.com/problem/110/
public class LintCode_0110_MinimumPathSum {

    public int minPathSum(int[][] grid) {
        if (null == grid) {
            return 0;
        }
        return p(grid, 0, 0, grid.length - 1, grid[0].length - 1);
    }

    public int p(int[][] grid, int i, int j, int m, int n) {
        if (i == m) {
            // 已经到最后一行了，只能向右边走
            int sum = 0;
            for (int k = j; k <= n; k++) {
                sum += grid[m][k];
            }
            return sum;
        }
        if (j == n) {
            // 已经到最后一列了，只能向下走
            int sum = 0;
            for (int k = i; k <= m; k++) {
                sum += grid[k][n];
            }
            return sum;
        }

        int min = Integer.MAX_VALUE;
        if (i < m) {
            min = Math.min(min, p(grid, i + 1, j, m, n));
        }
        if (j < n) {
            min = Math.min(min, p(grid, i, j + 1, m, n));
        }
        return min + grid[i][j];
    }

    // 使用缓存
    public int minPathSum2(int[][] grid) {
        if (null == grid) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = Integer.MIN_VALUE;
            }
        }
        return p2(grid, 0, 0, m - 1, n - 1, dp);
    }

    public int p2(int[][] grid, int i, int j, int m, int n, int[][] dp) {
        if (dp[i][j] != Integer.MIN_VALUE) {
            return dp[i][j];
        }
        if (i == m) {
            // 已经到最后一行了，只能向右边走
            int sum = 0;
            for (int k = j; k <= n; k++) {
                sum += grid[m][k];
            }
            dp[m][j] = sum;
            return sum;
        }
        if (j == n) {
            // 已经到最后一列了，只能向下走
            int sum = 0;
            for (int k = i; k <= m; k++) {
                sum += grid[k][n];
            }
            dp[i][n] = sum;
            return sum;
        }

        int min = Integer.MAX_VALUE;
        if (i < m) {
            min = Math.min(min, p2(grid, i + 1, j, m, n, dp));
        }
        if (j < n) {
            min = Math.min(min, p2(grid, i, j + 1, m, n, dp));
        }
        int ans = min + grid[i][j];
        dp[i][j] = ans;
        return ans;
    }

    public int maxValue3(int[][] grid) {
        if (null == grid) {
            return 0;
        }
        int m = grid.length - 1;
        int n = grid[0].length - 1;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i < m + 1; i++) {
            for (int j = 0; j < n + 1; j++) {
                dp[i][j] = Integer.MIN_VALUE;
            }
        }
        dp[m][n] = grid[m][n];
        for (int j = n - 1; j >= 0; j--) {
            dp[m][j] = dp[m][j + 1] + grid[m][j];
        }
        for (int i = m - 1; i >= 0; i--) {
            dp[i][n] = dp[i + 1][n] + grid[i][n];
        }
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                dp[i][j] = grid[i][j] + Math.min(dp[i + 1][j], dp[i][j + 1]);
            }
        }
        return dp[0][0];
    }
    
    public int maxValue4(int[][] grid) {
        if (null == grid) {
            return 0;
        }
        int m = grid.length - 1;
        int n = grid[0].length - 1;
        int[] dp = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            dp[i] = Integer.MIN_VALUE;
        }
        dp[n] = grid[m][n];
        for (int j = n - 1; j >= 0; j--) {
            dp[j] = dp[j + 1] + grid[m][j];
        }
        int first = dp[n];
        for (int i = m - 1; i >= 0; i--) {
            dp[n] = first + grid[i][n];
            for (int j = n - 1; j >= 0; j--) {
                dp[j] = grid[i][j] + Math.min(dp[j], dp[j + 1]);
            }
            first = dp[n];
        }
        return dp[0];
    }
}
