/*
 * 给定一个二维数组 map，含义是一张地图，例如，如下矩阵: -2 -3 3 -5 -10 1 0 30 -5 游戏的规则如下: 骑士从左上角出发，每次只能向右或向下走，最后到达右下角见到公主。
 * 地图中每个位置的值代表骑士要遭遇的事情。 如果是负数，说明此处有怪兽，要让骑士损失血量。 如果是非负数，代表此处有血瓶，能让骑士回血。
 * 骑士从左上角到右下角的过程中，走到任何一个位置时，血量都不能少于1。 为了保证骑士能见到公主，初始血量至少是多少?根据map，返回至少的初始血量。
 */
package leetcode;

public class LeetCode_0174_DungeonGame {

    public static int calculateMinimumHP(int[][] dungeon) {
        return p(dungeon, 0, 0);
    }

    public static int p(int[][] dungeon, int i, int j) {
        if (i == dungeon.length - 1 && j == dungeon[0].length - 1) {
            return dungeon[i][j] < 0 ? -dungeon[i][j] + 1 : 1;
        }
        if (i == dungeon.length - 1) {
            // 只能向右走
            int rightNeed = p(dungeon, i, j + 1);
            if (dungeon[i][j] < 0) {
                return rightNeed + Math.abs(dungeon[i][j]);
            } else if (dungeon[i][j] >= rightNeed) {
                return 1;
            } else {
                return rightNeed - dungeon[i][j];
            }
        }
        if (j == dungeon[0].length - 1) {
            // 只能向下走
            int downNeed = p(dungeon, i + 1, j);
            if (dungeon[i][j] < 0) {
                return downNeed + Math.abs(dungeon[i][j]);
            } else if (dungeon[i][j] >= downNeed) {
                return 1;
            } else {
                return downNeed - dungeon[i][j];
            }
        }
        // 可以向下走，也能向右走
        int rightNeed = p(dungeon, i, j + 1);
        int downNeed = p(dungeon, i + 1, j);
        int minNeed = Math.min(rightNeed, downNeed);
        if (dungeon[i][j] < 0) {
            return minNeed + Math.abs(dungeon[i][j]);
        } else if (dungeon[i][j] >= minNeed) {
            return 1;
        } else {
            return minNeed - dungeon[i][j];
        }
    }

    public static int calculateMinimumHP2(int[][] dungeon) {
        if (null == dungeon || dungeon.length == 0 || null == dungeon[0] || 0 == dungeon[0].length) {
            return 1;
        }
        int m = dungeon.length;
        int n = dungeon[0].length;
        int[][] dp = new int[m][n];
        dp[m - 1][n - 1] = dungeon[m - 1][n - 1] < 0 ? -dungeon[m - 1][n - 1] + 1 : 1;

        for (int i = dungeon[0].length - 2; i >= 0; i--) {
            int rightNeed = dp[dungeon.length - 1][i + 1];
            if (dungeon[dungeon.length - 1][i] < 0) {
                dp[dungeon.length - 1][i] = rightNeed + Math.abs(dungeon[dungeon.length - 1][i]);
            } else if (dungeon[dungeon.length - 1][i] >= rightNeed) {
                dp[dungeon.length - 1][i] = 1;
            } else {
                dp[dungeon.length - 1][i] = rightNeed - dungeon[dungeon.length - 1][i];
            }
        }

        for (int i = dungeon.length - 2; i >= 0; i--) {
            // 只能向下走
            int downNeed = dp[i + 1][dungeon[0].length - 1];
            if (dungeon[i][dungeon[0].length - 1] < 0) {
                dp[i][dungeon[0].length - 1] = downNeed + Math.abs(dungeon[i][dungeon[0].length - 1]);
            } else if (dungeon[i][dungeon[0].length - 1] >= downNeed) {
                dp[i][dungeon[0].length - 1] = 1;
            } else {
                dp[i][dungeon[0].length - 1] = downNeed - dungeon[i][dungeon[0].length - 1];
            }
        }
        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                int rightNeed = dp[i][j + 1];
                int downNeed = dp[i + 1][j];
                int minNeed = Math.min(rightNeed, downNeed);
                if (dungeon[i][j] < 0) {
                    dp[i][j] = minNeed + Math.abs(dungeon[i][j]);
                } else if (dungeon[i][j] >= minNeed) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = minNeed - dungeon[i][j];
                }
            }
        }
        return dp[0][0];
    }


    public static int calculateMinimumHP3(int[][] dungeon) {
        // TODO
        return -1;
    }
}
