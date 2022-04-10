package leetcode.hard;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

//Given a 2D board and a list of words from the dictionary, find all words in the board.
//
//Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.
//
// 
//
//Example:
//
//Input: 
//board = [
//  ['o','a','a','n'],
//  ['e','t','a','e'],
//  ['i','h','k','r'],
//  ['i','f','l','v']
//]
//words = ["oath","pea","eat","rain"]
//
//Output: ["eat","oath"]
// 
//
//Note:
//
//All inputs are consist of lowercase letters a-z.
//The values of words are distinct.
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
