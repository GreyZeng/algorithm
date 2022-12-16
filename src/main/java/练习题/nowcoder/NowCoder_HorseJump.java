package 练习题.nowcoder;

import java.util.*;

// 笔记：https://www.cnblogs.com/greyzeng/p/16839899.html
// 中国象棋中，整个棋盘就是横坐标上9条线、纵坐标上10条线的一个区域
// 给你三个 参数 x，y，k
// 返回“马”从(0,0)位置出发，必须走k步
// 最后落在(x,y)上的方法数有多少种?
// https://www.nowcoder.com/questionTerminal/c45704a41617402fb5c34a1778bb2645
public class NowCoder_HorseJump {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int x = in.nextInt();
    int y = in.nextInt();
    int k = in.nextInt();
    System.out.println(ways(x, y, k));
    in.close();
  }

  // 递归含义：还剩下step步，从(i,j)到达(a，b)可以选择的方法数是多少
  public static int ways(int i, int j, int a, int b, int step) {
    // 象棋区域 int[][] area = new int[10][9]
    if (i >= 10 || j >= 9 || i < 0 || j < 0) {
      // 越界
      return -1;
    }
    if (step == 0) {
      if (i == a && j == b) {
        return 1;
      }
      return -1;
    }
    // 四面八方尝试
    int p1 = ways(i - 2, j + 1, a, b, step - 1);
    int p2 = ways(i - 1, j + 2, a, b, step - 1);
    int p3 = ways(i - 1, j - 2, a, b, step - 1);
    int p4 = ways(i - 2, j - 1, a, b, step - 1);
    int p5 = ways(i + 2, j + 1, a, b, step - 1);
    int p6 = ways(i + 1, j + 2, a, b, step - 1);
    int p7 = ways(i + 1, j - 2, a, b, step - 1);
    int p8 = ways(i + 2, j - 1, a, b, step - 1);
    return ((p1 == -1) ? 0 : p1) + ((p2 == -1) ? 0 : p2) + ((p3 == -1) ? 0 : p3)
        + ((p4 == -1) ? 0 : p4) + ((p5 == -1) ? 0 : p5) + ((p6 == -1) ? 0 : p6)
        + ((p7 == -1) ? 0 : p7) + ((p8 == -1) ? 0 : p8);
  }

  // 根据暴力递归改动态规划
  public static int ways(int a, int b, int step) {
    // 象棋区域 int[][] area = new int[10][9]
    int[][][] dp = new int[10][9][step + 1];
    dp[a][b][0] = 1;
    for (int k = 0; k < step + 1; k++) {
      for (int i = 0; i < 10; i++) {
        for (int j = 0; j < 9; j++) {
          if (k == 0) {
            if (i == a && j == b) {
              dp[i][j][k] = 1;
            } else {
              dp[i][j][k] = -1;
            }
          } else {
            int p1 = (i - 2 >= 0 && j + 1 < 9) ? dp[i - 2][j + 1][k - 1] : -1;
            int p2 = (i - 1 >= 0 && j + 2 < 9) ? dp[i - 1][j + 2][k - 1] : -1;
            int p3 = (i - 1 >= 0 && j - 2 >= 0) ? dp[i - 1][j - 2][k - 1] : -1;
            int p4 = (i - 2 >= 0 && j - 1 >= 0) ? dp[i - 2][j - 1][k - 1] : -1;
            int p5 = (i + 2 < 10 && j + 1 < 9) ? dp[i + 2][j + 1][k - 1] : -1;
            int p6 = (i + 1 < 10 && j + 2 < 9) ? dp[i + 1][j + 2][k - 1] : -1;
            int p7 = (i + 1 < 10 && j - 2 >= 0) ? dp[i + 1][j - 2][k - 1] : -1;
            int p8 = (i + 2 < 10 && j - 1 >= 0) ? dp[i + 2][j - 1][k - 1] : -1;
            dp[i][j][k] = (p1 == -1 ? 0 : p1) + (p2 == -1 ? 0 : p2) + (p3 == -1 ? 0 : p3)
                + (p4 == -1 ? 0 : p4) + (p5 == -1 ? 0 : p5) + (p6 == -1 ? 0 : p6)
                + (p7 == -1 ? 0 : p7) + (p8 == -1 ? 0 : p8);
          }
        }
      }
    }
    return dp[0][0][step];
  }

}
