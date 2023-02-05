package 练习题.leetcode.easy;

// 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
// 注意：若s 和 t中每个字符出现的次数都相同，则称s 和 t互为字母异位词。
// 示例1:
// 输入: s = "anagram", t = "nagaram"
// 输出: true
// 示例 2:
// 输入: s = "rat", t = "car"
// 输出: false
// Leetcode题目 : https://leetcode.com/problems/valid-anagram/
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
