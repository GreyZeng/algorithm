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
package leetcode;

// TODO 需要强化练习
public class LeetCode_0010_RegularExpressionMatching {
    // 判断是否合法的str和exp
    // str中不能含有. 和 *
    // exp中，*不能开头，且两个*不能连在一起
    // exp中，.可以替换成任何的非空字符，*必须和前面的字符一起作用，可以让前面的字符变成0个或者多个
    // 比如：exp: abc*b 可以匹配 str: abb, 也可以匹配str: abcb 也可以匹配：abcccb，也可以匹配abccccccb,依次类推
    // exp: ab.b 可以匹配 abmb，abcb，abdb，依次类推，但是不能匹配abb，因为.不能替换空字符
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

    public static boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        char[] str = s.toCharArray();
        char[] exp = p.toCharArray();
        if (!isValid(str, exp)) {
            // 这个校验无论做不做都可以过leetcode的测评
            return false;
        }
        int M = str.length;
        int N = exp.length;
        boolean[][] dp = new boolean[M + 1][N + 1];
        dp[M][N] = true;
        for (int i = N - 2; i >= 0; i -= 2) {
            if (exp[i] != '*' && exp[i + 1] == '*') {
                dp[M][i] = true;
            } else {
                break;
            }
        }
        if (M > 0 && N > 0) {
            if (str[M - 1] == exp[N - 1] || exp[N - 1] == '.') {
                dp[M - 1][N - 1] = true;
            }
        }
        for (int i = M - 1; i >= 0; i--) {
            for (int j = N - 2; j >= 0; j--) {
                if (exp[j + 1] != '*') {
                    dp[i][j] = ((exp[j] == str[i]) || (exp[j] == '.')) && dp[i + 1][j + 1];
                } else {
                    if (exp[j] != '.' && exp[j] != str[i]) {
                        dp[i][j] = dp[i][j + 2];
                    } else {
                        if (dp[i][j + 2]) {
                            dp[i][j] = dp[i][j + 2];
                        } else {
                            if (i + 1 >= M || str[i] != str[i + 1] && (exp[j] != '.')) {
                                dp[i][j] = dp[i + 1][j + 2];
                            } else {
                                dp[i][j] = dp[i + 1][j];
                            }
                        }
                    }
                }
            }
        }
        return dp[0][0];
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

    // 这个递归的前提条件是：ei位置不能为*
    private static boolean process0(char[] s, char[] p, int si, int pi) {
        if (pi == p.length) {
            // 情况1.
            // exp串都到了最后，则str必须也到了最后才可以，
            // 否则str如果残余一些还没匹配，则肯定匹配失败
            return s.length == si;
        }
        if (si == s.length) {
//            if (ei == exp.length) {
//                // 情况2.
//                // str串都到了最后，exp也到了最后,肯定匹配成功
//                return true;
//            }
            if (((p.length - pi) & 1) == 1) {
                // pi及以后的字符必须首先是偶数个，剩余奇数个数了,后面如何如何都做不到变成空串了。
                return false;
            }
            // 情况3.
            // str串如果到了最后(成了个空串)
            // exp串还没结束，则exp余下的串必须是：X*Y*C*M* 这样”字符+*“成对出现的格式，因为只有这样的格式才能匹配一个空串（每个*把它前一个字母消掉）
            // 所以exp接下来的串中，ei位置不为*，ei+1位置必须是*，
            // 这样一来，ei和ei+1就可以搭配生成一个空串与str匹配 因为 X* 可以匹配 ”“，同时递归调用从ei+2位置开始匹配上str串
            if (pi + 1 < p.length && p[pi + 1] == '*') {
                return process0(s, p, si, pi + 2);
            }
            return false;
        }
        // 情况4. ei的下一个位置不是* (下面的情况5，6都是ei的下一个位置是*的情况)
        // 那么exp[ei] 位置 必须要和str[si]位置的字符一样，或者exp[ei]位置是个.也可以
        // 然后就ei和si就匹配上了，接下来调用递归 从 ei+1 , si+1之后开始匹配
        if (pi + 1 == p.length || p[pi + 1] != '*') {
            return (p[pi] == s[si] || p[pi] == '.') && process0(s, p, si + 1, pi + 1);
        }

        // 情况5. ei+1位置是*，
        // 那么不管ei位置是什么，我可以通过ei+1位置的*把ei位置的值消掉，相当于ei和ei+1已经成了空的，
        // 接下来调用递归
        // 用si去匹配ei+2位置，如果匹配上，也返回true
        if (process0(s, p, si, pi + 2)) {
            return true;
        }
        // 情况6. ei+1位置是*
        // 这里的while循环的意思是：ei+1位置的*让ei位置的值变成N个去一一匹配str串，匹配上了则返回true
        while (si != s.length && (p[pi] == s[si] || p[pi] == '.')) {
            if (process0(s, p, si + 1, pi + 2)) {
                return true;
            }
            si++;
        }
        return false;
    }

    // 动态规划
    public boolean isMatch2(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        char[] str = s.toCharArray();
        char[] exp = p.toCharArray();
        int[][] dp = new int[str.length + 1][exp.length + 1];
        return isValid(str, exp) && process2(str, exp, 0, 0, dp);
    }


    private boolean process2(char[] s, char[] e, int si, int ei, int[][] dp) {
        if (dp[si][ei] != 0) {
            return dp[si][ei] == 1;
        }
        if (ei == e.length) {
            boolean ans = (si == s.length);
            dp[si][ei] = ans ? 1 : -1;
            return ans;
        }
        if (ei + 1 == e.length || e[ei + 1] != '*') {
            boolean ans = (si != s.length && (s[si] == e[ei] || e[ei] == '.') && process2(s, e, si + 1, ei + 1, dp));
            dp[si][ei] = ans ? 1 : -1;
            return ans;
        }
        while (si != s.length && (e[ei] == s[si] || e[ei] == '.')) {
            if (process2(s, e, si, ei + 2, dp)) {
                dp[si][ei] = 1;
                return true;
            }
            si++;
        }
        boolean ans = process2(s, e, si, ei + 2, dp);
        dp[si][ei] = ans ? 1 : -1;
        return ans;
    }
}
