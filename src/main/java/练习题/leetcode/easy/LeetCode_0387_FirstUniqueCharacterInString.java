/*
 * Given a string, find the first non-repeating character in it and return its index. If it doesn't
 * exist, return -1.
 *
 * Examples:
 *
 * s = "leetcode" return 0.
 *
 * s = "loveleetcode" return 2.
 *
 *
 * Note: You may assume the string contains only lowercase English letters.
 */
package 练习题.leetcode.easy;

public class LeetCode_0387_FirstUniqueCharacterInString {

    public int firstUniqChar(String s) {
        int[] count = new int[26];
        char[] str = s.toCharArray();
        int N = str.length;
        for (int i = 0; i < N; i++) {
            count[str[i] - 'a']++;
        }
        for (int i = 0; i < N; i++) {
            if (count[str[i] - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }

}
