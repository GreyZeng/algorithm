package leetcode;

// 最长公共子序列
// 子序列是可以不连续的
// 笔记：https://www.cnblogs.com/greyzeng/p/16829972.html
// https://leetcode-cn.com/problems/longest-common-subsequence/
public class LeetCode_1143_LongestCommonSubsequence {

    public static int longestCommonSubsequence(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() < 1 || s2.length() < 1) {
            return 0;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int[][] dp = new int[s1.length()][s2.length()];
        dp[0][0] = str1[0] == str2[0] ? 1 : 0;
        for (int i = 1; i < s2.length(); i++) {
            dp[0][i] = str1[0] == str2[i] ? 1 : dp[0][i - 1];
        }
        for (int i = 1; i < s1.length(); i++) {
            dp[i][0] = str1[i] == str2[0] ? 1 : dp[i - 1][0];
        }
        for (int i = 1; i < str1.length; i++) {
            for (int j = 1; j < str2.length; j++) {
                int p1 = dp[i - 1][j];
                // 最长公共子序列不要j位置
                int p2 = dp[i][j - 1];
                // 既要i位置，也要j位置(需要满足条件 str[i] == str[j])
                // 既不要i位置，也不要j位置
                int p3 = (str1[i] == str2[j] ? 1 : 0) + dp[i - 1][j - 1];
                dp[i][j] = Math.max(p2, Math.max(p1, p3));
            }
        }
        // 最长公共子序列不要i位置
        return dp[s1.length() - 1][s2.length() - 1];
    }

    public static int longestCommonSubsequence2(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() < 1 || s2.length() < 1) {
            return 0;
        }
        return process(s1.toCharArray(), s2.toCharArray(), s1.length() - 1, s2.length() - 1);
    }

    // str1 从0....i
    // str2 从0....j
    // 最长公共子序列是多少
    public static int process(char[] str1, char[] str2, int i, int j) {
        if (i == 0 && j == 0) {
            return str1[i] == str2[j] ? 1 : 0;
        }
        if (i == 0) {
            if (str1[i] == str2[j]) {
                return 1;
            }
            return process(str1, str2, i, j - 1);
        }
        if (j == 0) {
            if (str1[i] == str2[j]) {
                return 1;
            }
            return process(str1, str2, i - 1, j);
        }
        // 最长公共子序列不要i位置
        int p1 = process(str1, str2, i - 1, j);
        // 最长公共子序列不要j位置
        int p2 = process(str1, str2, i, j - 1);
        // 既要i位置，也要j位置(需要满足条件 str[i] == str[j])
        // 既不要i位置，也不要j位置
        int p3 = (str1[i] == str2[j] ? 1 : 0) + process(str1, str2, i - 1, j - 1);
        return Math.max(p2, Math.max(p1, p3));
    }

}
