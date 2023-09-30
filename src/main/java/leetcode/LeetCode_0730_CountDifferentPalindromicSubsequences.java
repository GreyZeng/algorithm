package leetcode;

import java.util.Arrays;

// 给定一个字符串 s，返回 s中不同的非空「回文子序列」个数 。
//
// 通过从 s中删除 0 个或多个字符来获得子序列。
//
// 如果一个字符序列与它反转后的字符序列一致，那么它是「回文字符序列」。
//
// 如果有某个 i , 满足ai!= bi，则两个序列a1, a2, ...和b1, b2, ...不同。
//
// 注意：
//
// 结果可能很大，你需要对10^9+ 7取模 。
//
//
// 示例 1：
//
// 输入：s = 'bccb'
// 输出：6
// 解释：6 个不同的非空回文子字符序列分别为：'b', 'c', 'bb', 'cc', 'bcb', 'bccb'。
// 注意：'bcb' 虽然出现两次但仅计数一次。
// 示例 2：
//
// 输入：s = 'abcdabcdabcdabcdabcdabcdabcdabcddcbadcbadcbadcbadcbadcbadcbadcba'
// 输出：104860361
// 解释：共有 3104860382 个不同的非空回文子序列，104860361 对 10^9 + 7 取模后的值。
//
//
// 提示：
//
// 1 <= s.length <= 1000
// s[i]仅包含'a','b','c'或'd'
//
// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/count-different-palindromic-subsequences
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class LeetCode_0730_CountDifferentPalindromicSubsequences {
  // leetcode要求不能有重复序列
  // TODO
  public int countPalindromicSubsequences(String s) {
    final int MOD = 1000_000_007;
    int n = s.length();
    int[][] dp = new int[n][n];
    int[][] next = new int[n][4];
    int[][] pre = new int[n][4];
    for (int i = 0; i < n; i++) {
      dp[i][i] = 1;
    }
    int[] pos = new int[4];
    Arrays.fill(pos, -1);
    for (int i = 0; i < n; i++) {
      for (int c = 0; c < 4; c++) {
        pre[i][c] = pos[c];
      }
      pos[s.charAt(i) - 'a'] = i;
    }
    pos[0] = pos[1] = pos[2] = pos[3] = n;

    for (int i = n - 1; i >= 0; i--) {
      for (int c = 0; c < 4; c++) {
        next[i][c] = pos[c];
      }
      pos[s.charAt(i) - 'a'] = i;
    }

    for (int len = 2; len <= n; len++) {
      for (int i = 0; i + len <= n; i++) {
        int j = i + len - 1;
        if (s.charAt(i) == s.charAt(j)) {
          int low = next[i][s.charAt(i) - 'a'];
          int high = pre[j][s.charAt(i) - 'a'];
          if (low > high) {
            dp[i][j] = (2 + dp[i + 1][j - 1] * 2) % MOD;
          } else if (low == high) {
            dp[i][j] = (1 + dp[i + 1][j - 1] * 2) % MOD;
          } else {
            dp[i][j] = (dp[i + 1][j - 1] * 2 % MOD - dp[low + 1][high - 1] + MOD) % MOD;
          }
        } else {
          dp[i][j] = ((dp[i + 1][j] + dp[i][j - 1]) % MOD - dp[i + 1][j - 1] + MOD) % MOD;
        }
      }
    }
    return dp[0][n - 1];
  }

  // 笔记：https://www.cnblogs.com/greyzeng/p/16412505.html
  // 以下解法是包含重复序列的
  // 给定一个字符串str，当然可以生成很多子序列，返回有多少个子序列是回文子序列，空序列不算回文
  // 比如，str = “aba”，回文子序列：{a}、{a}、 {a,a}、 {b}、{a,b,a}，返回5
  public static int ways1(String str) {
    if (str == null || str.length() == 0) {
      return 0;
    }
    char[] s = str.toCharArray();
    char[] path = new char[s.length];
    return process(str.toCharArray(), 0, path, 0);
  }

  public static int process(char[] s, int si, char[] path, int pi) {
    if (si == s.length) {
      return isP(path, pi) ? 1 : 0;
    }
    int ans = process(s, si + 1, path, pi);
    path[pi] = s[si];
    ans += process(s, si + 1, path, pi + 1);
    return ans;
  }

  public static boolean isP(char[] path, int pi) {
    if (pi == 0) {
      return false;
    }
    int l = 0;
    int r = pi - 1;
    while (l < r) {
      if (path[l++] != path[r--]) {
        return false;
      }
    }
    return true;
  }

  public static int ways2(String s) {
    if (s == null || s.length() == 0) {
      return 0;
    }
    char[] str = s.toCharArray();
    int n = str.length;
    int[][] dp = new int[n][n];
    // 对角线都是1
    for (int i = 0; i < n; i++) {
      dp[i][i] = 1;
    }
    for (int i = 0; i < n - 1; i++) {
      dp[i][i + 1] = str[i] == str[i + 1] ? 3 : 2;
    }
    for (int i = n - 3; i >= 0; i--) {
      for (int j = i + 2; j < n; j++) {
        // 减去dp[i+1][j-1]是因为算重复了
        dp[i][j] = dp[i + 1][j] + dp[i][j - 1] - dp[i + 1][j - 1];
        if (str[i] == str[j]) {
          // 加1表示：str[i]和str[j]单独组成一个回文
          // 加dp[i+1][j-1]表示：所有(i+1...j-1)区间内的子序列加上首尾两个字符组成的子序列数量
          dp[i][j] = dp[i + 1][j] + dp[i][j - 1] - dp[i + 1][j - 1] + dp[i + 1][j - 1] + 1;
        }
      }
    }
    return dp[0][n - 1];
  }

  public static String randomString(int len, int types) {
    char[] str = new char[len];
    for (int i = 0; i < str.length; i++) {
      str[i] = (char) ('a' + (int) (Math.random() * types));
    }
    return String.valueOf(str);
  }

  public static void main(String[] args) {
    int N = 15;
    int types = 5;
    int testTimes = 1000000;
    System.out.println("测试开始");
    for (int i = 0; i < testTimes; i++) {
      int len = (int) (Math.random() * N);
      String str = randomString(len, types);
      int ans1 = ways1(str);
      int ans2 = ways2(str);
      if (ans1 != ans2) {
        System.out.println(str);
        System.out.println(ans1);
        System.out.println(ans2);
        System.out.println("Oops!");
        break;
      }
    }
    System.out.println("测试结束");
  }
}
