package 练习题.leetcode.hard;


import java.util.*;

// 给定一个m x n 二维字符网格board和一个单词（字符串）列表 words，找出所有同时在二维网格和字典中出现的单词
// 单词必须按照字母顺序，通过 相邻的单元格 内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格
// 同一个单元格内的字母在一个单词中不允许被重复使用。
// tips:
// 0.把words做成前缀树，加速
// 1.不能重复走，置为0，然后要恢复现场
// 2.深度优先
// 笔记见：https://www.cnblogs.com/greyzeng/p/16321675.html
public class LeetCode_0212_WordSearchII {
    public static class Trie {
        public Trie[] next;
        public int pass;
        public boolean end;

        public Trie() {
            next = new Trie[26];
            pass = 0;
            end = false;
        }
    }


    public static List<String> findWords(char[][] board, String[] words) {
        Set<String> set = new HashSet<>();
        Trie trie = new Trie();
        for (String word : words) {
            if (!set.contains(word)) {
                set.add(word);
                buildTrie(trie, word);
            }
        }
        LinkedList<Character> pre = new LinkedList<>();
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                int times = process(i, j, pre, ans, board, trie);
                if (times == set.size()) {
                    return new ArrayList<>(set);
                }
            }
        }
        return ans;
    }


    public static int process(int i, int j, LinkedList<Character> pre, List<String> ans,
                              char[][] board, Trie trie) {
        if (i >= board.length || i < 0 || j >= board[0].length || j < 0) {
            return 0;
        }
        char c = board[i][j];
        if (c == '0') {
            // 不走回头路
            return 0;
        }
        int index = c - 'a';
        if (trie.next[index] == null || trie.next[index].pass == 0) {
            // 没有路可以走
            return 0;
        }
        pre.addLast(c);
        trie = trie.next[index];

        int fix = 0;
        if (trie.end) {
            ans.add(buildString(pre));
            trie.end = false;
            fix++;
        }
        board[i][j] = '0';
        fix += process(i + 1, j, pre, ans, board, trie);
        fix += process(i, j + 1, pre, ans, board, trie);
        fix += process(i - 1, j, pre, ans, board, trie);
        fix += process(i, j - 1, pre, ans, board, trie);
        board[i][j] = c;
        pre.pollLast();
        trie.pass -= fix;
        return fix;
    }

    private static String buildString(LinkedList<Character> pre) {
        LinkedList<Character> preCopy = new LinkedList<>(pre);
        StringBuilder sb = new StringBuilder();
        while (!preCopy.isEmpty()) {
            Character c = preCopy.pollFirst();
            sb.append(c);
        }
        return sb.toString();

    }

    private static void buildTrie(Trie trie, String word) {
        char[] str = word.toCharArray();
        for (char c : str) {
            if (trie.next[c - 'a'] == null) {
                trie.next[c - 'a'] = new Trie();
            }
            trie = trie.next[c - 'a'];
            trie.pass++;
        }
        trie.end = true;
    }
}
