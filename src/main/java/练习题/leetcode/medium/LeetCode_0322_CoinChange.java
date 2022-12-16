package 练习题.leetcode.medium;

// https://leetcode.cn/problems/coin-change/
// 笔记：https://www.cnblogs.com/greyzeng/p/16770202.html
// 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
// 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回-1 。
// 你可以认为每种硬币的数量是无限的。
public class LeetCode_0322_CoinChange {

  // 暴力递归
  public static int coinChange1(int[] coins, int amount) {
    if (coins == null || coins.length == 0) {
      return -1;
    }
    return p(coins, 0, amount);
  }

  // 从i...往后自由选择，凑成rest的最少的硬币个数
  public static int p(int[] coins, int i, int rest) {
    if (rest < 0) {
      return -1;
    }
    if (rest == 0) {
      return 0;
    }
    // rest不为空
    if (i == coins.length) {
      // i 已经走到尽头
      return -1;
    }
    // 既没有到最后，也还有剩余
    int min = Integer.MAX_VALUE;
    int num = 0;
    while (num * coins[i] <= rest) {
      int after = p(coins, i + 1, rest - num * coins[i]);
      if (after != -1) {
        min = Math.min(num + after, min);
      }
      num++;
    }
    return min == Integer.MAX_VALUE ? -1 : min;
  }

  // 动态规划
  public static int coinChange2(int[] coins, int amount) {
    if (coins == null || coins.length == 0) {
      return -1;
    }
    int n = coins.length;
    int[][] dp = new int[n + 1][amount + 1];
    for (int i = 1; i < amount + 1; i++) {
      dp[n][i] = -1;
    }
    for (int i = n - 1; i >= 0; i--) {
      for (int j = 1; j < amount + 1; j++) {
        int min = Integer.MAX_VALUE;
        int num = 0;
        // 枚举行为，可以继续优化
        while (num * coins[i] <= j) {
          int after = dp[i + 1][j - num * coins[i]];
          if (after != -1) {
            min = Math.min(num + after, min);
          }
          num++;
        }
        dp[i][j] = (min == Integer.MAX_VALUE ? -1 : min);
      }
    }
    return dp[0][amount];
  }

  // 优化枚举行为
  public static int coinChange3(int[] coins, int amount) {
    if (coins == null || coins.length == 0) {
      return -1;
    }
    int n = coins.length;
    int[][] dp = new int[n + 1][amount + 1];
    for (int i = 1; i < amount + 1; i++) {
      dp[n][i] = -1;
    }
    for (int i = n - 1; i >= 0; i--) {
      for (int j = 1; j < amount + 1; j++) {
        dp[i][j] = dp[i + 1][j];
        if (j - coins[i] >= 0 && dp[i][j - coins[i]] != -1) {
          if (dp[i][j] == -1) {
            dp[i][j] = dp[i][j - coins[i]] + 1;
          } else {
            dp[i][j] = Math.min(dp[i][j - coins[i]] + 1, dp[i][j]);
          }
        }
      }
    }
    return dp[0][amount];
  }

}
