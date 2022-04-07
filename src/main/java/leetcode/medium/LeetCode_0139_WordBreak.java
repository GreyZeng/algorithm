package leetcode.medium;

import java.util.List;

//假设所有字符都是小写字母
//        大字符串是str
//        arr是去重的单词表, 每个单词都不是空字符串且可以使用任意次.
//        使用arr中的单词有多少种拼接str的方式. 返回方法数.
// https://leetcode-cn.com/problems/word-break/
// lintcode有同样的题目
// https://www.lintcode.com/problem/107/
// 因为使用了更强大的数据，所以普通的DFS方法无法解决此题。
// s.length <= 1e5
// dict.size <= 1e5
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
