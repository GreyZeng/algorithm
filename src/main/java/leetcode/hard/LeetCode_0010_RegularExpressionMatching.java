/*
 * Given an input string (s) and a pattern (p), implement regular expression
 * matching with support for '.' and '*'.
 *
 * '.' Matches any single character. '*' Matches zero or more of the preceding
 * element.
 *
 * The matching should cover the entire input string (not partial).
 *
 * Note:
 *
 * s could be empty and contains only lowercase letters a-z. p could be empty
 * and contains only lowercase letters a-z, and characters like . or *.
 *
 * Example 1:
 *
 * Input: s = "aa" p = "a" Output: false Explanation: "a" does not match the
 * entire string "aa".
 *
 * Example 2:
 *
 * Input: s = "aa" p = "a*" Output: true Explanation: '*' means zero or more of
 * the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes
 * "aa".
 *
 * Example 3:
 *
 * Input: s = "ab" p = ".*" Output: true Explanation: ".*" means
 * "zero or more (*) of any character (.)".
 *
 * Example 4:
 *
 * Input: s = "aab" p = "c*a*b" Output: true Explanation: c can be repeated 0
 * times, a can be repeated 1 time. Therefore, it matches "aab".
 *
 * Example 5:
 *
 * Input: s = "mississippi" p = "mis*is*p*." Output: false
 *1 <= s.length <= 20
 * 1 <= p.length <= 30
 * s contains only lowercase English letters.
 * p contains only lowercase English letters, '.', and '*'.
 * It is guaranteed for each appearance of the character '*', there will be a previous valid character to match.
 */
package leetcode.hard;

public class LeetCode_0010_RegularExpressionMatching {

    // 斜率优化版本
    public static boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        char[] str = s.toCharArray();
        char[] exp = p.toCharArray();
        if (!isValid(str, exp)) {
            return false;
        }
        boolean[][] dp = new boolean[str.length + 1][exp.length + 1];
        // 最后一列除了最后一个位置，都是false
        dp[str.length][exp.length] = true;
        for (int i = exp.length - 2; i >= 0; i--) {
            if (i + 1 < exp.length && exp[i + 1] == '*') {
                dp[str.length][i] = dp[str.length][i + 2];
            }
        }
        for (int si = str.length - 1; si >= 0; si--) {
            for (int pi = exp.length - 1; pi >= 0; pi--) {
                if (pi == exp.length - 1 || exp[pi + 1] != '*') {
                    dp[si][pi] = (str[si] == exp[pi] || exp[pi] == '.') && dp[si + 1][pi + 1];
                } else {
                    if (dp[si][pi + 2]) {
                        dp[si][pi] = true;
                    } else {
                        // dp[si][pi] = dp[si][pi + 2];
                        dp[si][pi] = (exp[pi] == str[si] || exp[pi] == '.') && (dp[si + 1][pi + 2] || dp[si + 1][pi]);
                    }

                }
            }
        }
        // p从0开始一直到最后，能否匹配出s从0开始一直到最后的位置
        return dp[0][0];
    }

    private static boolean isValid(char[] str, char[] exp) {
        for (char c : str) {
            if (c == '.' || c == '*') {
                return false;
            }
        }
        for (int i = 0; i < exp.length; i++) {
            if (exp[i] == '*' && (i == 0 || exp[i - 1] == '*')) {
                return false;
            }
        }
        return true;
    }

    // 暴力递归
    public static boolean isMatch0(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        char[] str = s.toCharArray();
        char[] exp = p.toCharArray();
        return isValid(str, exp) && process0(str, exp, 0, 0);
    }

    private static boolean process0(char[] s, char[] p, int si, int pi) {
        if (pi == p.length) {
            return si == s.length;
        }
        // pi还没有到头
        if (si == s.length) {
            if (((p.length - pi) & 1) == 1) {
                // pi及以后的字符必须首先是偶数个，剩余奇数个数了,后面如何都做不到变成空串了。
                return false;
            }
            if (pi + 1 < p.length && p[pi + 1] == '*') {
                // 后面必须是 : 有效字符 + "*"的组合模式
                return process0(s, p, si, pi + 2);
            }
            return false;
        }

        if (pi == p.length - 1 || p[pi + 1] != '*') {
            return (s[si] == p[pi] || p[pi] == '.') && process0(s, p, si + 1, pi + 1);
        }
        // pi 不是最后一个位置，且 p[pi+1] == '*'
        // 看p[pi] 和 s[si] 是否相等
//        if (p[pi] != s[si] && p[pi] != '.') {
//            return false;
//        }
        // p[pi] == '.' 或者 p[pi] == s[si] 且 p[pi + 1] = '*'
        // p[pi] 和 p[pi+1]至少可以消解为空串，即p[pi]位置不做匹配
        if (process0(s, p, si, pi + 2)) {
            return true;
        }
        // p[pi] 匹配 s[si]
        // 然后将p[pi+1]的'*'衍生出:
        // 0个p[pi]
        // 1个p[pi]
        // 2个p[pi]
        // ...
        // n个p[pi]
        for (int i = si; i < s.length; i++) {
            if (p[pi] == s[i] || p[pi] == '.') {
                if (process0(s, p, i + 1, pi + 2)) {
                    return true;
                }
            } else {
                break;
            }
        }
        return false;
    }

    // 动态规划
    public static boolean isMatch1(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        char[] str = s.toCharArray();
        char[] exp = p.toCharArray();
        if (!isValid(str, exp)) {
            return false;
        }
        int[][] dp = new int[str.length + 1][exp.length + 1];
        // dp[i][j] == 0 表示没有填过
        // dp[i][j] == 1 表示匹配上
        // dp[i][j] == - 1表示没有匹配上

        return dp1(str, exp, 0, 0, dp);
    }

    public static boolean dp1(char[] s, char[] p, int si, int pi, int[][] dp) {
        if (dp[si][pi] != 0) {
            return dp[si][pi] == 1;
        }
        if (pi == p.length) {
            boolean ans = (si == s.length);
            dp[si][pi] = ans ? 1 : -1;
            return ans;
        }
        if (si == s.length) {
            if (((p.length - pi) & 1) == 1) {
                dp[si][pi] = -1;
                return false;
            }
            if (pi + 1 < p.length && p[pi + 1] == '*') {
                boolean ans = dp1(s, p, si, pi + 2, dp);
                dp[si][pi] = ans ? 1 : -1;
                return ans;
            }
            dp[si][pi] = -1;
            return false;
        }

        if (pi == p.length - 1 || p[pi + 1] != '*') {
            boolean ans = (s[si] == p[pi] || p[pi] == '.') && dp1(s, p, si + 1, pi + 1, dp);
            dp[si][pi] = ans ? 1 : -1;
            return ans;
        }
        if (dp1(s, p, si, pi + 2, dp)) {
            dp[si][pi] = 1;
            return true;
        }
        for (int i = si; i < s.length; i++) {
            if (p[pi] == s[i] || p[pi] == '.') {
                if (dp1(s, p, i + 1, pi + 2, dp)) {
                    dp[si][pi] = 1;
                    return true;
                }
            } else {
                break;
            }
        }
        dp[si][pi] = -1;
        return false;
    }

}
