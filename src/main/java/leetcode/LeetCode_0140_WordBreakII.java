/*Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences.

Note:

The same word in the dictionary may be reused multiple times in the segmentation.
You may assume the dictionary does not contain duplicate words.
Example 1:

Input:
s = "catsanddog"
wordDict = ["cat", "cats", "and", "sand", "dog"]
Output:
[
  "cats and dog",
  "cat sand dog"
]
Example 2:

Input:
s = "pineapplepenapple"
wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
Output:
[
  "pine apple pen apple",
  "pineapple pen apple",
  "pine applepen apple"
]
Explanation: Note that you are allowed to reuse a dictionary word.
Example 3:

Input:
s = "catsandog"
wordDict = ["cats", "dog", "sand", "and", "cat"]
Output:
[]*/
package leetcode;

import java.util.ArrayList;
import java.util.List;


public class LeetCode_0140_WordBreakII {

    public static class Node {
        public String path;
        public boolean end;
        public Node[] nexts;

        public Node() {
            path = null;
            end = false;
            nexts = new Node[26];
        }
    }

    public static List<String> wordBreak(String s, List<String> wordDict) {
        if (null == wordDict || wordDict.isEmpty()) {
            return new ArrayList<>();
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
                    //break;
                }
            }
        }
        if (!dp[0]) {
            return new ArrayList<>();
        }
        ArrayList<String> path = new ArrayList<>();
        List<String> ans = new ArrayList<>();
        process(str, 0, root, dp, path, ans);
        return ans;

    }

    public static void process(final char[] str, int index, Node root, final boolean[] dp, ArrayList<String> path, List<String> ans) {
        if (index == str.length) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < path.size() - 1; i++) {
                builder.append(path.get(i) + " ");
            }
            builder.append(path.get(path.size() - 1));
            ans.add(builder.toString());
        } else {
            Node cur = root;
            for (int end = index; end < str.length; end++) {
                int road = str[end] - 'a';
                if (cur.nexts[road] == null) {
                    break;
                }
                cur = cur.nexts[road];
                if (cur.end && dp[end + 1]) {
                    path.add(cur.path);
                    process(str, end + 1, root, dp, path, ans);
                    path.remove(path.size() - 1);
                }
            }
        }
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
            cur.path = str;
            cur.end = true;
        }
        return root;
    }


}
