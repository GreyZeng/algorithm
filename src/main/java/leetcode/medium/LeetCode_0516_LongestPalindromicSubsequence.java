package leetcode.medium;

// 笔记：https://www.cnblogs.com/greyzeng/p/16760224.html
// 最长回文子序列问题
// 方式1：一个串和它的逆序串的最大公共子序列就是这个串的最长回文子序列
// 方式2：范围尝试
// https://www.lintcode.com/problem/667/
// https://leetcode.cn/problems/longest-palindromic-subsequence/
public class LeetCode_0516_LongestPalindromicSubsequence {
    // 暴力解
    public static int longestPalindromeSubseq2(String s) {
        if (s == null || s.length() < 1) {
            return 0;
        }
        char[] str = s.toCharArray();
        return process(0, str.length - 1, str);
    }

    // i...j的最长回文子序列是多少
    public static int process(int i, int j, char[] str) {
        if (i == j) {
            return 1;
        }
        if (i == j - 1) {
            return str[i] == str[j] ? 2 : 1;
        }
        int p1 = process(i + 1, j, str);
        int p2 = process(i, j - 1, str);
        int p3 = (str[i] == str[j] ? 2 : 0) + process(i + 1, j - 1, str);
        return Math.max(p1, Math.max(p2, p3));
    }

    // 动态规划，范围上的尝试
    public static int longestPalindromeSubseq(String s) {
        if (s == null || s.length() < 1) {
            return 0;
        }
        char[] str = s.toCharArray();
        int[][] dp = new int[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            dp[i][i] = 1;
            if (i != s.length() - 1) {
                dp[i][i + 1] = str[i] == str[i + 1] ? 2 : 1;
            }
        }

        for (int index = 2; index < s.length(); index++) {
            int i = 0;
            int j = index;
            while (j < s.length()) {
                int p1 = dp[i + 1][j];
                int p2 = dp[i][j - 1];
                int p3 = (str[i] == str[j] ? 2 : 0) + dp[i + 1][j - 1];
                dp[i][j] = Math.max(p1, Math.max(p2, p3));
                i++;
                j++;
            }
        }

        return dp[0][s.length() - 1];
    }


    // 方法1： 一个串和它的逆序串的最长公共子序列长度就是这个串的最大回文子序列的长度
    public int longestPalindromeSubseq3(String s) {
        char[] str1 = s.toCharArray();
        int n = str1.length;
        char[] str2 = new char[n];
        for (char str : str1) {
            str2[--n] = str;
        }
        return longestCommonSubsequence2(str1, str2);
    }

    // 最长公共子序列
    public int longestCommonSubsequence2(char[] str1, char[] str2) {
        if ((null == str1 || str1.length == 0) || str2 == null || str2.length == 0) {
            return 0;
        }
        int m = str1.length;
        int n = str2.length;
        int[][] dp = new int[m][n];
        dp[0][0] = str1[0] == str2[0] ? 1 : 0;
        for (int i = 1; i < n; i++) {
            dp[0][i] = str1[0] == str2[i] ? 1 : dp[0][i - 1];
        }
        for (int i = 1; i < m; i++) {
            dp[i][0] = str1[i] == str2[0] ? 1 : dp[i - 1][0];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                if (str1[i] == str2[j]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
                } else {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1]);
                }
            }
        }
        return dp[m - 1][n - 1];
    }
}
