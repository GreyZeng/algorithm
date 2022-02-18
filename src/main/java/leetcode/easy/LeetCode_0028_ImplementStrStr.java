package leetcode.easy;

// KMP算法
// https://www.cnblogs.com/greyzeng/p/15317466.html
public class LeetCode_0028_ImplementStrStr {

    public static int strStr(String str, String match) {
        if (str == null || match == null || match.length() > str.length()) {
            return -1;
        }
        if (match.length() < 1) {
            return 0;
        }
        char[] s = str.toCharArray();
        char[] m = match.toCharArray();
        int[] next = getNextArr(m);
        int x = 0;
        int y = 0;
        while (y < s.length && x < m.length) {
            if (s[y] == m[x]) {
                y++;
                x++;
            } else if (x != 0) {
                x = next[x];
            } else {
                y++;
            }
        }
        return x == m.length ? y - x : -1;
    }

    // 求解next数组逻辑
    private static int[] getNextArr(char[] str) {
        if (str.length == 1) {
            return new int[]{-1};
        }
        int[] next = new int[str.length];
        next[0] = -1;
        next[1] = 0;
        int i = 2; // 目前在哪个位置上求next数组值
        int cn = 0; // 前后缀最长字符的长度，也表示下一个要比的信息位置
        while (i < next.length) {
            if (str[i - 1] == str[cn]) {
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
