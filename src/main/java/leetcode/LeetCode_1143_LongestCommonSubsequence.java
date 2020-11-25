//Given two strings text1 and text2, return the length of their longest common subsequence.
//
//        A subsequence of a string is a new string generated from the original string with some characters(can be none) deleted without changing the relative order of the remaining characters. (eg, "ace" is a subsequence of "abcde" while "aec" is not). A common subsequence of two strings is a subsequence that is common to both strings.
//
//
//
//        If there is no common subsequence, return 0.
//
//
//
//        Example 1:
//
//        Input: text1 = "abcde", text2 = "ace"
//        Output: 3
//        Explanation: The longest common subsequence is "ace" and its length is 3.
//        Example 2:
//
//        Input: text1 = "abc", text2 = "abc"
//        Output: 3
//        Explanation: The longest common subsequence is "abc" and its length is 3.
//        Example 3:
//
//        Input: text1 = "abc", text2 = "def"
//        Output: 0
//        Explanation: There is no such common subsequence, so the result is 0.
//
//
//        Constraints:
//
//        1 <= text1.length <= 1000
//        1 <= text2.length <= 1000
//        The input strings consist of lowercase English characters only.
package leetcode;

public class LeetCode_1143_LongestCommonSubsequence {
    public int longestCommonSubsequence(String text1, String text2) {
        if (null == text1 || text2 == null || text1.length() == 0 || text2.length() == 0) {
            return 0;
        }
        char[] str1 = text1.toCharArray();
        char[] str2 = text2.toCharArray();

        int N = str1.length;
        int M = str2.length;
        int[][] dp = new int[N][M];
        for (int i = 0; i < N; i++) {
            if (str2[0] == str1[i]) {
                dp[i][0] = 1;
            }
            if (i != 0 && dp[i - 1][0] == 1) {
                dp[i][0] = 1;
            }
        }
        for (int i = 1; i < M; i++) {
            if (str1[0] == str2[i]) {
                dp[0][i] = 1;
            }
            if (dp[0][i - 1] == 1) {
                dp[0][i] = 1;
            }
        }
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                dp[i][j] = Math.max(Math.max(dp[i - 1][j - 1], dp[i][j - 1]), dp[i - 1][j]);
                if (str1[i] == str2[j]) {
                    dp[i][j] = Math.max(dp[i - 1][j - 1] + 1, dp[i][j]);
                }
            }
        }
        return dp[N - 1][M - 1];
    }
}
