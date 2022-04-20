package leetcode.medium;

import java.util.ArrayList;
import java.util.List;

//给定长度为m的字符串aim，以及一个长度为n的字符串str
//		问能否在str中找到一个长度为m的连续子串，
//		使得这个子串刚好由aim的m个字符组成，顺序无所谓，
//		返回任意满足条件的一个子串的起始位置，未找到返回-1
// https://leetcode-cn.com/problems/find-all-anagrams-in-a-string/submissions/
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
