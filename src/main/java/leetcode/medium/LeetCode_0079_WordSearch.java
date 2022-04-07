package leetcode.medium;

//
//Given a 2D board and a word, find if the word exists in the grid.
//
//		The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.
//
//		Example:
//
//		board =
//		[
//		['A','B','C','E'],
//		['S','F','C','S'],
//		['A','D','E','E']
//		]
//
//		Given word = "ABCCED", return true.
//		Given word = "SEE", return true.
//		Given word = "ABCB", return false.
//
//
//		Constraints:
//
//		board and word consists only of lowercase and uppercase English letters.
//		1 <= board.length <= 200
//		1 <= board[i].length <= 200
//		1 <= word.length <= 10^3
// https://leetcode-cn.com/problems/word-search/
public class LeetCode_0079_WordSearch {
    // 不能走重复路
    // 深度优先遍历
    // 增加现场
    public static boolean exist(char[][] board, String word) {
        char[] words = word.toCharArray();
        int M = board.length;
        int N = board[0].length;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (p(board, words, 0, i, j, M, N)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean p(char[][] board, char[] words, int s, int i, int j, int M, int N) {
        if (s == words.length) {
            return true;
        }
        if (i == M || i == -1 || j == N || j == -1) {
            return false;
        }
        if (board[i][j] == words[s]) {
            char t = board[i][j];
            board[i][j] = '0';
            boolean r = p(board, words, s + 1, i + 1, j, M, N) || p(board, words, s + 1, i, j + 1, M, N) || p(board, words, s + 1, i, j - 1, M, N) || p(board, words, s + 1, i - 1, j, M, N);
            board[i][j] = t;
            return r;
        } else {
            return false;
        }
    }


    // 可以走重复的设定
    public static boolean findWord1(char[][] m, String word) {
        if (word == null || word.equals("")) {
            return true;
        }
        if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
            return false;
        }
        char[] w = word.toCharArray();
        int N = m.length;
        int M = m[0].length;
        int len = w.length;
        // dp[i][j][k]表示：必须以m[i][j]这个字符结尾的情况下，能不能找到w[0...k]这个前缀串
        boolean[][][] dp = new boolean[N][M][len];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                dp[i][j][0] = m[i][j] == w[0];
            }
        }
        for (int k = 1; k < len; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    dp[i][j][k] = (m[i][j] == w[k] && checkPrevious(dp, i, j, k));
                }
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (dp[i][j][len - 1]) {
                    return true;
                }
            }
        }
        return false;
    }

    // 可以走重复路
    // 从m[i][j]这个字符出发，能不能找到str[k...]这个后缀串
    public static boolean canLoop(char[][] m, int i, int j, char[] str, int k) {
        if (k == str.length) {
            return true;
        }
        if (i == -1 || i == m.length || j == -1 || j == m[0].length || m[i][j] != str[k]) {
            return false;
        }
        // 不越界！m[i][j] == str[k] 对的上的！
        // str[k+1....]
        boolean ans = false;
        if (canLoop(m, i + 1, j, str, k + 1) || canLoop(m, i - 1, j, str, k + 1) || canLoop(m, i, j + 1, str, k + 1) || canLoop(m, i, j - 1, str, k + 1)) {
            ans = true;
        }
        return ans;
    }

    public static boolean checkPrevious(boolean[][][] dp, int i, int j, int k) {
        boolean up = i > 0 ? (dp[i - 1][j][k - 1]) : false;
        boolean down = i < dp.length - 1 ? (dp[i + 1][j][k - 1]) : false;
        boolean left = j > 0 ? (dp[i][j - 1][k - 1]) : false;
        boolean right = j < dp[0].length - 1 ? (dp[i][j + 1][k - 1]) : false;
        return up || down || left || right;
    }

}
