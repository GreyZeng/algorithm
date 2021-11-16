package leetcode;

public class LeetCode_0010_RegularExpressionMatching1 {

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

    // 这个递归的前提条件是：ei位置不能为*
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
            return s[si] == p[pi] || p[pi] == '.' && process0(s, p, si + 1, pi + 1);
        }
        // pi 不是最后一个位置，且 p[pi+1] == '*'
        // 看p[pi] 和 s[si] 是否相等
        if (p[pi] != s[si] && p[pi] != '.') {
            return false;
        }
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
        for (int i = si + 1; i < s.length; i++) {
            if (process0(s,p,i,pi+2)) {
                return true;
            }
        }
        return false;
    }
}
