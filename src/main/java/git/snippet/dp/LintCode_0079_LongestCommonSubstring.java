/*
 * 79. 最长公共子串 给出两个字符串，找到最长公共子串，并返回其长度。
 *
 *
 *
 * 样例 样例 1: 输入: "ABCD" and "CBCE" 输出: 2
 *
 * 解释: 最长公共子串是 "BC"
 */
package git.snippet.dp;

public class LintCode_0079_LongestCommonSubstring {
    public static int longestCommonSubstring(String A, String B) {
        if (A == null || B == null || A.length() == 0 || B.length() == 0) {
            return 0;
        }
        char[] str1 = A.toCharArray();
        char[] str2 = B.toCharArray();
        int m = str1.length;
        int n = str2.length;
        // 必须以str1[i]和str[j]结尾的最长公共子串是多少
        int[][] dp = new int[m][n];
        // 第0列
        int max = 0;
        for (int i = 0; i < m; i++) {
            if (str1[i] == str2[0]) {
                dp[i][0] = 1;
                max = 1;
            }
        }
        // 第0行
        for (int i = 0; i < n; i++) {
            if (str1[0] == str2[i]) {
                dp[0][i] = 1;
                max = 1;
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (str1[i] == str2[j]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    max = Math.max(max, dp[i][j]);
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        String A = "abcd";
        String B = "ac";
        System.out.println(longestCommonSubstring(A, B));
    }
}
