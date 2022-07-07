package leetcode.medium;

// https://leetcode.cn/problems/smallest-subsequence-of-distinct-characters/
// 和 https://leetcode.cn/problems/remove-duplicate-letters/ 一样
public class LeetCode_1081_SmallestSubsequenceOfDistinctCharacters {
    public String smallestSubsequence(String s) {
        char[] str = s.toCharArray();
        int L = 0;
        int R = 0;
        // 词频表
        int[] map = new int[26];
        for (char c : str) {
            map[c - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        while (R != str.length) {
            if (map[str[R] - 'a'] == -1 || --map[str[R] - 'a'] > 0) {
                R++;
            } else {
                // R位置的词频目前减到0了，可以从L...R之间收集一个最小ASCII码的字符了
                int p = -1;
                for (int i = L; i <= R; i++) {
                    if (map[str[i] - 'a'] != -1 && (p == -1 || str[i] < str[p])) {
                        p = i;
                    }
                }
                sb.append(str[p]);
                for (int i = p + 1; i <= R; i++) {
                    if (map[str[i] - 'a'] != -1) {
                        map[str[i] - 'a']++;
                    }
                }
                map[str[p] - 'a'] = -1;
                L = p + 1;
                R = L;
            }
        }
        return sb.toString();
    }
}
