package 算法.动态规划;

// 描述
// 给定一个只含非负整数的m*nm∗n网格，找到一条从左上角到右下角的可以使数字和最小的路径。
// 你在同一时间只能向下或者向右移动一步
// https://www.lintcode.com/problem/110/
// 笔记：https://www.cnblogs.com/greyzeng/p/16712777.html
public class LintCode_0110_MinimumPathSum {
    // 暴力解，超时
    public static int minPathSum(int[][] grid) {
        if (grid == null || grid.length < 1 || grid[0].length < 1) {
            return 0;
        }
        return process(grid, 0, 0);
    }

    // 从i，j开始，一直到最后，最小路径和是多少
    public static int process(int[][] grid, int i, int j) {
        // 到最后一行了，只能向右走
        if (i == grid.length - 1) {
            int sum = 0;
            for (int m = j; m < grid[0].length; m++) {
                sum += grid[i][m];
            }
            return sum;
        }
        // 到最后一列了，只能向下走
        if (j == grid[0].length - 1) {
            int sum = 0;
            for (int m = i; m < grid.length; m++) {
                sum += grid[m][j];
            }
            return sum;
        }
        // 普遍位置
        int p1 = grid[i][j], p2 = grid[i][j];
        if (i + 1 < grid.length) {
            p1 += process(grid, i + 1, j);
        }
        if (j + 1 < grid[0].length) {
            p2 += process(grid, i, j + 1);
        }
        return Math.min(p1, p2);
    }

    public static int minPathSum2(int[][] grid) {
        if (grid == null || grid.length < 1 || grid[0].length < 1) {
            return 0;
        }
        int[][] dp = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        return process(grid, 0, 0, dp);
    }

    // 使用缓存
    public static int process(int[][] grid, int i, int j, int[][] dp) {
        if (dp[i][j] != Integer.MAX_VALUE) {
            return dp[i][j];
        }
        // 到最后一行了，只能向右走
        if (i == grid.length - 1) {
            int sum = 0;
            for (int m = j; m < grid[0].length; m++) {
                sum += grid[i][m];
            }
            dp[i][j] = sum;
            return sum;
        }
        // 到最后一列了，只能向下走
        if (j == grid[0].length - 1) {
            int sum = 0;
            for (int m = i; m < grid.length; m++) {
                sum += grid[m][j];
            }
            dp[i][j] = sum;
            return sum;
        }
        // 普遍位置
        int p1 = grid[i][j], p2 = grid[i][j];
        if (i + 1 < grid.length) {
            p1 += process(grid, i + 1, j, dp);
        }
        if (j + 1 < grid[0].length) {
            p2 += process(grid, i, j + 1, dp);
        }
        dp[i][j] = Math.min(p1, p2);
        return dp[i][j];
    }

    // 动态规划
    public static int minPathSum3(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[m - 1][n - 1] = grid[m - 1][n - 1];
        for (int i = m - 2; i >= 0; i--) {
            dp[i][n - 1] = grid[i][n - 1] + dp[i + 1][n - 1];
        }
        for (int i = n - 2; i >= 0; i--) {
            dp[m - 1][i] = grid[m - 1][i] + dp[m - 1][i + 1];
        }
        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                dp[i][j] = grid[i][j] + Math.min(dp[i + 1][j], +dp[i][j + 1]);
            }
        }
        // 普遍位置
        return dp[0][0];
    }

    // 压缩数组技巧
    public static int minPathSum4(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        //
        int[] dp = new int[n];
        // 最右下角位置
        dp[n - 1] = grid[m - 1][n - 1];
        // 填最后一行
        for (int i = n - 2; i >= 0; i--) {
            dp[i] = dp[i + 1] + grid[m - 1][i];
        }
        int first = dp[n - 1];
        for (int i = m - 2; i >= 0; i--) {
            dp[n - 1] = first + grid[i][n - 1];
            for (int j = n - 2; j >= 0; j--) {
                dp[j] = grid[i][j] + Math.min(dp[j], dp[j + 1]);
            }
            first = dp[n - 1];
        }
        // 普遍位置
        return dp[0];
    }
}
