package leetcode;

import java.util.ArrayList;
import java.util.List;

// 问题一：一个字符串至少需要添加多少个字符能整体变成回文串
// 问题二：返回问题一的其中一种添加结果
// 问题三：返回问题一的所有添加结果
// 测评链接： https://leetcode-cn.com/problems/minimum-insertion-steps-to-make-a-string-palindrome/
public class LeetCode_1312_MinimumInsertionStepsToMakeAStringPalindrome {
  // 至少添加多少字符让整个字符串变成回文串，范围上的尝试模型
  public static int minInsertions(String s) {
    if (s == null || s.length() == 0 || s.length() == 1) {
      return 0;
    }
    if (s.length() == 2) {
      return s.charAt(0) == s.charAt(1) ? 0 : 1;
    }
    char[] str = s.toCharArray();
    // dp[i][j]: 0...i范围内，
    int[][] dp = new int[str.length][str.length];
    // 对角线全是0
    // 对角线上一行，即【i，i+1】范围，相等则是0，不等则是1
    for (int i = 0; i < str.length - 1; i++) {
      dp[i][i + 1] = str[i] == str[i + 1] ? 0 : 1;
    }
    // 其余位置
    for (int i = str.length - 3; i >= 0; i--) {
      for (int j = i + 2; j < str.length; j++) {
        dp[i][j] = Math.min(dp[i][j - 1], dp[i + 1][j]) + 1;
        if (str[i] == str[j]) {
          dp[i][j] = Math.min(dp[i][j], dp[i + 1][j - 1]);
        }
      }
    }
    return dp[0][str.length - 1];
  }

  // TODO
  // 本题第二问，返回其中一种结果
  public static String minInsertionsOneWay(String s) {
    if (s == null || s.length() < 2) {
      return s;
    }
    char[] str = s.toCharArray();
    int N = str.length;
    int[][] dp = new int[N][N];
    for (int i = 0; i < N - 1; i++) {
      dp[i][i + 1] = str[i] == str[i + 1] ? 0 : 1;
    }
    for (int i = N - 3; i >= 0; i--) {
      for (int j = i + 2; j < N; j++) {
        dp[i][j] = Math.min(dp[i][j - 1], dp[i + 1][j]) + 1;
        if (str[i] == str[j]) {
          dp[i][j] = Math.min(dp[i][j], dp[i + 1][j - 1]);
        }
      }
    }

    int L = 0;
    int R = N - 1;
    char[] ans = new char[N + dp[L][R]];
    int ansl = 0;
    int ansr = ans.length - 1;
    while (L < R) {
      if (dp[L][R - 1] == dp[L][R] - 1) {
        ans[ansl++] = str[R];
        ans[ansr--] = str[R--];
      } else if (dp[L + 1][R] == dp[L][R] - 1) {
        ans[ansl++] = str[L];
        ans[ansr--] = str[L++];
      } else {
        ans[ansl++] = str[L++];
        ans[ansr--] = str[R--];
      }
    }
    if (L == R) {
      ans[ansl] = str[L];
    }
    return String.valueOf(ans);
  }

  // TODO
  // 本题第三问，返回所有可能的结果
  public static List<String> minInsertionsAllWays(String s) {
    List<String> ans = new ArrayList<>();
    if (s == null || s.length() < 2) {
      ans.add(s);
    } else {
      char[] str = s.toCharArray();
      int N = str.length;
      int[][] dp = new int[N][N];
      for (int i = 0; i < N - 1; i++) {
        dp[i][i + 1] = str[i] == str[i + 1] ? 0 : 1;
      }
      for (int i = N - 3; i >= 0; i--) {
        for (int j = i + 2; j < N; j++) {
          dp[i][j] = Math.min(dp[i][j - 1], dp[i + 1][j]) + 1;
          if (str[i] == str[j]) {
            dp[i][j] = Math.min(dp[i][j], dp[i + 1][j - 1]);
          }
        }
      }
      int M = N + dp[0][N - 1];
      char[] path = new char[M];
      process(str, dp, 0, N - 1, path, 0, M - 1, ans);
    }
    return ans;
  }

  // 当前来到的动态规划中的格子，(L,R)
  // path .... [pl....pr] ....
  public static void process(
      char[] str, int[][] dp, int L, int R, char[] path, int pl, int pr, List<String> ans) {
    if (L >= R) { // L > R L==R
      if (L == R) {
        path[pl] = str[L];
      }
      ans.add(String.valueOf(path));
    } else {
      if (dp[L][R - 1] == dp[L][R] - 1) {
        path[pl] = str[R];
        path[pr] = str[R];
        process(str, dp, L, R - 1, path, pl + 1, pr - 1, ans);
      }
      if (dp[L + 1][R] == dp[L][R] - 1) {
        path[pl] = str[L];
        path[pr] = str[L];
        process(str, dp, L + 1, R, path, pl + 1, pr - 1, ans);
      }
      if (str[L] == str[R] && (L == R - 1 || dp[L + 1][R - 1] == dp[L][R])) {
        path[pl] = str[L];
        path[pr] = str[R];
        process(str, dp, L + 1, R - 1, path, pl + 1, pr - 1, ans);
      }
    }
  }

  public static void main(String[] args) {
    String s;
    String ans2;
    List<String> ans3;

    System.out.println("本题第二问，返回其中一种结果测试开始");
    s = "mbadm";
    ans2 = minInsertionsOneWay(s);
    System.out.println(ans2);

    s = "练习题/leetcode";
    ans2 = minInsertionsOneWay(s);
    System.out.println(ans2);

    s = "aabaa";
    ans2 = minInsertionsOneWay(s);
    System.out.println(ans2);
    System.out.println("本题第二问，返回其中一种结果测试结束");

    System.out.println();

    System.out.println("本题第三问，返回所有可能的结果测试开始");
    s = "mbadm";
    ans3 = minInsertionsAllWays(s);
    for (String way : ans3) {
      System.out.println(way);
    }
    System.out.println();

    s = "练习题/leetcode";
    ans3 = minInsertionsAllWays(s);
    for (String way : ans3) {
      System.out.println(way);
    }
    System.out.println();

    s = "aabaa";
    ans3 = minInsertionsAllWays(s);
    for (String way : ans3) {
      System.out.println(way);
    }
    System.out.println();
    System.out.println("本题第三问，返回所有可能的结果测试结束");
  }
}
