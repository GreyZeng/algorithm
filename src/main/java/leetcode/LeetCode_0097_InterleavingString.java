// 给定三个字符串str1、str2和aim，如果aim包含且仅包含来自str1和str2的所有字符， 
// 而且在aim中属于str1的字符之间保持原来在str1中的顺序，属于str2的字符之间保持 原来在str2中的顺序，
// 那么称aim是str1和str2的交错组成。实现一个函数，判断aim是 否是str1和str2交错组成
// 【举例】 str1="AB"，str2="12"。那么"AB12"、"A1B2"、"A12B"、"1A2B"和"1AB2"等都是 str1 和 str2 的 交错组成

// tips:
// 一个样本做行一个样本做列的模型
// dp[i][j] str1 拿出前缀i长度，str2 拿出前缀j长度，能否交错组成str[i+j]
package leetcode;
// Constraints:

// 0 <= s1.length, s2.length <= 100
// 0 <= s3.length <= 200
// s1, s2, and s3 consist of lower-case English letters.
public class LeetCode_0097_InterleavingString {
    public static boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        if (s1.length() == 0) {
            return s2.equals(s3);
        }
        if (s2.length() == 0) {
            return s1.equals(s3);
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        char[] str3 = s3.toCharArray();

        boolean[][] dp = new boolean[str1.length + 1][str2.length + 1];
        dp[0][0] = true;
        for (int i = 1; i <= str1.length; i++) {
            if (str1[i - 1] != str3[i - 1]) {
                break;
            }
            dp[i][0] = true;
        }

        for (int i = 1; i <= str2.length; i++) {
            if (str2[i - 1] != str3[i - 1]) {
                break;
            }
            dp[0][i] = true;
        }
        for (int i = 1; i < str1.length + 1; i++) {
            for (int j = 1; j < str2.length + 1; j++) {
                dp[i][j] = ((dp[i - 1][j] && str1[i - 1] == str3[i + j - 1])
                        || (dp[i][j - 1] && str2[j - 1] == str3[i + j - 1]));
            }
        }

        return dp[str1.length][str2.length];
    }
    
}
