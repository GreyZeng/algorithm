/*
 * Given two strings s and t, return the minimum window in s which will contain all the characters
 * in t.
 *
 * If there is no such window in s that covers all characters in t, return the empty string "".
 *
 * Note that If there is such a window, it is guaranteed that there will always be only one unique
 * minimum window in s.
 *
 * Example 1:
 *
 * Input: s = "ADOBECODEBANC", t = "ABC" Output: "BANC" Example 2:
 *
 * Input: s = "a", t = "a" Output: "a"
 *
 *
 * Constraints:
 *
 * 1 <= s.length, t.length <= 10^5 s and t consist of English letters.
 */
package 练习题.leetcode.hard;

/**
 * @author Grey
 * @date 2021/02/22
 */
public class LeetCode_0076_MinimumWindowSubstring {

    // 欠账表 + all
    // 滑动窗口
    public static String minWindow(String s, String t) {
        // 如果目标串比原始串还大，则原始串无论如何都无法找到包含目标串所有字符的子串
        if (s.length() < t.length()) {
            return "";
        }
        char[] str = s.toCharArray();
        char[] target = t.toCharArray();
        // 初始化欠帐表
        // 如果不止ASCII码的字符，则可以用Hash表来实现欠账表
        int[] owe = new int[256];
        for (char c : target) {
            owe[c]++;
        }
        int all = target.length;
        int win = 0;
        int l = 0;
        int r = 0;
        // [finalL, finalL + win] 就是对应的结果字符串
        int finalL = 0;
        while (r != str.length) {
            owe[str[r]]--;
            if (owe[str[r]] >= 0) {
                // 有效还款
                all--;
            }
            // all等于0说明找到一个符合条件的窗口，开始结算
            if (all == 0) {
                // 开始移动L，缩小窗口
                while (owe[str[l]] < 0) {
                    // owe[str[l]] < 0 都是无效的还款
                    owe[str[l++]]++;
                }
                // 窗口没有形成或者窗口当前形成的窗口小于上一次的窗口大小，则更新
                if (win == 0 || win > r - l + 1) {
                    win = r - l + 1;
                    finalL = l;
                }
                owe[str[l++]]++;
                all++;
            }
            r++;
        }
        return s.substring(finalL, finalL + win);
    }
}
