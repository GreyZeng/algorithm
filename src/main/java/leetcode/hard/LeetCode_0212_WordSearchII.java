package leetcode.hard;


import java.util.*;

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


    public static List<String> findWords(char[][] board, String[] words){
        Set<String> set = new HashSet<>();
        Trie trie = new Trie();
        for (String word : words) {
            if (!set.contains(word)){
                set.add(word);
                buildTrie(trie,word);
            }
        }
        LinkedList<Character> pre= new LinkedList<>();
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < board.length;i++) {
            for (int j = 0; j < board[0].length;j++) {
                int times = process(i,j,pre,ans,board,trie);
                if (times == set.size()) {
                    return new ArrayList<>(set);
                }
            }
        }
        return ans;
    }



    public static int process(int i, int j, LinkedList<Character> pre, List<String> ans, char[][] board, Trie trie){
        if (i >= board.length || i < 0 || j >= board[0].length || j < 0) {
            return 0;
        }
        char c = board[i][j];
        if (c == '0') {
            // 不走回头路
            return 0;
        }
        int index= c - 'a';
        if (trie.next[index] == null || trie.next[index].pass == 0) {
            // 没有路可以走
            return 0;
        }
        pre.addLast(c);
        trie = trie.next[index];

        int fix = 0;
        if(trie.end) {
            ans.add(buildString(pre));
            trie.end=false;
            fix++;
        }
        board[i][j] = '0';
        fix +=process(i+1,j,pre,ans,board,trie);
        fix+=process(i,j+1,pre,ans,board,trie);
        fix+=process(i-1,j,pre,ans,board,trie);
        fix+=process(i,j-1,pre,ans,board,trie);
        board[i][j] = c;
        pre.pollLast();
        trie.pass-=fix;
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
