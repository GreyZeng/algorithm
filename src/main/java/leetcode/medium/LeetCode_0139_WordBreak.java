package leetcode.medium;

import java.util.List;

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

    public static class Node {
        public boolean end;
        public Node[] nexts;

        public Node() {
            end = false;
            nexts = new Node[26];
        }
    }

    public static boolean wordBreak1(String s, List<String> wordDict) {
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
        int N = str.length;
        boolean[] dp = new boolean[N + 1];
        dp[N] = true; // dp[i] word[i.....] 能不能被分解
        // dp[N] word[N...] -> "" 能不能够被分解
        // dp[i] ... dp[i+1....]
        for (int i = N - 1; i >= 0; i--) {
            // i
            // word[i....] 能不能够被分解
            // i..i i+1....
            // i..i+1 i+2...
            Node cur = root;
            for (int end = i; end < N; end++) {
                cur = cur.nexts[str[end] - 'a'];
                if (cur == null) {
                    break;
                }
                // 有路！
                if (cur.end) {
                    // i...end 真的是一个有效的前缀串 end+1.... 能不能被分解
                    dp[i] |= dp[end + 1];
                }
                if (dp[i]) {
                    break;
                }
            }
        }
        return dp[0];
    }

    // 返回所有的方法数
    public static int wordBreak2(String s, List<String> wordDict) {
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
        int N = str.length;
        int[] dp = new int[N + 1];
        dp[N] = 1;
        for (int i = N - 1; i >= 0; i--) {
            Node cur = root;
            for (int end = i; end < N; end++) {
                cur = cur.nexts[str[end] - 'a'];
                if (cur == null) {
                    break;
                }
                if (cur.end) {
                    dp[i] += dp[end + 1];
                }
            }
        }
        return dp[0];
    }

}
