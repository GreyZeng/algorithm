package git.snippet.leetcode;

// 统计所有小于非负整数 n 的质数的数量。
// 示例 1：
// 输入：n = 10
// 输出：4
// 解释：小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
// 示例 2：
// 输入：n = 0
// 输出：0
// 示例 3：
// 输入：n = 1
// 输出：0
// 提示：
// 0 <= n <= 5 * 10^6
// Leetcode题目 : https://leetcode.com/problems/count-primes/
public class LeetCode_0204_CountPrimes {
  public static int countPrimes(int n) {
    if (n < 3) {
      return 0;
    }
    boolean[] f = new boolean[n];
    int count = n >> 1; // 0~n范围内的偶数直接排除
    for (int i = 3; i * i < n; i += 2) {
      // 判断奇数是不是
      if (!f[i]) {
        // 排除掉奇数中有因数的值
        for (int j = i * i; j < n; j += (2 * i)) {
          if (!f[j]) {
            count--;
            f[j] = true;
          }
        }
      }
    }
    return count;
  }

  // 这个不是最优解
  public static int countPrimes2(int n) {
    int res = 0;
    for (int i = 2; i < n; i++) {
      if (isPrime(i)) {
        res++;
      }
    }
    return res;
  }

  public static boolean isPrime(int x) {
    for (int i = 2; i * i <= x; i++) {
      if (x % i == 0) {
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    countPrimes(10);
  }
}
