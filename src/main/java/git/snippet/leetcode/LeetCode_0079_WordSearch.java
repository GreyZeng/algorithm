package git.snippet.leetcode;

/*
 * 给定一个char[][] matrix，也就是char类型的二维数组，再给定一个字符串word， 可以从任何一个某个位置出发，可以走上下左右，能不能找到word？ 比如： char[][] m
 * = { { 'a', 'b', 'z' }, { 'c', 'd', 'o' }, { 'f', 'e', 'o' }, }; word = "zooe" 是可以找到的
 *
 * 设定1：可以走重复路的情况下，返回能不能找到 比如，word = "zoooz"，是可以找到的，z -> o -> o -> o -> z，因为允许走一条路径中已经走过的字符
 *
 * 设定2：不可以走重复路的情况下，返回能不能找到 比如，word = "zoooz"，是不可以找到的，因为允许走一条路径中已经走过的字符不能重复走
 *
 * 写出两种设定下的code
 *
 */
// https://leetcode-cn.com/problems/word-search/
// 笔记：https://www.cnblogs.com/greyzeng/p/16321675.html
public class LeetCode_0079_WordSearch {
    // 不能走重复路
    public static boolean exist(char[][] board, String word) {
        char[] str = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (p(i, j, 0, str, board)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean p(int i, int j, int index, char[] str, char[][] board) {
        if (index == str.length) {
            return true;
        }
        if (i >= board.length || j >= board[0].length || i < 0 || j < 0) {
            return false;
        }
        if (board[i][j] == '0') {
            return false;
        }
        char c = board[i][j];
        board[i][j] = '0';
        if (str[index] == c) {
            boolean p1 = p(i + 1, j, index + 1, str, board);
            if (p1) {
                board[i][j] = c;
                return true;
            }
            boolean p2 = p(i, j + 1, index + 1, str, board);
            if (p2) {
                board[i][j] = c;
                return true;
            }
            boolean p3 = p(i - 1, j, index + 1, str, board);
            if (p3) {
                board[i][j] = c;
                return true;
            }
            boolean p4 = p(i, j - 1, index + 1, str, board);
            if (p4) {
                board[i][j] = c;
                return true;
            }
        }
        board[i][j] = c;
        return false;
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
        if (canLoop(m, i + 1, j, str, k + 1)
                || canLoop(m, i - 1, j, str, k + 1)
                || canLoop(m, i, j + 1, str, k + 1)
                || canLoop(m, i, j - 1, str, k + 1)) {
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
