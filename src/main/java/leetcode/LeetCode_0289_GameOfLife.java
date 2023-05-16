/*
 * According to the Wikipedia's article:
 * "The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970."
 *
 * Given a board with m by n cells, each cell has an initial state live (1) or dead (0). Each cell
 * interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four
 * rules (taken from the above Wikipedia article):
 *
 * Any live cell with fewer than two live neighbors dies, as if caused by under-population.
 * 邻居==1或者0，下一代会死 Any live cell with two or three live neighbors lives on to the next generation.
 * 邻居==2 或者3，会保留到下一代 Any live cell with more than three live neighbors dies, as if by
 * over-population.. 邻居>3 会死 Any dead cell with exactly three live neighbors becomes a live cell, as
 * if by reproduction. 在一个非细胞的位置，如果有三个邻居的时候会复活 Write a function to compute the next state (after one
 * update) of the board given its current state. The next state is created by applying the above
 * rules simultaneously to every cell in the current state, where births and deaths occur
 * simultaneously.
 *
 * Example:
 *
 * Input: [ [0,1,0], [0,0,1], [1,1,1], [0,0,0] ] Output: [ [0,0,0], [1,0,1], [0,1,1], [0,1,0] ]
 * Follow up:
 *
 * Could you solve it in-place? Remember that the board needs to be updated at the same time: You
 * cannot update some cells first and then use their updated values to update other cells. In this
 * question, we represent the board using a 2D array. In principle, the board is infinite, which
 * would cause problems when the active area encroaches the border of the array. How would you
 * address these problems?
 */
package leetcode;

public class LeetCode_0289_GameOfLife {

    // TODO 可以优化的点：位信息压缩
    public static void gameOfLife(int[][] board) {
        if (null == board || board.length == 0 || board[0].length == 0) {
            return;
        }
        int M = board.length;
        int N = board[0].length;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                process(board, M, N, i, j);
            }
        }

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 3) {
                    board[i][j] = 1;
                }
                if (board[i][j] == 4) {
                    board[i][j] = 0;
                }
            }
        }

    }

    private static void process(int[][] board, int m, int n, int i, int j) {
        int nb = nb(board, m, n, i, j);
        boolean isLive = isLive(nb);
        int state = board[i][j];
        /*
         * if (isLive && state == 1) { // 不处理 } if (!isLive && state == 0) { // 不处理 }
         */
        if (nb == 3 && state == 0) {
            // 复活的情况
            board[i][j] = 3;
        }
        if (!isLive && state == 1) {
            // 死亡的情况
            board[i][j] = 4;
        }
        // die -> Live
        // Live -> die
        // die -> die 0->0
        // Live -> Live 1->1
    }

    private static boolean isLive(int nb) {
        return nb == 3 || nb == 2;
    }


    private static int nb(int[][] board, int M, int N, int i, int j) {
        int nb = 0;

        if (i - 1 >= 0) {
            nb += (board[i - 1][j] == 1 || board[i - 1][j] == 4) ? 1 : 0;
        }

        if (i + 1 < M) {
            nb += (board[i + 1][j] == 1 || board[i + 1][j] == 4) ? 1 : 0;
        }

        if (j - 1 >= 0) {
            nb += (board[i][j - 1] == 1 || board[i][j - 1] == 4) ? 1 : 0;
        }

        if (j + 1 < N) {
            nb += (board[i][j + 1] == 1 || board[i][j + 1] == 4) ? 1 : 0;
        }

        if (i - 1 >= 0 && j - 1 >= 0) {
            nb += (board[i - 1][j - 1] == 1 || board[i - 1][j - 1] == 4) ? 1 : 0;
        }

        if (i - 1 >= 0 && j + 1 < N) {
            nb += (board[i - 1][j + 1] == 1 || board[i - 1][j + 1] == 4) ? 1 : 0;
        }

        if (i + 1 < M && j - 1 >= 0) {
            nb += (board[i + 1][j - 1] == 1 || board[i + 1][j - 1] == 4) ? 1 : 0;
        }

        if (i + 1 < M && j + 1 < N) {
            nb += (board[i + 1][j + 1] == 1 || board[i + 1][j + 1] == 4) ? 1 : 0;
        }

        return nb;

    }

    public static void main(String[] args) {
        // [[0,0,0],[1,0,1],[0,1,1],[0,1,0]]
        int[][] board = {{0, 1, 0}, {0, 0, 1}, {1, 1, 1}, {0, 0, 0}};
        gameOfLife(board);

    }

}
