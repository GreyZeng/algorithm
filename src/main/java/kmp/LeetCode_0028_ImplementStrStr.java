package kmp;

// KMP算法
// https://www.cnblogs.com/greyzeng/p/15317466.html
public class LeetCode_0028_ImplementStrStr {
    public static int strStr(String str, String match) {
        if (str == null || match == null || str.length() < match.length()) {
            return -1;
        }
        if (match.length() < 1) {
            return 0;
        }
        char[] s = str.toCharArray();
        char[] m = match.toCharArray();
        int[] next = getNext(m);
        int i = 0;
        int j = 0;
        while (i < s.length && j < m.length) {
            if (s[i] == m[j]) {
                i++;
                j++;
            } else if (j != 0) {
                j = next[j];
            } else {
                i++;
            }
        }
        return j == m.length ? (i - j) : -1;
    }

    private static int[] getNext(char[] m) {
        if (m.length == 1) {
            return new int[]{-1};
        }
        int[] next = new int[m.length];
        next[0] = -1;
        next[1] = 0;
        int i = 2;
        int cn = 0;
        while (i < next.length) {
            if (m[i - 1] == m[cn]) {
                next[i++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[i++] = 0;
            }
        }
        return next;
    }
}
