package leetcode;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

//Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words.
//
//		Note:
//
//		The same word in the dictionary may be reused multiple times in the segmentation.
//		You may assume the dictionary does not contain duplicate words.
//		Example 1:
//
//		Input: s = "leetcode", wordDict = ["leet", "code"]
//		Output: true
//		Explanation: Return true because "leetcode" can be segmented as "leet code".
//		Example 2:
//
//		Input: s = "applepenapple", wordDict = ["apple", "pen"]
//		Output: true
//		Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
//		Note that you are allowed to reuse a dictionary word.
//		Example 3:
//
//		Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
//		Output: false
public class LeetCode_0139_WordBreak {

    // 暴力尝试
    public static boolean wordBreak(String s, List<String> wordDict) {
        return process(s, 0, new HashSet<>(wordDict)) != 0;
    }

    public static int process(final String s, int index, final Set<String> wordDict) {
        char[] str = s.toCharArray();
        if (index == str.length) {
            return 1;
        }
        int t = 0;
        for (int i = index; i < str.length; i++) {
            if (wordDict.contains(s.substring(index, i + 1))) {
                t += process(s, i + 1, wordDict);
            }
        }
        return t;
    }

    // 暴力尝试转换为动态规划
    public static boolean wordBreak2(String s, List<String> wordDict) {
        if (null == wordDict || wordDict.isEmpty()) {
            return false;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        int[] dp = new int[N + 1];
        dp[N] = 1;
        int t = 0;
        for (int index = N - 1; index >= 0; index--) {
            t = 0;
            for (int i = index; i < str.length; i++) {
                if (wordDict.contains(s.substring(index, i + 1))) {
                    t += dp[i + 1];
                }
            }
            dp[index] = t;
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

    // 最优解前缀树
    public static boolean wordBreak3(String s, List<String> wordDict) {
        if (null == wordDict || wordDict.isEmpty()) {
            return false;
        }
        Node root = buildTrie(wordDict);
        char[] str = s.toCharArray();
        int N = str.length;
        boolean[] dp = new boolean[N + 1];
        dp[N] = true;
        for (int index = N - 1; index >= 0; index--) {
            Node cur = root;
            for (int i = index; i < N; i++) {
                int p = str[i] - 'a';
                if (cur.nexts[p] == null) {
                    break;
                }
                cur = cur.nexts[p];
                if (cur.end && dp[i + 1]) {
                    dp[index] = true;
                    break;
                }
            }
        }
        return dp[0];

    }

    // 构造前缀树
    private static Node buildTrie(List<String> wordDict) {
        Node root = new Node();

        for (String str : wordDict) {
            Node cur = root;
            char[] s = str.toCharArray();
            for (char c : s) {
                int p = c - 'a';
                if (cur.nexts[p] == null) {
                    cur.nexts[p] = new Node();
                }
                cur = cur.nexts[p];
            }
            cur.end = true;
        }
        return root;
    }

}
