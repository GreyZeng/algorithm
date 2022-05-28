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
//提示：
//
//        m == board.length
//        n == board[i].length
//        1 <= m, n <= 12
//        board[i][j] 是一个小写英文字母
//        1 <= words.length <= 3 * 104
//        1 <= words[i].length <= 10
//        words[i] 由小写英文字母组成
//        words 中的所有字符串互不相同
//
//        来源：力扣（LeetCode）
//        链接：https://leetcode.cn/problems/word-search-ii
//        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
//tips:
//0.把words做成前缀树，加速
//1.不能重复走，置为0，然后要恢复现场
//2.深度优先
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
        List<String> ans = new ArrayList<>();
        LinkedList<Character> path = new LinkedList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                int size = p(board, i, j, path, trie, ans);
                if (size == set.size()){
                    return new ArrayList<>(set);
                }
            }
        }
        return ans;
    }

    // 从(i,j)出发，在board中能走出的所有答案都在ans里面，
    // path为前面已经走的路生成的字符串
    // 前缀树trie用于杀死不可能的情况
    private static int p(char[][] board, int i, int j, LinkedList<Character> path, Trie trie, List<String> ans) {
        if (i == board.length || j == board[0].length || i < 0 || j < 0) {
            return 0;
        }
        char c = board[i][j];
        if (c == '0') {
            // 不走回头路
            return 0;
        }
        int index = c - 'a';
        if (trie.next[index] == null || trie.next[index].pass == 0) {
            return 0;
        }
        path.addLast(c);
        trie = trie.next[index];
        int fix = 0;
        if (trie.end) {
            // 到这一步说明找到了匹配的单词
            ans.add(buildResult(path));
            trie.end = false;
            fix++;
        }
        board[i][j] = '0';
        fix += p(board, i - 1, j, path, trie, ans);

        fix += p(board, i, j - 1, path, trie, ans);

        fix += p(board, i + 1, j, path, trie, ans);

        fix += p(board, i, j + 1, path, trie, ans);

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
        int index;
        for (char c : s) {
            index = c - 'a';
            if (trie.next[index] == null) {
                trie.next[index] = new Trie();
            }
            trie = trie.next[index];
            trie.pass++;
        }
        trie.end = true;
    }
}
