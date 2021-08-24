//描述
//        给出两个字符串，找到最长公共子序列(LCS)，返回LCS的长度。
//
//        最长公共子序列的定义：
//
//        最长公共子序列问题是在一组序列（通常2个）中找到最长公共子序列（注意：不同于子串，LCS不需要是连续的子串）。该问题是典型的计算机科学问题，是文件差异比较程序的基础，在生物信息学中也有所应用。
//        https://en.wikipedia.org/wiki/Longest_common_subsequence_problem
//        样例
//        样例 1：
//
//        输入：
//
//        A = "ABCD"
//        B = "EDCA"
//        输出：
//
//        1
//        解释：
//
//        LCS是 'A' 或 'D' 或 'C'
//        样例 2：
//
//        输入：
//
//        A = "ABCD"
//        B = "EACB"
//        输出：
//
//        2
//        解释：
//
//        LCS 是 "AC"
package lintcode;

/**
 * https://www.lintcode.com/problem/longest-common-subsequence/description
 *
 * @author <a href="mailto:410486047@qq.com">Grey</a>
 * @date 2021/8/24
 * @since
 */
public class LintCode_0077_LongestCommonSubsequence {
    public static void main(String[] args) {
        String A = "bedaacbade";
        String B = "dccaeedbeb";
        int i = new LintCode_0077_LongestCommonSubsequence().longestCommonSubsequence(A, B);
        System.out.println(i);
    }

    // 暴力方法
    public int longestCommonSubsequence(String A, String B) {
        if ((null == A || A.length() == 0) || (B == null || B.length() == 0)) {
            return 0;
        }
        char[] str1 = A.toCharArray();
        char[] str2 = B.toCharArray();
        return p(str1, str2, str1.length - 1, str2.length - 1);
    }

    // str1 从0...i
    // str2 从0...j
    // 最长公共子序列的长度是多少
    public int p(char[] str1, char[] str2, int i, int j) {
        if (i == 0 && j == 0) {
            return str1[i] == str2[j] ? 1 : 0;
        } else if (i == 0) {
            if (str1[i] == str2[j]) {
                return 1;
            } else {
                return p(str1, str2, i, j - 1);
            }
        } else if (j == 0) {
            if (str1[i] == str2[j]) {
                return 1;
            } else {
                return p(str1, str2, i - 1, j);
            }
        }
        int p1 = p(str1, str2, i - 1, j);
        int p2 = p(str1, str2, i, j - 1);
        int p3 = str1[i] == str2[j] ? (1 + p(str1, str2, i - 1, j - 1)) : p(str1, str2, i - 1, j - 1);
        return Math.max(p3, Math.max(p1, p2));
    }

    public int longestCommonSubsequence2(String A, String B) {
        if ((null == A || A.length() == 0) || (B == null || B.length() == 0)) {
            return 0;
        }
        char[] str1 = A.toCharArray();
        char[] str2 = B.toCharArray();
        int m = str1.length;
        int n = str2.length;
        int[][] dp = new int[m][n];
        dp[0][0] = str1[0] == str2[0] ? 1 : 0;
        for (int i = 1 ; i < n; i++) {
            dp[0][i] = str1[0] == str2[i]?1:dp[0][i - 1];
        }
        for (int i = 1 ; i < m; i++) {
            dp[i][0] = str1[i] == str2[0]?1:dp[i - 1][0];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.max(dp[i - 1][j],dp[i][j - 1]);
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
