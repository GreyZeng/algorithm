/*Given a string s, you can convert it to a palindrome by adding characters in front of it. Find and return the shortest palindrome you can find by performing this transformation.

 

Example 1:

Input: s = "aacecaaa"
Output: "aaacecaaa"
Example 2:

Input: s = "abcd"
Output: "dcbabcd"
 

Constraints:

0 <= s.length <= 5 * 10^4
s consists of lowercase English letters only.*/
package leetcode.hard;

// 使用manacher算法：https://www.cnblogs.com/greyzeng/p/15314213.html
public class LeetCode_0214_ShortestPalindrome {
    // 必须包含第一个字符的最长回文子串是什么
    // 剩余部分字符串逆序一下，就是需要求的值
    public static String shortestPalindrome(String s) {

        if (null == s || s.length() == 0) {
            return "";
        }
        char[] str = s.toCharArray();
        char[] strs = manacherString(str);
        int N = strs.length;
        int R = -1;
        int C = -1;
        int[] pArr = new int[N];
        int count = 0;
        for (int i = 0; i < N; i++) {
            // 先求pArr[i]至少不用扩的区域
            pArr[i] = i < R ? Math.min(pArr[C - (i - C)], R - i) : 1;
            // 两边继续扩充
            while (i + pArr[i] < N && i - pArr[i] > -1) {
                if (strs[i + pArr[i]] == strs[i - pArr[i]]) {
                    pArr[i]++;
                } else {
                    break;
                }
            }
            // 更新R和C
            if (i + pArr[i] > R) {
                R = i + pArr[i];
                C = i;
            }
            if (i - pArr[i] + 1 == 0) {
                count = i;
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i = N - 2; i > count + pArr[count] - 2; i -= 2) {
            sb.append(strs[i]);
        }
        sb.append(s);
        return sb.toString();

    }

    private static char[] manacherString(char[] str) {
        int N = str.length;
        int M = N * 2 + 1;
        char[] newArray = new char[M];
        for (int i = 0; i < M; i++) {
            newArray[i] = (i & 1) == 1 ? str[i / 2] : '#';
        }
        return newArray;
    }
}
