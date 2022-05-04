package leetcode.medium;

import java.util.ArrayList;
import java.util.List;

//给定两个字符串s和 p，找到s中所有p的异位词的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
//
//        异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。
//
//        
//
//        示例1:
//
//        输入: s = "cbaebabacd", p = "abc"
//        输出: [0,6]
//        解释:
//        起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
//        起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
//        示例 2:
//
//        输入: s = "abab", p = "ab"
//        输出: [0,1,2]
//        解释:
//        起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
//        起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
//        起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
//        
//
//        提示:
//
//        1 <= s.length, p.length <= 3 * 10^4
//        s和p仅包含小写字母
//
//        来源：力扣（LeetCode）
//        链接：https://leetcode-cn.com/problems/find-all-anagrams-in-a-string
//        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
// 滑动窗口 + 欠账表
public class LeetCode_0438_FindAllAnagramsInAString {
    public static List<Integer> findAnagrams(String s, String p) {
        ArrayList<Integer> result = new ArrayList<>();
        if (s == null || p == null) {
            return result;
        }
        int left = 0, right = 0, count = p.length();

        int[] map = new int[256];
        char[] sc = s.toCharArray();
        // 初始化map
        for (char c : p.toCharArray()) {
            map[c]++;
        }
        while (right < s.length()) {
            // 1：扩展窗口，窗口中包含一个T中子元素，count--；
            if (map[sc[right++]]-- >= 1) {
                count--;
            }
            // 2：通过count或其他限定值，得到一个可能解。
            if (count == 0) {
                result.add(left);
            }
            // 3：只要窗口中有可能解，那么缩小窗口直到不包含可能解。
            if (right - left == p.length() && map[sc[left++]]++ >= 0) {
                count++;
            }
        }
        return result;
    }

}
