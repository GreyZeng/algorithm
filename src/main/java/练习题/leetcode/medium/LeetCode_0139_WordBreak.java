package 练习题.leetcode.medium;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

// 假设所有字符都是小写字母
// 大字符串是str
// arr是去重的单词表, 每个单词都不是空字符串且可以使用任意次.
// 使用arr中的单词有多少种拼接str的方式. 返回方法数.
// https://leetcode-cn.com/problems/word-break/
// lintcode有同样的题目
// https://www.lintcode.com/problem/107/
// 因为使用了更强大的数据，所以普通的DFS方法无法解决此题。
// s.length <= 1e5
// dict.size <= 1e5
// 使用arr中的单词有多少种拼接str的方式. 返回方法数.
// 前缀树 O(M) + O(N^2) 暴力方法O(N^3)
public class LeetCode_0139_WordBreak {
    // 暴力递归
    public static boolean wordBreak(String s, List<String> dict) {
        return p(s, 0, new HashSet<>(dict)) != 0;
    }

    public static int p(String s, int index, Set<String> dict) {
        if (index == s.length()) {
            return 1;
        }
        int result = 0;
        for (int i = index; i < s.length(); i++) {
            if (dict.contains(s.substring(index, i + 1))) {
                result += p(s, i + 1, dict);
            }
        }
        return result;
    }

    // 动态规划
    public static boolean wordBreak2(String s, List<String> dict) {
        HashSet<String> wordDict = new HashSet<>(dict);
        int length = s.length();
        int[] dp = new int[length + 1];
        dp[length] = 1;
        for (int index = length - 1; index >= 0; index--) {
            for (int i = index; i < s.length(); i++) {
                if (wordDict.contains(s.substring(index, i + 1))) {
                    dp[index] += dp[i + 1];
                }
            }
        }
        return dp[0] != 0;
    }

    public static class Node {
        public boolean end;
        public Node[] nexts;

        public Node() {
            end = false;
            nexts = new Node[26];
        }
    }

    // 前缀树优化
    public static boolean wordBreak4(String s, List<String> wordDict) {
        Node root = new Node();
        for (String str : wordDict) {
            char[] chs = str.toCharArray();
            Node node = root;
            int index;
            for (char ch : chs) {
                index = ch - 'a';
                if (node.nexts[index] == null) {
                    node.nexts[index] = new Node();
                }
                node = node.nexts[index];
            }
            node.end = true;
        }
        char[] str = s.toCharArray();
        int length = str.length;
        int[] dp = new int[length + 1];
        dp[length] = 1;
        for (int index = length - 1; index >= 0; index--) {
            Node cur = root;
            for (int i = index; i < length; i++) {
                cur = cur.nexts[str[i] - 'a'];
                if (cur == null) {
                    break;
                }
                if (cur.end) {
                    dp[index] += dp[i + 1];
                }
            }
        }
        return dp[0] != 0;
    }

}
