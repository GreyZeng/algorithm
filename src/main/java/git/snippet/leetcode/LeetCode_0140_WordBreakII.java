package git.snippet.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// word break 进阶，返回所有组合的情况
public class LeetCode_0140_WordBreakII {

    public static List<String> wordBreak(String s, List<String> words) {
        if (null == words || words.isEmpty()) {
            return new ArrayList<>();
        }
        Set<String> wordDict = new HashSet<>(words);
        Node root = buildTrie(wordDict);
        char[] str = s.toCharArray();
        int length = str.length;
        boolean[] dp = new boolean[length + 1];
        dp[length] = true;
        for (int index = length - 1; index >= 0; index--) {
            Node cur = root;
            for (int i = index; i < length; i++) {
                int p = str[i] - 'a';
                if (cur.nexts[p] == null) {
                    break;
                }
                cur = cur.nexts[p];
                if (cur.end && dp[i + 1]) {
                    dp[index] = true;
                    // break;
                }
            }
        }
        if (!dp[0]) {
            return new ArrayList<>();
        }
        List<String> path = new ArrayList<>();
        List<String> ans = new ArrayList<>();
        process(str, 0, root, dp, path, ans);
        return ans;
    }

    // 0...index-1已经处理完毕，收集的字符串在path中，最后的句子存在ans中
    public static void process(
            final char[] str,
            int index,
            Node root,
            final boolean[] dp,
            List<String> path,
            List<String> ans) {
        if (index == str.length) {
            // 收集一个句子
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < path.size() - 1; i++) {
                sb.append(path.get(i)).append(" ");
            }
            sb.append(path.get(path.size() - 1));
            ans.add(sb.toString());
            return;
        }
        Node cur = root;
        for (int i = index; i < str.length; i++) {
            // 如果没有往下的路，直接杀死这条路径
            if (cur.nexts[str[i] - 'a'] == null) {
                break;
            }
            cur = cur.nexts[str[i] - 'a'];
            if (cur.end && dp[i + 1]) {
                // 是否是一个有效的拼接，包括两个部分
                // 当前位置已经是结尾了 cur.end
                // 当前位置后面有拼接出完整字符串的能力(dp[i+1] == true)
                path.add(cur.path);
                process(str, i + 1, root, dp, path, ans);
                // 恢复现场
                path.remove(path.size() - 1);
            }
        }
    }

    // 构造前缀树
    private static Node buildTrie(Set<String> wordDict) {
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
}
