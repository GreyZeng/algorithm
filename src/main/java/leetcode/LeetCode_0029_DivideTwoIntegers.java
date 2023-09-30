package leetcode;

// 记住结论即可
// 只用位运算实现加减乘除
// https://leetcode.cn/problems/divide-two-integers
// 笔记：https://www.cnblogs.com/greyzeng/p/16637476.html
public class LeetCode_0029_DivideTwoIntegers {
  public static void main(String[] args) {
    // int a = -13;
    // System.out.println(a >>> 1);
    // System.out.println(a >> 1);
    // System.out.println(Integer.MIN_VALUE / (-1));
    // System.out.println(Integer.MIN_VALUE);
    System.out.println(13 ^ 20);
    System.out.println(-Integer.MIN_VALUE);
    System.out.println(Integer.MIN_VALUE);
    System.out.println(Integer.toBinaryString(418));
  }

  // 原始加法就是：无进位信息（异或）+进位信息
  public static int add(int a, int b) {
    int sum = a;
    while (b != 0) {
      // 异或运算就是无进位相加
      sum = a ^ b;
      // 进位信息
      b = (a & b) << 1;
      a = sum;
    }
    return sum;
  }

  // 某个数n的相反数就是 ~n + 1，由于不能用+号
  // 所以是 add(~n,1)
  public static int negNum(int n) {
    return add(~n, 1);
  }

  public static int minus(int a, int b) {
    return add(a, negNum(b));
  }

  // 参考小学算乘法的过程。
  public static int multi(int a, int b) {
    int res = 0;
    while (b != 0) {
      if ((b & 1) != 0) {
        res = add(res, a);
      }
      a <<= 1;
      b >>>= 1;
    }
    return res;
  }

  public static boolean isNeg(int n) {
    return n < 0;
  }

  // 全部转成正数来计算
  public static int div(int x, int y) {
    int a = isNeg(x) ? negNum(x) : x;
    int b = isNeg(y) ? negNum(y) : y;
    int res = 0;
    for (int i = 31; i > negNum(1); i = minus(i, 1)) {
      if ((a >> i) >= b) {
        res |= (1 << i);
        a = minus(a, b << i);
      }
    }
    return isNeg(x) ^ isNeg(y) ? negNum(res) : res;
  }

  public static int divide(int a, int b) {
    if (b == Integer.MIN_VALUE) {
      return a == Integer.MIN_VALUE ? 1 : 0;
    }
    // 除数不是系统最小
    if (a == Integer.MIN_VALUE) {
      if (b == negNum(1)) {
        // leetcode的题目要求
        return Integer.MAX_VALUE;
      }
      // 求 a / b
      // 先算 (a + 1)/b = c
      // 然后算 a - (b*c) = d
      // 然后 d / b = e
      // c + e = (a+1)/b + (a-(b*c))/b = a / b
      int c = div(add(a, 1), b);
      return add(c, div(minus(a, multi(c, b)), b));
    }
    // dividend不是系统最小，divisor也不是系统最小
    return div(a, b);
  }
  // div(a,b) a和b都不能是系统最小
}
