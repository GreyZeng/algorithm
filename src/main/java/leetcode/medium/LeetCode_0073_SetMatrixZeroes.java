package leetcode.medium;

// https://leetcode.com/problems/set-matrix-zeroes/
public class LeetCode_0073_SetMatrixZeroes {
    // 第一行和第一列的数据来作为map索引

    // 进阶：如何只用一个变量来控制
    // 左上角的数只对第一行负责
    // 只用一个变量控制第一列
    public static void setZeroes(int[][] matrix) {
        boolean t = false;
        boolean l = false;
        int M = matrix.length;
        int N = matrix[0].length;
        for (int i = 0; i < N; i++) {
            if (matrix[0][i] == 0) {
                t = true;
                break;
            }
        }
        for (int[] ints : matrix) {
            if (ints[0] == 0) {
                l = true;
                break;
            }
        }
        for (int i = M - 1; i >= 1; i--) {
            for (int j = N - 1; j >= 1; j--) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        for (int i = 1; i < N; i++) {
            if (matrix[0][i] == 0) {
                for (int j = 1; j <= M - 1; j++) {
                    matrix[j][i] = 0;
                }
            }
        }
        for (int i = 1; i < M; i++) {
            if (matrix[i][0] == 0) {
                for (int j = 0; j < N; j++) {
                    matrix[i][j] = 0;
                }
            }
        }
        if (t) {
            for (int i = 0; i < N; i++) {
                matrix[0][i] = 0;
            }
        }
        if (l) {
            for (int i = 0; i < M; i++) {
                matrix[i][0] = 0;
            }
        }
    }

    public static void main(String[] args) {

        int[][] m = {{0, 1, 2, 0}, {3, 4, 5, 2}, {1, 3, 1, 5}};
        setZeroes(m);
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                System.out.print(m[i][j] + " ");
            }
            System.out.println();
        }

    }

}
