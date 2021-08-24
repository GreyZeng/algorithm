package lintcode;

// https://www.lintcode.com/problem/667/
// 最长回文子序列长度
public class LintCode_0667_LongestPalindromicSubsequence {
    // 方法1： 一个串和它的逆序串的最长公共子序列长度就是这个串的最大回文子序列的长度
    public int longestPalindromeSubseq(String s) {
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

    // 方法2
    // 范围内的尝试[L...R] 暴力递归
    public int longestPalindromeSubseq2(String s) {
        if (s == null || s.length() < 1) {
            return 0;
        }
        int n = s.length();
        char[] str = s.toCharArray();
        return p(str, n, 0, n - 1);
    }
    // i...j范围的最长回文子序列长度是多少
    public int p(char[] str, int n, int i, int j) {
        if (i == j) {
            return 1;
        }
        if (i == j - 1) {
            return str[i] == str[j] ? 2 : 1;
        }
        int p1 = p(str, n, i + 1, j);
        int p2 = p(str, n, i, j - 1);
        int p3 = p(str, n, i + 1, j - 1) + (str[i] == str[j] ? 2 : 0);
        return Math.max(p3, Math.max(p1, p2));
    }
    // 动态规划
    public int longestPalindromeSubseq3(String s) {
        if (s == null || s.length() < 1) {
            return 0;
        }
        int n = s.length();
        char[] str = s.toCharArray();
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        for (int i = 0; i < n - 1; i++) {
            dp[i][i + 1] = (str[i] == str[i + 1]) ? 2 : 1;
        }
        
        for (int i = 2; i <= n; i++) {
            int r = 0;
            int c = i;
            while (r < n - i ) {
                int p1 = dp[r + 1][c];
                int p2 = dp[r][c - 1];
                int p3 = dp[r + 1][c - 1] + (str[r] == str[c] ? 2 : 0);
                dp[r][c] =  Math.max(p3, Math.max(p1, p2));
                r++;
                c++;
            }
        }
        return dp[0][n - 1];
    }
}
