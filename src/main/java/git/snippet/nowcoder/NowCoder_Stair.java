// 链接：https://www.nowcoder.com/questionTerminal/b178fcef3ed4448c99d7c0297312212d
// 来源：牛客网
//
// 在你面前有一个n阶的楼梯，你一步只能上1阶或2阶。
// 请问计算出你可以采用多少种不同的方式爬完这个楼梯。
//
// 输入描述:
// 一个正整数n(n<=100)，表示这个楼梯一共有多少阶
//
//
// 输出描述:
// 一个正整数，表示有多少种不同的方式爬完这个楼梯
// 示例1
// 输入
// 5
// 输出
// 8
package git.snippet.nowcoder;

import java.math.BigInteger;
import java.util.Scanner;

// 一个人可以一次往上迈1个台阶，也可以迈2个台阶
//
// 返回这个人迈上N级台阶的方法数
public class NowCoder_Stair {

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    in.close();
    if (n <= 2) {
      System.out.println(n);
      return;
    }
    System.out.println(ways(n));
  }

  public static BigInteger ways1(int n) {
    BigInteger f1 = BigInteger.valueOf(1);
    BigInteger f2 = BigInteger.valueOf(2);
    BigInteger result = null;

    for (int i = 3; i < n + 1; i++) {
      result = f1.add(f2);
      f1 = f2;
      f2 = result;
    }
    return result;
  }

  public static BigInteger ways(int n) {
    BigInteger[][] base = {
      {BigInteger.valueOf(1), BigInteger.valueOf(1)}, {BigInteger.valueOf(1), BigInteger.valueOf(0)}
    };
    BigInteger[][] result = matrixPow(base, n - 2);
    return result[1][0].add(result[0][0].multiply(BigInteger.valueOf(2)));
  }

  public static BigInteger[][] matrixPow(BigInteger[][] m, int n) {
    BigInteger[][] ans = {
      {BigInteger.valueOf(1), BigInteger.valueOf(0)}, {BigInteger.valueOf(0), BigInteger.valueOf(1)}
    };
    BigInteger[][] t = m;
    while (n != 0) {
      if ((n & 1) != 0) {
        ans = matrixMultiply(ans, t);
      }
      t = matrixMultiply(t, t);
      n = (n >> 1);
    }
    return ans;
  }

  private static BigInteger[][] matrixMultiply(BigInteger[][] ans, BigInteger[][] t) {
    BigInteger a = ans[0][0].multiply(t[0][0]).add(ans[0][1].multiply(t[1][0]));
    BigInteger b = ans[0][0].multiply(t[0][1]).add(ans[0][1].multiply(t[1][1]));
    BigInteger c = ans[1][0].multiply(t[0][0]).add(ans[1][1].multiply(t[1][0]));
    BigInteger d = ans[1][0].multiply(t[0][1]).add(ans[1][1].multiply(t[1][1]));
    return new BigInteger[][] {{a, b}, {c, d}};
  }
}
