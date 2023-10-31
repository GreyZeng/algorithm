package git.snippet.leetcode;

// TODO
// 给定整数 n 和 k，找到 1 到 n 中字典序第 k 小的数字。
// 注意：1 ≤ k ≤ n ≤ 10^9。
// 示例 :
// 输入:
// n: 13 k: 2
// 输出:
// 10
// 解释:
// 字典序的排列是 [1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9]，所以第二小的数字是 10。
// leetcode题目：https://leetcode.cn/problems/k-th-smallest-in-lexicographical-order/
// 这道题在leetcode上，所有题解都只能做到O( (logN) 平方)的解
// 我们课上讲的是O(logN)的解
// tips：
// 比如 672 ：
// 1，
// 10，100~109
// 11，110~119
// ...
// 以x开头 <=3位的有几个？有111个，推断一下
// 原始数：46564，分成三部分，1~3，4，1~9
public class LeetCode_0440_KthSmallestInLexicographicalOrder {

  public static int[] offset = {
    0, 1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000, 1000000000
  };

  public static int[] number = {
    0, 1, 11, 111, 1111, 11111, 111111, 1111111, 11111111, 111111111, 1111111111
  };

  public static int findKthNumber(int n, int k) {
    // 数字num，有几位，len位
    // 65237, 5位，len = 5
    int len = len(n);
    // 65237, 开头数字，6，first
    int first = n / offset[len];
    // 65237，左边有几个？
    int left = (first - 1) * number[len];
    int pick = 0;
    int already = 0;
    if (k <= left) {
      // k / a 向上取整-> (k + a - 1) / a
      pick = (k + number[len] - 1) / number[len];
      already = (pick - 1) * number[len];
      return kth((pick + 1) * offset[len] - 1, len, k - already);
    }
    int mid = number[len - 1] + (n % offset[len]) + 1;
    if (k - left <= mid) {
      return kth(n, len, k - left);
    }
    k -= left + mid;
    len--;
    pick = (k + number[len] - 1) / number[len] + first;
    already = (pick - first - 1) * number[len];
    return kth((pick + 1) * offset[len] - 1, len, k - already);
  }

  public static int len(int n) {
    int len = 0;
    while (n != 0) {
      n /= 10;
      len++;
    }
    return len;
  }

  public static int kth(int max, int len, int kth) {
    // 中间范围还管不管的着！
    // 有任何一步，中间位置没命中，左或者右命中了，那以后就都管不着了！
    // 但是开始时，肯定是管的着的！
    boolean closeToMax = true;
    int ans = max / offset[len];
    while (--kth > 0) {
      max %= offset[len--];
      int pick = 0;
      if (!closeToMax) {
        pick = (kth - 1) / number[len];
        ans = ans * 10 + pick;
        kth -= pick * number[len];
      } else {
        int first = max / offset[len];
        int left = first * number[len];
        if (kth <= left) {
          closeToMax = false;
          pick = (kth - 1) / number[len];
          ans = ans * 10 + pick;
          kth -= pick * number[len];
          continue;
        }
        kth -= left;
        int mid = number[len - 1] + (max % offset[len]) + 1;
        if (kth <= mid) {
          ans = ans * 10 + first;
          continue;
        }
        closeToMax = false;
        kth -= mid;
        len--;
        pick = (kth + number[len] - 1) / number[len] + first;
        ans = ans * 10 + pick;
        kth -= (pick - first - 1) * number[len];
      }
    }
    return ans;
  }
}
