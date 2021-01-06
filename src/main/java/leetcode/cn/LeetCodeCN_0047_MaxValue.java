/*在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。你可以从棋盘的左上角开始拿格子里的礼物，
并每次向右或者向下移动一格、直到到达棋盘的右下角。给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？
        示例 1:

        输入:
        [
         [1,3,1],
         [1,5,1],
         [4,2,1]
        ]
        输出: 12
        解释: 路径 1→3→5→2→1 可以拿到最多价值的礼物
        提示：
        0 < grid.length <= 200
        0 < grid[0].length <= 200
        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/li-wu-de-zui-da-jie-zhi-lcof
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。*/
package leetcode.cn;


public class LeetCodeCN_0047_MaxValue {
    // 暴力递归
    public static int maxValue(int[][] grid) {
        if (null == grid) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        return p(grid, m, n, 0, 0);
    }

    // 从i位置开始只能向右边和下边走直到右下角能拿到的礼物最大值是多少
    public static int p(int[][] grid, int m, int n, int row, int col) {
        if (row == m - 1) {
            int sum = 0;
            for (int i = col; i < n; i++) {
                sum += grid[m - 1][i];
            }
            return sum;
        }
        if (col == n - 1) {
            int sum = 0;
            for (int i = row; i < m; i++) {
                sum += grid[i][n - 1];
            }
            return sum;
        }
        int max = 0;
        if (row + 1 < m) {
            int downMax = p(grid, m, n, row + 1, col);
            max = Math.max(max, downMax);
        }
        if (col + 1 < n) {
            int rightMax = p(grid, m, n, row, col + 1);
            max = Math.max(max, rightMax);
        }
        return max + grid[row][col];
    }

    public static int maxValue2(int[][] grid) {
        if (null == grid) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        return p(grid, m, n, 0, 0, dp);
    }

    // 从i位置开始只能向右边和下边走直到右下角能拿到的礼物最大值是多少
    public static int p(int[][] grid, int m, int n, int row, int col, int[][] dp) {
        if (dp[row][col] != 0) {
            return dp[row][col];
        }
        if (row == m - 1) {
            int sum = 0;
            for (int i = col; i < n; i++) {
                sum += grid[m - 1][i];
            }
            dp[row][col] = sum;
            return sum;
        }
        if (col == n - 1) {
            int sum = 0;
            for (int i = row; i < m; i++) {
                sum += grid[i][n - 1];
            }
            dp[row][col] = sum;
            return sum;
        }
        int max = 0;
        if (row + 1 < m) {
            int downMax = p(grid, m, n, row + 1, col, dp);
            max = Math.max(max, downMax);
        }
        if (col + 1 < n) {
            int rightMax = p(grid, m, n, row, col + 1, dp);
            max = Math.max(max, rightMax);
        }
        dp[row][col] = max + grid[row][col];
        return dp[row][col];
    }


    // 动态规划
    public static int maxValue3(int[][] grid) {
        if (null == grid) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[m - 1][n - 1] = grid[m - 1][n - 1];
        for (int i = n - 2; i >= 0; i--) {
            dp[m - 1][i] = dp[m - 1][i + 1] + grid[m - 1][i];
        }
        for (int i = m - 2; i >= 0; i--) {
            dp[i][n - 1] = dp[i + 1][n - 1] + grid[i][n - 1];
        }
        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                dp[i][j] = Math.max(dp[i + 1][j], dp[i][j + 1]) + grid[i][j];
            }
        }
        return dp[0][0];
    }

    // 压缩数组
    public static int maxValue4(int[][] grid) {
        if (null == grid) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int[] dp = new int[n];
        dp[n - 1] = grid[m - 1][n - 1];
        for (int i = n - 2; i >= 0; i--) {
            dp[i] = dp[i + 1] + grid[m - 1][i];
        }
        int tmp = dp[n - 1];
        for (int i = m - 2; i >= 0; i--) {
            dp[n - 1] = tmp + grid[i][n - 1];
            for (int j = n - 2; j >= 0; j--) {
                dp[j] = Math.max(dp[j], dp[j + 1]) + grid[i][j];
            }
            tmp = dp[n - 1];
        }
        return dp[0];
    }

    public static void main(String[] args) {
        int[][] m = {{1, 2}, {1, 1}};
        System.out.println(maxValue(m));
        System.out.println(maxValue2(m));
        System.out.println(maxValue3(m));
        System.out.println(maxValue4(m));
    }
}
