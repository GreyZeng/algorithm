package leetcode;

//Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.
//
//		A region is captured by flipping all 'O's into 'X's in that surrounded region.
//
//		Example:
//
//		X X X X
//		X O O X
//		X X O X
//		X O X X
//		After running your function, the board should be:
//
//		X X X X
//		X X X X
//		X X X X
//		X O X X
//		Explanation:
//
//		Surrounded regions shouldn’t be on the border, which means that any 'O' on the border of the board are not flipped to 'X'.
//		Any 'O' that is not on the border and it is not connected to an 'O' on the border will be flipped to 'X'.
//		Two cells are connected if they are adjacent cells connected horizontally or vertically.
// TODO
public class LeetCode_0130_SurroundedRegions {

    // 递归方法
    public static void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return;
        }
        int M = board.length;
        int N = board[0].length;
        for (int i = 0; i < M; i++) {
            if (board[i][0] == 'O') {
                free(board, i, 0);
            }
            if (board[i][N - 1] == 'O') {
                free(board, i, N - 1);
            }
        }
        for (int i = 0; i < N; i++) {
            if (board[0][i] == 'O') {
                free(board, 0, i);
            }
            if (board[M - 1][i] == 'O') {
                free(board, M - 1, i);
            }
        }
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] != '#') {
                    board[i][j] = 'X';
                } else {
                    board[i][j] = 'O';
                }
            }
        }
    }

    private static void free(char[][] board, int i, int j) {
        if (!inValid(board, i, j)) {
            return;
        }
        board[i][j] = '#';
        free(board, i + 1, j - 1);
        free(board, i + 1, j + 1);
        free(board, i - 1, j - 1);
        free(board, i - 1, j + 1);
    }

    public static boolean inValid(char[][] board, int i, int j) {
        if (i < 0 || j < 0 || i > board.length - 1 || j > board[0].length - 1 || board[i][j] != 'O') {
            return false;
        }
        return true;
    }

    // TODO 并查集方法
    public static void solve2(char[][] board) {

    }

}
