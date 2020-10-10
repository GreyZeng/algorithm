package leetcode;

//Given two strings s and t , write a function to determine if t is an anagram of s.
//
//        Example 1:
//
//        Input: s = "anagram", t = "nagaram"
//        Output: true
//        Example 2:
//
//        Input: s = "rat", t = "car"
//        Output: false
//        Note:
//        You may assume the string contains only lowercase alphabets.
//
//        Follow up:
//        What if the inputs contain unicode characters? How would you adapt your solution to such case?
public class LeetCode_0242_ValidAnagram {

    // 统计词频
    public static boolean isAnagram(String s, String t) {
        if (s == null && t != null) {
            return false;
        }
        if (s != null && t == null) {
            return false;
        }
        if (s == null) {
            return true;
        }
        char[] str = s.toCharArray();
        char[] to = t.toCharArray();
        int L = str.length;
        int N = to.length;
        if (L != N) {
            return false;
        }
        int[] c = new int[26];
        for (char x : str) {
            c[x - 'a']++;
        }
        for (char x : to) {
            c[x - 'a']--;
            if (c[x - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }

}
