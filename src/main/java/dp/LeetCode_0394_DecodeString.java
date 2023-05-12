/*
 * Given an encoded string, return its decoded string.
 *
 * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is
 * being repeated exactly k times. Note that k is guaranteed to be a positive integer.
 *
 * You may assume that the input string is always valid; No extra white spaces, square brackets are
 * well-formed, etc.
 *
 * Furthermore, you may assume that the original data does not contain any digits and that digits
 * are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].
 *
 *
 *
 * Example 1:
 *
 * Input: s = "3[a]2[bc]" Output: "aaabcbc" Example 2:
 *
 * Input: s = "3[a2[c]]" Output: "accaccacc" Example 3:
 *
 * Input: s = "2[abc]3[cd]ef" Output: "abcabccdcdcdef" Example 4:
 *
 * Input: s = "abc3[cd]xyz" Output: "abccdcdcdxyz"
 */
package dp;

// 笔记：https://www.cnblogs.com/greyzeng/p/17001706.html
// 递归的嵌套结构 嵌套问题 计算器问题
// https://leetcode.cn/problems/decode-string/
public class LeetCode_0394_DecodeString {

    public static String decodeString(String s) {
        char[] str = s.toCharArray();
        int len = str.length;
        return p(str, len, 0)[0];
    }

    private static String[] p(char[] str, int n, int from) {
        StringBuilder sb = new StringBuilder();
        int pre = 0;
        int i = from;
        while (i != n && str[i] != ']') {
            if (Character.isLowerCase(str[i]) || Character.isUpperCase(str[i])) {
                // 字母
                sb.append(str[i++]);
            } else if (Character.isDigit(str[i])) {
                // 数字
                pre = pre * 10 + Integer.parseInt(String.valueOf(str[i++]));
            } else if ('[' == str[i]) {
                // 左括号
                String[] bra = p(str, n, i + 1);
                sb.append(build(pre, bra[0]));
                pre = 0;
                i = Integer.parseInt(bra[1]) + 1;
            }
        }
        return new String[]{sb.toString(), String.valueOf(i)};
    }

    private static String build(int pre, String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < pre; i++) {
            sb.append(s);
        }
        return sb.toString();
    }
}
