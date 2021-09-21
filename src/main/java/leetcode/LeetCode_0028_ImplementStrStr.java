//Implement strStr().
//
//		Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
//
//		Clarification:
//
//		What should we return when needle is an empty string? This is a great question to ask during an interview.
//
//		For the purpose of this problem, we will return 0 when needle is an empty string. This is consistent to C's strstr() and Java's indexOf().
//
//
//
//		Example 1:
//
//		Input: haystack = "hello", needle = "ll"
//		Output: 2
//		Example 2:
//
//		Input: haystack = "aaaaa", needle = "bba"
//		Output: -1
//		Example 3:
//
//		Input: haystack = "", needle = ""
//		Output: 0
//
//
//		Constraints:
//
//		0 <= haystack.length, needle.length <= 5 * 10^4
//		haystack and needle consist of only lower-case English characters.
package leetcode;

// KMP算法
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
        int l = m.length;
        int[] next = getNextArr(m, l);
        int x = 0;
        int y = 0;
        while (y < s.length && x < l) {
            if (s[y] == m[x]) {
                y++;
                x++;
            } else if (x != 0) {
                x = next[x];
            } else {
                y++;
            }
        }
        return x == l ? y - x : -1;
    }

    // 求解next数组逻辑
    private static int[] getNextArr(char[] str, int l) {
        if (l == 1) {
            return new int[]{-1};
        }
        int[] next = new int[l];
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
