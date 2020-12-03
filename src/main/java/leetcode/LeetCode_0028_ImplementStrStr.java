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


    public static int strStr(String haystack, String needle) {
        if (haystack == null || needle == null || needle.length() > haystack.length()) {
            return -1;
        }
        if (needle.length() < 1) {
            return 0;
        }
        char[] s = haystack.toCharArray();
        char[] m = needle.toCharArray();
        int M = m.length;
        int[] next = getNextArr(m, M);
        int x = 0;
        int y = 0;
        while (x < s.length && y < M) {
            if (s[x] == m[y]) {
                x++;
                y++;
            } else if (y != 0) {
                y = next[y];

            } else {
                x++;
            }
        }
        return y == M ? x - y : -1;
    }

    private static int[] getNextArr(char[] match, int M) {
        if (M == 1) {
            return new int[]{-1};
        }
        int[] next = new int[M];
        next[0] = -1;
        next[1] = 0;
        int i = 2;
        int cn = 0;
        while (i < next.length) {
            if (match[i - 1] == match[cn]) {
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
