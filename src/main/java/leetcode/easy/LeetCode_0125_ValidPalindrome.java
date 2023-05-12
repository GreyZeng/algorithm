package leetcode.easy;


// Given a string, determine if it is a palindrome, considering only alphanumeric characters and
// ignoring cases.
//
// Note: For the purpose of this problem, we define empty string as valid palindrome.
//
// Example 1:
//
// Input: "A man, a plan, a canal: Panama"
// Output: true
// Example 2:
//
// Input: "race a car"
// Output: false
//
//
// Constraints:
//
// s consists only of printable ASCII characters.
@Deprecated
public class LeetCode_0125_ValidPalindrome {

    public static boolean isPalindrome(String s) {
        if (s == null || s.length() <= 1) {
            return true;
        }
        char[] str = s.toCharArray();
        int L = 0;
        int R = str.length - 1;
        while (L < R) {
            if (validChar(str[L]) && validChar(str[R])) {
                if (equal(str[L], str[R])) {
                    L++;
                    R--;
                } else {
                    return false;
                }
            } else {
                if (!validChar(str[L])) {
                    L++;
                } else if (!validChar(str[R])) {
                    R--;
                } else {
                    L++;
                    R--;
                }
            }
        }
        return true;
    }

    public static boolean validChar(char c) {
        return isLetter(c) || isNumber(c);
    }

    public static boolean equal(char c1, char c2) {
        if (isNumber(c1) || isNumber(c2)) {
            return c1 == c2;
        }
        return (c1 == c2) || (Math.max(c1, c2) - Math.min(c1, c2) == 32);
    }

    public static boolean isLetter(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }

    public static boolean isNumber(char c) {
        return (c >= '0' && c <= '9');
    }
}
