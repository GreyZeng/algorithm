/*Given a string s, remove duplicate letters so that every letter appears once and only once. You must make sure your result is the smallest in lexicographical order among all possible results.

        Note: This question is the same as 1081: https://leetcode.com/problems/smallest-subsequence-of-distinct-characters/



        Example 1:

        Input: s = "bcabc"
        Output: "abc"
        Example 2:

        Input: s = "cbacdcbc"
        Output: "acdb"


        Constraints:

        1 <= s.length <= 10^4
        s consists of lowercase English letters.*/
package leetcode;

public class LeetCode_0316_RemoveDuplicateLetters {
    public static String removeDuplicateLetters(String s) {
        char[] str = s.toCharArray();
        int L = 0;
        int R = 0;
        // 词频表
        int[] map = new int[26];
        for (int i = 0; i < str.length; i++) {
            map[str[i] - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        while (R != str.length) {
            if (map[str[R] - 'a'] == -1 || --map[str[R] - 'a'] > 0) {
                R++;
            } else {
                // R位置的词频目前减到0了，可以从L...R之间收集一个最小ASCII码的字符了
                int p = -1;
                for (int i = L; i <= R; i++) {
                    if (map[str[i] - 'a'] != -1 && (p == -1 || str[i] < str[p])) {
                        p = i;
                    }
                }
                sb.append(str[p]);
                for (int i = p + 1; i <= R; i++) {
                    if (map[str[i] - 'a'] != -1) {
                        map[str[i] - 'a']++;
                    }
                }
                map[str[p] - 'a'] = -1;
                L = p + 1;
                R = L;
            }
        }
        return sb.toString();
    }
}
