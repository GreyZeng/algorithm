package git.snippet.leetcode;

import java.util.ArrayList;
import java.util.List;

// 给定两个字符串s和 p，找到s中所有p的异位词的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
// 异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。
// 示例1:
//
// 输入: s = "cbaebabacd", p = "abc"
// 输出: [0,6]
// 解释:
// 起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
// 起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
// 示例 2:
//
// 输入: s = "abab", p = "ab"
// 输出: [0,1,2]
// 解释:
// 起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
// 起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
// 起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
// 提示:
// 1 <= s.length, p.length <= 3 * 10^4
// s和p仅包含小写字母
//
// 来源：力扣（LeetCode）
// 链接：https://leetcode-cn.com/problems/find-all-anagrams-in-a-string
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
// 笔记：https://www.cnblogs.com/greyzeng/p/16485378.html
public class LeetCode_0438_FindAllAnagramsInAString {
    // 滑动窗口 + 欠账表
    public List<Integer> findAnagrams(String s, String p) {
        if (s == null || p == null) {
            return new ArrayList<>();
        }
        List<Integer> ans = new ArrayList<>();
        char[] str = s.toCharArray();
        char[] pStr = p.toCharArray();
        // 词频表
        int[] map = new int[26];
        // 窗口从[l,r)
        int l = 0;
        int r = 0;
        // 欠账数量
        int c = pStr.length;
        for (char ch : pStr) {
            map[ch - 'a']++;
        }
        int n = str.length;
        while (r < n) {
            if (map[str[r] - 'a'] > 0) {
                // 有效还款
                c--;
            }
            map[str[r++] - 'a']--;
            if (c == 0) {
                // 收集到一个位置
                ans.add(l);
            }
            // 形成窗口
            if (r - l == pStr.length) {
                // l面临要出窗口
                if (map[str[l] - 'a'] >= 0) {
                    // 退回去
                    c++;
                }
                map[str[l++] - 'a']++;
            }
        }
        return ans;
    }
}
