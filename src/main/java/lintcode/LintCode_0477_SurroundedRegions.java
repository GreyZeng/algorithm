package lintcode;

// https://www.lintcode.com/problem/477/
public class LintCode_0477_SurroundedRegions {
    public static void surroundedRegions(char[][] board) {
        if (null == board || board.length == 0 || board[0].length == 0) {
            return;
        }
        int row = board.length;
        int col = board[0].length;
        // 四周的O可以将联通的O解救出来
        for (int i = 0; i < row; i++) {
            if (board[i][0] == 'O') {
                free(board, i, 0);
            }
            if (board[i][col - 1] == 'O') {
                free(board, i, col - 1);
            }
        }
        // 四周的O可以将联通的O解救出来
        for (int i = 0; i < col; i++) {
            if (board[0][i] == 'O') {
                free(board, 0, i);
            }
            if (board[row - 1][i] == 'O') {
                free(board, row - 1, i);
            }
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] != '#') {
                    board[i][j] = 'X';
                } else {
                    board[i][j] = 'O';
                }
            }
        }
    }

    public static void free(char[][] board, int i, int j) {
        int row = board.length;
        int col = board[0].length;
        if (i >= row || i < 0 || j >= col || j < 0 || board[i][j] != 'O') {
            return;
        }
        board[i][j] = '#';
        free(board, i + 1, j);
        free(board, i - 1, j);
        free(board, i, j + 1);
        free(board, i, j - 1);
    }

}
