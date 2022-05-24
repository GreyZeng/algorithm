package leetcode.hard;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

//给定一个字符类型的二维数组board，和一个字符串组成的列表words。可以从board任何位置出发，每一步可以走向上、下、左、右，四个方向，但是一条路径已经走过的位置，不能重复走。
//返回words哪些单词可以被走出来。
//例子
//board=[
//['o','a','a','n'],
//['e','t','a','e'],
//['i','h','k','r'],
//['i','f','l','v']
//]
//words=["oath","pea","eat","rain"]
//输出：["eat","oath"]
//LeetCode_0212_WordSearchII
//tips:
//0.把words做成前缀树，加速
//1.不能重复走，置为0，然后要恢复现场
//2.深度优先
// https://leetcode-cn.com/problems/word-search-ii/
public class LeetCode_0212_WordSearchII {
    public static class Trie {
        public Trie[] next;
        public int pass;
        public int end;

        public Trie() {
            next = new Trie[26];
            pass = 0;
            end = 0;
        }

    }

    // 利用前缀树加速
    public List<String> findWords(char[][] board, String[] words) {
        HashSet<String> set = new HashSet<>();
        Trie trie = new Trie();
        for (String word : words) {
            if (!set.contains(word)) {
                set.add(word);
                buildTrie(trie, word);
            }
        }
        int M = board.length;
        int N = board[0].length;
        List<String> ans = new ArrayList<>();
        LinkedList<Character> path = new LinkedList<>();
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                p(board, i, j, path, trie, ans, M, N);
            }
        }
        return ans;
    }

    private static int p(char[][] board, int i, int j, LinkedList<Character> path, Trie trie, List<String> ans, int M,
                         int N) {
        if (i == M || j == N || i < 0 || j < 0) {
            return 0;
        }
        char c = board[i][j];

        if (c == '0') {
            return 0;
        }
        int index = c - 'a';
        if (trie.next[index] == null || trie.next[index].pass == 0) {
            return 0;
        }
        path.addLast(c);
        trie = trie.next[index];
        int fix = 0;
        if (trie.end == 1) {
            ans.add(buildResult(path));
            trie.end--;
            fix++;
        }

        board[i][j] = '0';
        fix += p(board, i - 1, j, path, trie, ans, M, N);
        fix += p(board, i, j - 1, path, trie, ans, M, N);
        fix += p(board, i + 1, j, path, trie, ans, M, N);
        fix += p(board, i, j + 1, path, trie, ans, M, N);
        board[i][j] = c;
        path.pollLast();
        trie.pass -= fix;
        return fix;
    }

    private static String buildResult(LinkedList<Character> path) {
        LinkedList<Character> t = new LinkedList<>(path);
        StringBuilder sb = new StringBuilder();
        while (!t.isEmpty()) {
            sb.append(t.pollFirst());
        }
        return sb.toString();

    }

    // 将word转换成前缀树
    private void buildTrie(Trie trie, String word) {
        trie.pass++;
        char[] s = word.toCharArray();
        int index = 0;
        for (int i = 0; i < s.length; i++) {
            index = s[i] - 'a';
            if (trie.next[index] == null) {
                trie.next[index] = new Trie();
            }
            trie = trie.next[index];
            trie.pass++;
        }
        trie.end++;
    }
}
