/*Given a string s, find the longest palindromic subsequence's length in s. You may assume that the maximum length of s is 1000.

        Example 1:
        Input:

        "bbbab"
        Output:
        4
        One possible longest palindromic subsequence is "bbbb".


        Example 2:
        Input:

        "cbbd"
        Output:
        2
        One possible longest palindromic subsequence is "bb".


        Constraints:

        1 <= s.length <= 1000
        s consists only of lowercase English letters.*/
package leetcode;

public class LeetCode_0516_LongestPalindromicSubsequence {
    
    public static int longestPalindromeSubseq(String s) {
        char[] str = s.toCharArray();
        int n = str.length;
        // L...R 最大回文长度存dp[L][R]中
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        for (int i = 0; i < n - 1; i++) {
            dp[i][i + 1] = str[i] == str[i + 1] ? 2 : 1;
        }
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 2; j < n; j++) {
                dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                if (str[i] == str[j]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i + 1][j - 1] + 2);
                }
            }
        }
        return dp[0][n - 1];
    }
    
    public static int longestPalindromeSubseq2(String s) {
        char[] str1 = s.toCharArray();
        char[] reverseStr = reverse(str1);
        return lcp(str1, reverseStr);
    }

    public static char[] reverse(char[] str1) {
        char[] str2 = new char[str1.length];
        int index = str1.length - 1;
        for (int i = 0; i < str1.length; i++) {
            str2[i] = str1[index--];
        }
        return str2;
    }

    public static int lcp(char[] str1, char[] str2) {
        int m = str1.length;
        int n = str2.length;
        int[][] dp = new int[m][n];
        dp[0][0] = str1[0] == str2[0] ? 1 : 0;
        for (int i = 1; i < m; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], str1[i] == str2[0] ? 1 : 0);
        }
        for (int j = 1; j < n; j++) {
            dp[0][j] = Math.max(dp[0][j - 1], str1[0] == str2[j] ? 1 : 0);
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                if (str1[i] == str2[j]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
                }
            }
        }
        return dp[m - 1][n - 1];
    }
    
    


}
