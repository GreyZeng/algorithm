package lintcode;

// 笔记：https://blog.csdn.net/hotonyhui/article/details/127656554
// 背包问题
// 在 n 个物品中挑选若干物品装入背包，最多能装多满？假设背包的大小为m，每个物品的大小为Ai (每个物品只能选择一次且物品大小均为正整数)
// https://www.lintcode.com/problem/92/
public class LintCode_0092_Backpack {
  // 暴力递归
  public static int backPack(int m, int[] arr) {
    if (arr == null || arr.length < 1) {
      return 0;
    }
    return p(m, 0, arr);
  }

  // 还剩rest容量，从i开始到最后，得到的最大容量是多少
  public static int p(int i, int j, int[] arr) {
    if (j == arr.length) {
      return 0;
    }
    int p1 = p(i, j + 1, arr);
    return i >= arr[j] ? Math.max(arr[j] + p(i - arr[j], j + 1, arr), p1) : p1;
  }

  // 动态规划，使用缓存
  public static int backPack2(int m, int[] arr) {
    if (arr == null || arr.length < 1) {
      return 0;
    }
    int[][] dp = new int[arr.length + 1][m + 1];
    for (int i = 0; i < dp.length; i++) {
      for (int j = 0; j < dp[0].length; j++) {
        dp[i][j] = -1;
      }
    }
    return p2(m, 0, arr, dp);
  }

  // 还剩rest容量,得到的最大容量是多少
  // 从i开始到最后，得到的最大容量是多少
  public static int p2(int rest, int i, int[] arr, int[][] dp) {
    if (dp[i][rest] != -1) {
      return dp[i][rest];
    }
    int ans = 0;
    if (i == arr.length) {
      dp[i][rest] = ans;
      return ans;
    }
    int p1 = p2(rest, i + 1, arr, dp);
    ans = rest >= arr[i] ? Math.max(arr[i] + p2(rest - arr[i], i + 1, arr, dp), p1) : p1;
    dp[i][rest] = ans;
    return ans;
  }

  // 动态规划
  public static int backPack3(int m, int[] arr) {
    if (arr == null || arr.length < 1) {
      return 0;
    }
    int[][] dp = new int[arr.length + 1][m + 1];
    for (int i = arr.length - 1; i >= 0; i--) {
      for (int j = 0; j < m + 1; j++) {
        int p1 = dp[i + 1][j];
        dp[i][j] = j >= arr[i] ? Math.max(arr[i] + dp[i + 1][j - arr[i]], p1) : p1;
      }
    }
    return dp[0][m];
  }

  // 动态规划+压缩数组优化
  public static int backPack4(int m, int[] arr) {
    if (arr == null || arr.length < 1) {
      return 0;
    }
    int[] dp = new int[m + 1];
    for (int i = arr.length - 1; i >= 0; i--) {
      for (int j = m; j >= 0; j--) {
        if (j >= arr[i]) {
          dp[j] = Math.max(dp[j - arr[i]] + arr[i], dp[j]);
        }
      }
    }
    return dp[m];
  }
}
