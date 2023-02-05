package 练习题.leetcode.medium;

import java.util.HashSet;

// TODO
// 给定两个字符串pattern和s，返回s是否符合pattern的规定
// 这里的符合是指，pattern里的每个字母和字符串s中子串，存在着单一映射双向连接的对应
// 例子1：
// 输入：pattern = "abab", s = "redblueredblue"
// 输出：true
// 解释：
// 映射如下
// 'a' -> "red"
// 'b' -> "blue"
// 例子2
// 输入：pattern = "aaaa", s = "asdasdasdasd"
// 输出：true
// 解释:
// 映射如下
// 'a' -> "asd"
// 例子3
// 输入: pattern = "abab", s = "asdasdasdasd"
// 输出: true
// 解释:
// 映射如下
// 'a' -> "a"
// 'b' -> "sdasd"
// 例子4
// 输入：pattern = "aabb", s = "xyzabcxzyabc"
// 输出：false
// Leetcode题目 : https://leetcode.com/problems/word-pattern-ii/
public class LeetCode_0291_WordPatternII {

    public static boolean wordPatternMatch(String pattern, String str) {
        return match(str, pattern, 0, 0, new String[26], new HashSet<>());
    }

    // 题目有限制，str和pattern其中的字符，一定是a~z小写
    // p[a] -> "abc"
    // p[b] -> "fbf"
    // 需要指代的表最多26长度
    // String[] map -> new String[26]
    // p[a] -> "abc" map[0] -> "abc"
    // p[b] -> "fbf" map[1] -> "fbf";
    // p[z] -> "kfk" map[25] -> "kfk"
    // HashSet<String> set -> map中指代了哪些字符串
    // str[si.......] 是不是符合 p[pi......]？符合返回true，不符合返回false
    // 之前的决定！由map和set，告诉我！不能冲突！
    public static boolean match(String s, String p, int si, int pi, String[] map,
                                HashSet<String> set) {
        if (pi == p.length() && si == s.length()) {
            return true;
        }
        // str和pattern，并没有都结束！
        if (pi == p.length() || si == s.length()) {
            return false;
        }
        // str和pattern，都没结束！

        char ch = p.charAt(pi);
        String cur = map[ch - 'a'];
        if (cur != null) { // 当前p[pi]已经指定过了！
            return si + cur.length() <= s.length() // 不能越界！
                    && cur.equals(s.substring(si, si + cur.length()))
                    && match(s, p, si + cur.length(), pi + 1, map, set);
        }
        // p[pi]没指定！
        int end = s.length();
        // 剪枝！重要的剪枝！
        // 通过pattern的后续字符，留出足够的空间
        for (int i = p.length() - 1; i > pi; i--) {
            end -= map[p.charAt(i) - 'a'] == null ? 1 : map[p.charAt(i) - 'a'].length();
        }
        for (int i = si; i < end; i++) {
            // 从si出发的所有前缀串，全试
            cur = s.substring(si, i + 1);
            // 但是，只有这个前缀串，之前没占过别的坑！才能去尝试
            if (!set.contains(cur)) {
                set.add(cur);
                map[ch - 'a'] = cur;
                if (match(s, p, i + 1, pi + 1, map, set)) {
                    return true;
                }
                map[ch - 'a'] = null;
                set.remove(cur);
            }
        }
        return false;
    }

}
