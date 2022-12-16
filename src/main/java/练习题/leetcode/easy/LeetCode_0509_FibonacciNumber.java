package 练习题.leetcode.easy;

// https://www.cnblogs.com/greyzeng/p/15388178.html
public class LeetCode_0509_FibonacciNumber {
  // 暴力解法（递归版本）O(2^n)
  public static int fib(int N) {
    if (N <= 0) {
      return 0;
    }
    if (N == 1 || N == 2) {
      return 1;
    }
    return fib(N - 1) + fib(N - 2);
  }

  // 暴力解法（迭代版本）
  public static int fib2(int N) {
    if (N <= 0) {
      return 0;
    }
    if (N == 1 || N == 2) {
      return 1;
    }
    int first = 1;
    int second = 1;
    int result = 0;
    for (int i = 3; i <= N; i++) {
      result = first + second;
      first = second;
      second = result;
    }
    return result;
  }

  // 最优解 O(log^N)
  public static int fib3(int N) {
    if (N <= 0) {
      return 0;
    }
    if (N == 1 || N == 2) {
      return 1;
    }
    int[][] matrix = matrixPow(new int[][] {{0, 1}, {1, 1}}, N - 2);
    return matrix[0][1] + matrix[1][1];
  }

  public static int[][] matrixPow(int[][] matrix, int n) {
    int[][] ans = new int[][] {{1, 0}, {0, 1}};
    int[][] t = matrix;
    while (n != 0) {
      if ((n & 1) != 0) {
        ans = matrix(t, ans);
      }
      n >>= 1;
      t = matrix(t, t);
    }
    return ans;
  }

  public static int[][] matrix(int[][] A, int[][] B) {
    int[][] result = new int[2][2];
    result[0][0] = A[0][0] * B[0][0] + A[0][1] * B[1][0];
    result[0][1] = A[0][0] * B[0][1] + A[0][1] * B[1][1];
    result[1][0] = A[1][0] * B[0][0] + A[1][1] * B[1][0];
    result[1][1] = A[1][0] * B[0][1] + A[1][1] * B[1][1];
    return result;
  }
}
