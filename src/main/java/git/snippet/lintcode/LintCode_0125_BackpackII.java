package git.snippet.lintcode;

// 笔记：https://blog.csdn.net/hotonyhui/article/details/127656554
// 背包问题
// 描述
// 有 n 个物品和一个大小为 m 的背包. 给定数组 A 表示每个物品的大小和数组 V 表示每个物品的价值.
//
// 问最多能装入背包的总价值是多大?
//
// A[i], V[i], n, m 均为整数
// 你不能将物品进行切分
// 你所挑选的要装入背包的物品的总大小不能超过 m
// 每个物品只能取一次
// m <= 1000m<=1000\
// len(A),len(V)<=100len(A),len(V)<=100
//
// 样例
// 样例 1：
//
// 输入：
//
// m = 10
// A = [2, 3, 5, 7]
// V = [1, 5, 2, 4]
// 输出：
//
// 9
// 解释：
//
// 装入 A[1] 和 A[3] 可以得到最大价值, V[1] + V[3] = 9
//
// 样例 2：
//
// 输入：
//
// m = 10
// A = [2, 3, 8]
// V = [2, 5, 8]
// 输出：
//
// 10
// 解释：
//
// 装入 A[0] 和 A[2] 可以得到最大价值, V[0] + V[2] = 10
//
// 挑战
// O(nm) 空间复杂度可以通过, 你能把空间复杂度优化为O(m)吗？
// https://www.lintcode.com/problem/125/
public class LintCode_0125_BackpackII {
  // w[]表示重量
  // v[]表示价值
  // 暴力递归
  public static int backPackII(int m, int[] w, int[] v) {
    if (m <= 0 || w == null || w.length < 1 || v == null || v.length < 1) {
      return 0;
    }
    return process(0, m, w, v);
  }

  // i号及其往后所有的物品在重量允许范围内的最大价值是多少
  public static int process(int i, int m, int[] w, int[] v) {
    if (i == w.length) {
      return 0;
    }
    // 不选i号商品
    int p1 = process(i + 1, m, w, v);
    if (m >= w[i]) {
      // 这种情况下，才有资格选i号商品
      return Math.max(p1, v[i] + process(i + 1, m - w[i], w, v));
    }
    return p1;
  }

  // 缓存法
  public static int backPackII2(int m, int[] w, int[] v) {
    if (m <= 0 || w == null || w.length < 1 || v == null || v.length < 1) {
      return 0;
    }
    int[][] dp = new int[w.length + 1][m + 1];
    for (int i = 0; i < dp.length; i++) {
      for (int j = 0; j < dp[0].length; j++) {
        dp[i][j] = -1;
      }
    }
    return process2(0, m, w, v, dp);
  }

  public static int process2(int i, int m, int[] w, int[] v, int[][] dp) {
    if (dp[i][m] != -1) {
      return dp[i][m];
    }
    if (i == w.length) {
      dp[i][m] = 0;
      return 0;
    }
    // 最后一行都是0
    // 从最后一行开始
    int ans = process2(i + 1, m, w, v, dp);
    if (i < w.length && m >= w[i]) {
      // 这种情况下，才有资格选i号商品
      ans = Math.max(ans, v[i] + process2(i + 1, m - w[i], w, v, dp));
    }
    dp[i][m] = ans;
    return ans;
  }

  // 动态规划解
  public static int backPackII3(int m, int[] w, int[] v) {
    if (m <= 0 || w == null || w.length < 1 || v == null || v.length < 1) {
      return 0;
    }
    int[][] dp = new int[w.length + 1][m + 1];
    // 倒数第一行都是0
    // 从倒数第二行开始填
    for (int i = w.length - 1; i >= 0; i--) {
      for (int j = m; j >= 0; j--) {
        dp[i][j] = dp[i + 1][j];
        if (j >= w[i]) {
          dp[i][j] = Math.max(dp[i][j], v[i] + dp[i + 1][j - w[i]]);
        }
        if (j == m && i == 0) {
          break;
        }
      }
    }
    return dp[0][m];
  }

  public static int backPackII4(int m, int[] w, int[] v) {
    if (m <= 0 || w == null || w.length < 1 || v == null || v.length < 1) {
      return 0;
    }
    int[] dp = new int[m + 1];
    for (int i = w.length - 1; i >= 0; i--) {
      for (int j = m; j >= 0; j--) {
        if (j >= w[i]) {
          dp[j] = Math.max(dp[j], v[i] + dp[j - w[i]]);
        }
        if (i == 0) {
          break;
        }
      }
    }
    return dp[m];
  }
}
