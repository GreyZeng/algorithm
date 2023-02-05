// 给你一个字符串s和一个字符规律p，请你来实现一个支持 '.'和'*'的正则表达式匹配。

// '.' 匹配任意单个字符
// '*' 匹配零个或多个前面的那一个元素
// 所谓匹配，是要涵盖整个字符串s的，而不是部分字符串。

//
// 示例 1：

// 输入：s = "aa", p = "a"
// 输出：false
// 解释："a" 无法匹配 "aa" 整个字符串。
// 示例 2:

// 输入：s = "aa", p = "a*"
// 输出：true
// 解释：因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
// 示例3：

// 输入：s = "ab", p = ".*"
// 输出：true
// 解释：".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
//

// 提示：

// 1 <= s.length<= 20
// 1 <= p.length<= 30
// s只包含从a-z的小写字母。
// p只包含从a-z的小写字母，以及字符.和*。
// 保证每次出现字符* 时，前面都匹配到有效的字符

// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/regular-expression-matching
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
package 练习题.leetcode.hard;

// 笔记：https://www.cnblogs.com/greyzeng/p/16331923.html
public class LeetCode_0010_RegularExpressionMatching {


    // 动态规划
    // 未优化枚举行为
    public static boolean isMatch2(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        char[] str = s.toCharArray();
        char[] pStr = p.toCharArray();
        if (!isValid(str, pStr)) {
            return false;
        }
        boolean[][] dp = new boolean[str.length + 1][pStr.length + 1];
        // 最后一列，除了 dp[str.length][pStr.length] = true
        // 其余位置都是false
        dp[str.length][pStr.length] = true;
        // 最后一行
        dp[str.length][pStr.length - 1] = false;
        for (int i = pStr.length - 2; i >= 0; i--) {
            if (((pStr.length - i) & 1) == 1) {
                dp[str.length][i] = false;
            } else if (i + 1 < pStr.length && pStr[i + 1] == '*') {
                dp[str.length][i] = dp[str.length][i + 2];
            } else {
                dp[str.length][i] = false;
            }
        }
        // 倒数第二列
        for (int i = str.length - 1; i >= 0; i--) {
            dp[i][pStr.length - 1] = ((str[i] == pStr[pStr.length - 1] || pStr[pStr.length - 1] == '.')
                    && dp[i + 1][pStr.length]);
        }
        for (int i = str.length - 1; i >= 0; i--) {
            for (int j = pStr.length - 2; j >= 0; j--) {
                if (pStr[j + 1] != '*') {
                    dp[i][j] = (str[i] == pStr[j] || pStr[j] == '.') && dp[i + 1][j + 1];
                } else if (dp[i][j + 2]) {
                    dp[i][j] = true;
                } else {
                    for (int k = i; k < str.length; k++) {
                        if (pStr[j] == str[k] || pStr[j] == '.') {
                            if (dp[k + 1][j + 2]) {
                                dp[i][j] = true;
                                break;
                            }
                        } else {
                            // dp[i][j] = false;
                            break;
                        }
                    }
                }
            }
        }
        return dp[0][0];
    }

    // 斜率优化版本
    public static boolean isMatch3(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        char[] str = s.toCharArray();
        char[] pStr = p.toCharArray();
        if (!isValid(str, pStr)) {
            return false;
        }
        boolean[][] dp = new boolean[str.length + 1][pStr.length + 1];
        // 最后一列，除了 dp[str.length][pStr.length] = true
        // 其余位置都是false
        dp[str.length][pStr.length] = true;
        // 最后一行
        dp[str.length][pStr.length - 1] = false;
        for (int i = pStr.length - 2; i >= 0; i--) {
            if (((pStr.length - i) & 1) == 1) {
                dp[str.length][i] = false;
            } else if (i + 1 < pStr.length && pStr[i + 1] == '*') {
                dp[str.length][i] = dp[str.length][i + 2];
            } else {
                dp[str.length][i] = false;
            }
        }
        // 倒数第二列
        for (int i = str.length - 1; i >= 0; i--) {
            dp[i][pStr.length - 1] = ((str[i] == pStr[pStr.length - 1] || pStr[pStr.length - 1] == '.')
                    && dp[i + 1][pStr.length]);
        }
        for (int i = str.length - 1; i >= 0; i--) {
            for (int j = pStr.length - 2; j >= 0; j--) {
                if (pStr[j + 1] != '*') {
                    dp[i][j] = (str[i] == pStr[j] || pStr[j] == '.') && dp[i + 1][j + 1];
                } else if (dp[i][j + 2]) {
                    dp[i][j] = true;
                } else if ((pStr[j] == str[i] || pStr[j] == '.') && (dp[i + 1][j + 2] || dp[i + 1][j])) {
                    dp[i][j] = true;
                }
            }
        }
        return dp[0][0];
    }

    // 首先过滤掉无效字符
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

    public static void main(String[] args) {
        String s = "aa";
        String p = "a*";
        System.out.println(isMatch3(s, p));
    }

    // 暴力递归
    public static boolean isMatch0(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        char[] str = s.toCharArray();
        char[] pStr = p.toCharArray();
        return isValid(str, pStr) && process0(str, pStr, 0, 0);
    }


    private static boolean process0(char[] s, char[] p, int si, int pi) {
        if (pi == p.length) {
            return si == s.length;
        }
        // 能到这里，说明：pi还没有到头
        if (si == s.length) {
            // si已经到头了
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
        // si和pi都没到头
        if (pi == p.length - 1) {
            return (s[si] == p[pi] || p[pi] == '.') && process0(s, p, si + 1, pi + 1);
        }
        if (p[pi + 1] != '*') {
            return (s[si] == p[pi] || p[pi] == '.') && process0(s, p, si + 1, pi + 1);
        }
        // pi 不是最后一个位置，且 p[pi+1] == '*'
        // p[pi] 和 p[pi+1]至少可以先消解为空串，即p[pi]位置不做匹配
        if (process0(s, p, si, pi + 2)) {
            return true;
        }
        // 如果走到这步，p[pi]消解为空串这条道路行不通
        // 所以只能让：p[pi] 匹配 s[si]
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
    // 缓存解法
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
