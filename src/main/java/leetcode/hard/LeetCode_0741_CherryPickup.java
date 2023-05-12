package leetcode.hard;

// 最大路径和
// https://leetcode.cn/problems/cherry-pickup
// 给定一个矩阵matrix，先从左上角开始，每一步只能往右或者往下走，走到右下角。
// 然后从右下角出发，每一步只能往上或者往左走，再回到左上角。任何一个位置的数字，只能获得一遍。返回最大路径和。
// tips:
// 三维dp
// 设置两个小人，走到同一位置的时候，只算一份，路径和，就是来回的最大路径和
// 笔记：https://www.cnblogs.com/greyzeng/p/16415455.html
public class LeetCode_0741_CherryPickup {
    // 动态规划
    // 三维表
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

    // 定义两个小人，两个人都从(0,0)位置出发，到右下角位置，每人同时选择不同的下一步，如果两个小人跳到了同一个位置，只计算一份。
    public static int p(int[][] matrix, int m, int n, int x1, int y1, int x2, int[][][] dp) {
        if (dp[x1][y1][x2] != Integer.MIN_VALUE) {
            return dp[x1][y1][x2];
        }
        if (x1 == m - 1 && y1 == n - 1) {
            // 已经到了最右下角了
            // 隐含条件：另外一个点也一定到达右下角
            // 获得一份樱桃数
            dp[x1][y1][x2] = matrix[x1][y1];
            return matrix[x1][y1];
        }

        int next = -1;
        // 下，下
        if (x1 + 1 < m && x2 + 1 < m && matrix[x1 + 1][y1] != -1
                && matrix[x2 + 1][getY2(x1, y1, x2)] != -1) {
            next = Math.max(p(matrix, m, n, x1 + 1, y1, x2 + 1, dp), next);
        }
        // 下，右
        if (x1 + 1 < m && getY2(x1, y1, x2) + 1 < n && matrix[x1 + 1][y1] != -1
                && matrix[x2][getY2(x1, y1, x2) + 1] != -1) {
            next = Math.max(p(matrix, m, n, x1 + 1, y1, x2, dp), next);
        }
        // 右，下
        if (y1 + 1 < n && x2 + 1 < m && matrix[x1][y1 + 1] != -1
                && matrix[x2 + 1][getY2(x1, y1, x2)] != -1) {
            next = Math.max(p(matrix, m, n, x1, y1 + 1, x2 + 1, dp), next);
        }
        // 右，右
        if (y1 + 1 < n && getY2(x1, y1, x2) + 1 < m && matrix[x1][y1 + 1] != -1
                && matrix[x2][getY2(x1, y1, x2) + 1] != -1) {
            next = Math.max(p(matrix, m, n, x1, y1 + 1, x2, dp), next);
        }
        if (next == -1) {
            dp[x1][y1][x2] = -1;
            return -1;
        }
        if (x1 == x2) {
            // 到达同一个位置，只取一个值
            dp[x1][y1][x2] = next + matrix[x1][y1];
            return dp[x1][y1][x2];
        }
        dp[x1][y1][x2] = next + matrix[x1][y1] + matrix[x2][getY2(x1, y1, x2)];
        return dp[x1][y1][x2];
    }

    public static int getY2(int x1, int y1, int x2) {
        return x1 + y1 - x2;
    }

    // 暴力解法 leetcode 超时
    // 四个参数
    public static int cherryPickup1(int[][] matrix) {
        return Math.max(0, p(matrix, matrix.length, matrix[0].length, 0, 0, 0, 0));
    }

    // 定义两个小人，两个人都从(0,0)位置出发，到右下角位置，每人同时选择不同的下一步，如果两个小人跳到了同一个位置，只计算一份。
    public static int p(int[][] matrix, int m, int n, int x1, int y1, int x2, int y2) {
        if (x1 == m - 1 && y1 == n - 1) {
            // 已经到了最右下角了
            // 隐含条件：另外一个点也一定到达右下角
            // 获得一份樱桃数
            return matrix[x1][y1];
        }

        int next = -1;
        // 下，下
        if (x1 + 1 < m && x2 + 1 < m && matrix[x1 + 1][y1] != -1 && matrix[x2 + 1][y2] != -1) {
            next = Math.max(p(matrix, m, n, x1 + 1, y1, x2 + 1, y2), next);
        }
        // 下，右
        if (x1 + 1 < m && y2 + 1 < n && matrix[x1 + 1][y1] != -1 && matrix[x2][y2 + 1] != -1) {
            next = Math.max(p(matrix, m, n, x1 + 1, y1, x2, y2 + 1), next);
        }
        // 右，下
        if (y1 + 1 < n && x2 + 1 < m && matrix[x1][y1 + 1] != -1 && matrix[x2 + 1][y2] != -1) {
            next = Math.max(p(matrix, m, n, x1, y1 + 1, x2 + 1, y2), next);
        }
        // 右，右
        if (y1 + 1 < n && y2 + 1 < m && matrix[x1][y1 + 1] != -1 && matrix[x2][y2 + 1] != -1) {
            next = Math.max(p(matrix, m, n, x1, y1 + 1, x2, y2 + 1), next);
        }
        if (next == -1) {
            return -1;
        }
        if (x1 == x2) {
            // 到达同一个位置，只取一个值
            return next + matrix[x1][y1];
        }
        return next + matrix[x1][y1] + matrix[x2][y2];
    }

    // 暴力解法 leetcode 超时
    // 三个参数
    public static int cherryPickup2(int[][] matrix) {
        return Math.max(0, p(matrix, matrix.length, matrix[0].length, 0, 0, 0));
    }

    // 定义两个小人，两个人都从(0,0)位置出发，到右下角位置，每人同时选择不同的下一步，如果两个小人跳到了同一个位置，只计算一份。
    public static int p(int[][] matrix, int m, int n, int x1, int y1, int x2) {
        if (x1 == m - 1 && y1 == n - 1) {
            // 已经到了最右下角了
            // 隐含条件：另外一个点也一定到达右下角
            // 获得一份樱桃数
            return matrix[x1][y1];
        }

        int next = -1;
        // 下，下
        if (x1 + 1 < m && x2 + 1 < m && matrix[x1 + 1][y1] != -1
                && matrix[x2 + 1][getY2(x1, y1, x2)] != -1) {
            next = Math.max(p(matrix, m, n, x1 + 1, y1, x2 + 1), next);
        }
        // 下，右
        if (x1 + 1 < m && getY2(x1, y1, x2) + 1 < n && matrix[x1 + 1][y1] != -1
                && matrix[x2][getY2(x1, y1, x2) + 1] != -1) {
            next = Math.max(p(matrix, m, n, x1 + 1, y1, x2), next);
        }
        // 右，下
        if (y1 + 1 < n && x2 + 1 < m && matrix[x1][y1 + 1] != -1
                && matrix[x2 + 1][getY2(x1, y1, x2)] != -1) {
            next = Math.max(p(matrix, m, n, x1, y1 + 1, x2 + 1), next);
        }
        // 右，右
        if (y1 + 1 < n && getY2(x1, y1, x2) + 1 < m && matrix[x1][y1 + 1] != -1
                && matrix[x2][getY2(x1, y1, x2) + 1] != -1) {
            next = Math.max(p(matrix, m, n, x1, y1 + 1, x2), next);
        }
        if (next == -1) {
            return -1;
        }
        if (x1 == x2) {
            // 到达同一个位置，只取一个值
            return next + matrix[x1][y1];
        }
        return next + matrix[x1][y1] + matrix[x2][getY2(x1, y1, x2)];
    }
}
