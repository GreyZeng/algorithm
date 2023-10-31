/*
 * 给定一个正数1，裂开的方法有一种，(1) 给定一个正数2，裂开的方法有两种，(1和1)、(2) 给定一个正数3，裂开的方法有三种，(1、1、1)、(1、2)、(3) 给定一个正数4，
 * 裂开的方法有五种，(1、1、1、1)、(1、1、2)、(1、3)、(2、2)、 (4) 给定一个正数n，求裂开的方法数。 动态规划优化状态依赖的技巧 f(int pre, int rest)
 *
 * 可以优化枚举行为（前面有：分硬币方法，股票问题3）
 */
package git.snippet.snippet;

public class Code_0044_SplitNum {
  public static int splitNum(int n) {
    return process(1, n);
  }

  public static int process(int pre, int rest) {
    if (rest == 0) {
      return 1;
    }
    if (pre > rest) {
      return 0;
    }
    int ways = 0;
    for (int i = pre; i <= rest; i++) {
      ways += process(i, rest - i);
    }
    return ways;
  }

  public static int splitNum2(int n) {
    int[][] dp = new int[n + 1][n + 1];
    for (int i = 1; i < n + 1; i++) {
      dp[i][0] = 1;
      dp[i][i] = 1;
    }
    for (int i = 2; i < n + 1; i++) {
      int c = i;
      int r = 1;
      while (c < n + 1) {
        int ways = 0;
        for (int s = r; s <= c; s++) {
          ways += dp[s][c - s];
        }
        dp[r][c] = ways;
        r++;
        c++;
      }
    }
    return dp[1][n];
  }

  public static int splitNum3(int n) {
    int[][] dp = new int[n + 1][n + 1];
    for (int i = 1; i < n + 1; i++) {
      dp[i][0] = 1;
      dp[i][i] = 1;
    }
    for (int i = 2; i < n + 1; i++) {
      int c = i;
      int r = 1;
      while (c < n + 1) {
        dp[r][c] = dp[r + 1][c] + ((c - r >= 0) ? dp[r][c - r] : 0);
        r++;
        c++;
      }
    }
    return dp[1][n];
  }

  public static void main(String[] args) {
    int value = 100;
    System.out.println("begin");
    for (int i = 1; i < value; i++) {
      int ans1 = splitNum(i);
      int ans2 = splitNum2(i);
      int ans3 = splitNum3(i);
      if (ans1 != ans2 || ans2 != ans3) {
        System.out.println("Oops!!!");
        break;
      }
    }
    System.out.println("end");
  }
}
