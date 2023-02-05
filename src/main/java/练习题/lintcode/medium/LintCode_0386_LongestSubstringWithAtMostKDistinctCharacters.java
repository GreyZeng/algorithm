/*
 * 386. 最多有k个不同字符的最长子字符串 给定字符串S，找到最多有k个不同字符的最长子串T。
 *
 * 样例 样例 1:
 *
 * 输入: S = "eceba" 并且 k = 3 输出: 4 解释: T = "eceb" 样例 2:
 *
 * 输入: S = "WORLD" 并且 k = 4 输出: 4 解释: T = "WORL" 或 "ORLD" 挑战 O(n) 时间复杂度
 */
package 练习题.lintcode.medium;

// 给定一个字符串str，和一个正数k，返回字符种类不超过k种的最长子串长度。
// Leetcode题目 : https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/
// leetcode 加锁 340
// https://www.lintcode.com/problem/longest-substring-with-at-most-k-distinct-characters/description
public class LintCode_0386_LongestSubstringWithAtMostKDistinctCharacters {

    public static int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null || s.length() == 0 || k < 1) {
            return 0;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        int dist = 0;
        int R = 0;
        int ans = 0;
        int[] count = new int[256];
        for (int i = 0; i < N; i++) {
            // 构建窗口
            while (R < N && (dist < k || (dist == k && count[str[R]] > 0))) {
                dist += count[str[R]] == 0 ? 1 : 0;
                count[str[R++]]++;
            }

            // 来到第一个违规的位置，开始结算答案
            ans = Math.max(ans, R - i);
            dist -= count[str[i]] == 1 ? 1 : 0;
            count[str[i]]--;
        }

        return ans;

    }

}
