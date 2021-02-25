/*We can scramble a string s to get a string t using the following algorithm:

If the length of the string is 1, stop.
If the length of the string is > 1, do the following:
Split the string into two non-empty substrings at a random index, i.e., if the string is s, divide it to x and y where s = x + y.
Randomly decide to swap the two substrings or to keep them in the same order. i.e., after this step, s may become s = x + y or s = y + x.
Apply step 1 recursively on each of the two substrings x and y.
Given two strings s1 and s2 of the same length, return true if s2 is a scrambled string of s1, otherwise, return false.



Example 1:

Input: s1 = "great", s2 = "rgeat"
Output: true
Explanation: One possible scenario applied on s1 is:
"great" --> "gr/eat" // divide at random index.
"gr/eat" --> "gr/eat" // random decision is not to swap the two substrings and keep them in order.
"gr/eat" --> "g/r / e/at" // apply the same algorithm recursively on both substrings. divide at ranom index each of them.
"g/r / e/at" --> "r/g / e/at" // random decision was to swap the first substring and to keep the second substring in the same order.
"r/g / e/at" --> "r/g / e/ a/t" // again apply the algorithm recursively, divide "at" to "a/t".
"r/g / e/ a/t" --> "r/g / e/ a/t" // random decision is to keep both substrings in the same order.
The algorithm stops now and the result string is "rgeat" which is s2.
As there is one possible scenario that led s1 to be scrambled to s2, we return true.
Example 2:

Input: s1 = "abcde", s2 = "caebd"
Output: false
Example 3:

Input: s1 = "a", s2 = "a"
Output: true


Constraints:

s1.length == s2.length
1 <= s1.length <= 30
s1 and s2 consist of lower-case English letters.*/
package leetcode;

public class LeetCode_0087_ScrambleString {

    public static boolean isScramble(String s1, String s2) {
        if (s1 == null && s2 == null) {
            return true;
        }
        if (s1 == null) {
            return false;
        }
        if (s2 == null) {
            return false;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        if (!isValid(str1, str2)) {
            return false;
        }
        return f(str1, str2, 0, 0, str2.length);
    }

    // str1中，L1往后（包括L1）一共k个字符串 以及  str2中，L2往后（包括L2）一共k个字符串 是否互为扰动串
    private static boolean f(char[] str1, char[] str2, int L1, int L2, int k) {
        if (k == 1) {
            // base case， 针对这样的情况，只需要判断str1[L1], str2[L2]
            return str1[L1] == str2[L2];
        }
        // 枚举第一刀的位置
        for (int left = 1; left < k; left++) {
            boolean case1 = f(str1, str2, L1, L2, left) && f(str1, str2, L1 + left, L2 + left, k - left);
            boolean case2 = f(str1, str2, L1 + left, L2, k - left) && f(str1, str2, L1, L2 + k - left, left);
            if (case1 || case2) {
                return true;
            }
        }
        return false;
    }

    private static boolean isValid(char[] str1, char[] str2) {
        if (str1.length != str2.length) {
            return false;
        }
        int[] map = new int[26];
        for (char c : str1) {
            map[c - 'a']++;
        }
        for (char c : str2) {
            map[c - 'a']--;
            if (map[c - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }


}
