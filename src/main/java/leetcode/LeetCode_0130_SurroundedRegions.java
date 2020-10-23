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
public class LeetCode_0130_SurroundedRegions {

    // 感染过的地方设置为.
    // 不能一次遍历标记位置该改不该改
    // 或者通过边界处感染
    public static void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) {
            return;
        }
        // 从边界开始"释放"所有O（即把O设置为一个其他字符，比如：F）
        int M = board.length;
        int N = board[0].length;
        for (int i = 0; i <= M - 1; i++) {
            if (board[i][0] == 'O') {
                free(board, i, 0);
            }
            if (board[i][N - 1] == 'O') {
                free(board, i, N - 1);
            }
        }
        for (int i = 1; i <= N - 1; i++) {
            if (board[0][i] == 'O') {
                free(board, 0, i);
            }
            if (board[M - 1][i] == 'O') {
                free(board, M - 1, i);
            }
        }
        // 然后开始收集答案，只要是F的一律保持O，其他全部设置为X
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] = board[i][j] == 'F' ? 'O' : 'X';
            }
        }
    }

    public static void free(char[][] board, int i, int j) {
        if (i == board.length || j == board[0].length || i < 0 || j < 0 || board[i][j] != 'O') {
            return;
        }
        board[i][j] = 'F';
        free(board, i + 1, j);
        free(board, i - 1, j);
        free(board, i, j + 1);
        free(board, i, j - 1);
    }

}
