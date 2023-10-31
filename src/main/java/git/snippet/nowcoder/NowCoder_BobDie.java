package git.snippet.nowcoder;

import java.util.Scanner;

// 给定5个参数，n，m，i，j，k 表示在`n*m`的区域上，醉汉Bob初始在(i,j)位置 Bob一共要迈出 k 步，
// 且每步都会等概率向上下左右四个方向走一个单位,任何时候Bob只要离开 n*m 的区域，就直接死亡,返回k步之后，Bob还在 n*m 的区域的概率
// Bob总的走法有：4^k
// 笔记：https://www.cnblogs.com/greyzeng/p/16842365.html
public class NowCoder_BobDie {

  public static String livePossibility1(int i, int j, int k, int n, int m) {
    return buildExp(process(i, j, k, n, m), (long) Math.pow(4, k));
  }

  // 目前在i，j位置，还有k步要走，走完了如果还在棋盘中就获得1个生存点，返回总的生存点数
  public static long process(int i, int j, int k, int n, int m) {
    if (i < 0 || i == n || j < 0 || j == m) {
      return 0;
    }
    // 还在棋盘中！
    if (k == 0) {
      return 1;
    }
    // 还在棋盘中！还有步数要走
    long up = process(i - 1, j, k - 1, n, m);
    long down = process(i + 1, j, k - 1, n, m);
    long left = process(i, j - 1, k - 1, n, m);
    long right = process(i, j + 1, k - 1, n, m);
    return up + down + left + right;
  }

  public static String livePossibility2(int i, int j, int k, int n, int m) {
    long[][][] dp = new long[n][m][k + 1];
    for (int row = 0; row < n; row++) {
      for (int col = 0; col < m; col++) {
        dp[row][col][0] = 1;
      }
    }
    for (int rest = 1; rest <= k; rest++) {
      for (int r = 0; r < n; r++) {
        for (int c = 0; c < m; c++) {
          dp[r][c][rest] = pick(dp, n, m, r - 1, c, rest - 1);
          dp[r][c][rest] += pick(dp, n, m, r + 1, c, rest - 1);
          dp[r][c][rest] += pick(dp, n, m, r, c - 1, rest - 1);
          dp[r][c][rest] += pick(dp, n, m, r, c + 1, rest - 1);
        }
      }
    }
    return buildExp(dp[i][j][k], (long) Math.pow(4, k));
  }

  public static String buildExp(long m, long n) {
    return m / gcd(m, n) + "/" + n / gcd(m, n);
  }

  public static long gcd(long m, long n) {
    return n == 0 ? m : gcd(n, m % n);
  }

  public static long pick(long[][][] dp, int n, int m, int r, int c, int rest) {
    if (r < 0 || r == n || c < 0 || c == m) {
      return 0;
    }
    return dp[r][c][rest];
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int m = sc.nextInt();
    int i = sc.nextInt();
    int j = sc.nextInt();
    int k = sc.nextInt();
    System.out.println(livePossibility1(i, j, k, n, m));
    System.out.println(livePossibility2(i, j, k, n, m));
    sc.close();
  }
}
