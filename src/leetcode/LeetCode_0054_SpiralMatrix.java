package leetcode;

import java.util.ArrayList;
import java.util.List;

//
//Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
//
//        Example 1:
//
//        Input:
//        [
//        [ 1, 2, 3 ],
//        [ 4, 5, 6 ],
//        [ 7, 8, 9 ]
//        ]
//        Output: [1,2,3,6,9,8,7,4,5]
//        Example 2:
//
//        Input:
//        [
//        [1, 2, 3, 4],
//        [5, 6, 7, 8],
//        [9,10,11,12]
//        ]
//        Output: [1,2,3,4,8,12,11,10,9,5,6,7]
public class LeetCode_0054_SpiralMatrix {

    //有可能最内层是一条直线或者单点，或者还是一个长方形
    public static List<Integer> spiralOrder(int[][] matrix) {
        if (null == matrix) {
            return null;
        }
        int M = matrix.length;
        if (M == 0) {
            return new ArrayList<>();
        }
        int N = matrix[0].length;
        int ltx = 0;
        int lty = 0;
        int rbx = M - 1;
        int rby = N - 1;
        List<Integer> list = new ArrayList<>();
        while (lty <= rby && ltx <= rbx) {
            spiralOrder(matrix, M, N, ltx++, lty++, rbx--, rby--, list);
        }
        return list;
    }

    private static void spiralOrder(int[][] matrix, int m, int n, int ltx, int lty, int rbx, int rby, List<Integer> list) {
        if (ltx == rbx) {
            for (int i = lty; i <= rby; i++) {
                list.add(matrix[ltx][i]);
            }
        } else if (lty == rby) {
            for (int i = ltx; i <= rbx; i++) {
                list.add(matrix[i][lty]);
            }
        } else {
            for (int i = lty; i < rby; i++) {
                list.add(matrix[ltx][i]);
            }
            for (int i = ltx; i < rbx; i++) {
                list.add(matrix[i][rby]);
            }
            for (int i = rby; i > lty; i--) {
                list.add(matrix[rbx][i]);
            }
            for (int i = rbx; i > ltx; i--) {
                list.add(matrix[i][lty]);
            }
        }
    }

    public static void main(String[] args) {
        int[][] m = {{1}};
        spiralOrder(m);
    }


}
