package lintcode.medium;

import java.util.Set;

//描述
//给定字符串 s 和单词字典 dict，确定 s 是否可以分成一个或多个以空格分隔的子串，并且这些子串都在字典中存在。
//因为我们已经使用了更强大的数据，所以普通的DFS方法无法解决此题。
// https://www.lintcode.com/problem/107/
// 因为使用了更强大的数据，所以普通的DFS方法无法解决此题。
// s.length <= 1e5
// dict.size <= 1e5
public class LintCode_0107_WordBreak {
    public static boolean wordBreak(String s, Set<String> wordDict) {
        return process(s, 0, wordDict) != 0;
    }

    public static int process(final String s, int index, final Set<String> wordDict) {
        if (index == s.length()) {
            return 1;
        }
        int result = 0;
        for (int i = index; i < s.length(); i++) {
            // substring [from, to+1)
            if (wordDict.contains(s.substring(index, i + 1))) {
                result += process(s, i + 1, wordDict);
            }
        }
        return result;
    }

    // O(N^3) leetcode 可以过，lintcode过不了。
    public static boolean wordBreak2(String s, Set<String> wordDict) {
        if (null == wordDict) {
            return false;
        }
        if (wordDict.isEmpty()) {
            return s.length() == 0;
        }
        int N = s.length();
        int[] dp = new int[N + 1];
        dp[N] = 1;
        for (int m = N - 1; m >= 0; m--) {
            int result = 0;
            for (int i = m; i < s.length(); i++) {
                // substring [from, to+1)
                if (wordDict.contains(s.substring(m, i + 1))) {
                    result += dp[i + 1];
                }
            }
            dp[m] = result;
        }
        return dp[0] != 0;
    }

    public static boolean wordBreak3(String s, Set<String> wordDict) {
        if (null == wordDict) {
            return false;
        }
        if (wordDict.isEmpty()) {
            return s.length() == 0;
        }
        Node root = new Node();
        for (String word : wordDict) {
            Node cur = root;
            for (char c : word.toCharArray()) {
                if (cur.next[c - 'a'] == null) {
                    cur.next[c - 'a'] = new Node();
                }
                cur = cur.next[c - 'a'];
            }
            cur.end = true;
        }

        int N = s.length();
        boolean[] dp = new boolean[N + 1];
        dp[N] = true;
        char[] str = s.toCharArray();
        for (int i = N - 1; i >= 0; i--) {
            Node cur = root;
            for (int end = i; end < N; end++) {
                cur = cur.next[str[end] - 'a'];
                if (cur == null) {
                    break;
                }
                if (cur.end && dp[end + 1]) {
                    dp[i] = dp[end + 1];
                }
            }
        }
        return dp[0];
    }

    public static class Node {
        public boolean end;
        public Node[] next;

        public Node() {
            end = false;
            next = new Node[26];
        }
    }
}
