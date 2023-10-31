package git.snippet.metering;

// TODO
// 给定正整数n，找到若干个完全平方数（比如1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
// 给你一个整数 n ，返回和为 n 的完全平方数的 最少数量 。
// 完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和
// 11 不是。
// 示例1：
// 输入：n = 12
// 输出：3
// 解释：12 = 4 + 4 + 4
// 示例 2：
// 输入：n = 13
// 输出：2
// 解释：13 = 4 + 9
// Leetcode题目 : https://leetcode.com/problems/perfect-squares/
// tips:
// 打表技巧
// 观察输出结果
// 数学解
// 1）四平方和定理
// 2）任何数消掉4的因子，结论不变
public class LeetCode_0279_PerfectSquares {

  // 暴力解
  public static int numSquares1(int n) {
    int res = n, num = 2;
    while (num * num <= n) {
      int a = n / (num * num), b = n % (num * num);
      res = Math.min(res, a + numSquares1(b));
      num++;
    }
    return res;
  }

  // 1 : 1, 4, 9, 16, 25, 36, ...
  // 4 : 7, 15, 23, 28, 31, 39, 47, 55, 60, 63, 71, ...
  // 规律解
  // 规律一：个数不超过4
  // 规律二：出现1个的时候，显而易见
  // 规律三：任何数 % 8 == 7，一定是4个
  // 规律四：任何数消去4的因子之后，剩下rest，rest % 8 == 7，一定是4个
  public static int numSquares2(int n) {
    int rest = n;
    while (rest % 4 == 0) {
      rest /= 4;
    }
    if (rest % 8 == 7) {
      return 4;
    }
    int f = (int) Math.sqrt(n);
    if (f * f == n) {
      return 1;
    }
    for (int first = 1; first * first <= n; first++) {
      int second = (int) Math.sqrt(n - first * first);
      if (first * first + second * second == n) {
        return 2;
      }
    }
    return 3;
  }

  // 数学解
  // 1）四平方和定理
  // 2）任何数消掉4的因子，结论不变
  public static int numSquares3(int n) {
    while (n % 4 == 0) {
      n /= 4;
    }
    if (n % 8 == 7) {
      return 4;
    }
    for (int a = 0; a * a <= n; ++a) {
      // a * a + b * b = n
      int b = (int) Math.sqrt(n - a * a);
      if (a * a + b * b == n) {
        return (a > 0 && b > 0) ? 2 : 1;
      }
    }
    return 3;
  }

  public static void main(String[] args) {
    for (int i = 1; i < 1000; i++) {
      System.out.println(i + " , " + numSquares1(i));
    }
  }
}
