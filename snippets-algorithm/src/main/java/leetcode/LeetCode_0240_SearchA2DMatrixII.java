package leetcode;

//Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
//
//        Integers in each row are sorted in ascending from left to right.
//        Integers in each column are sorted in ascending from top to bottom.
//        Example:
//
//        Consider the following matrix:
//
//        [
//        [1,   4,  7, 11, 15],
//        [2,   5,  8, 12, 19],
//        [3,   6,  9, 16, 22],
//        [10, 13, 14, 17, 24],
//        [18, 21, 23, 26, 30]
//        ]
//        Given target = 5, return true.
//
//        Given target = 20, return false.
public class LeetCode_0240_SearchA2DMatrixII {

    public static boolean searchMatrix(int[][] m, int target) {

        if (null == m || m.length == 0 || m[0].length == 0) {
            return false;
        }
        // note 从最右上角的数开始操作
        int M = m.length - 1;
        int N = m[0].length - 1;
        int i = 0;
        int j = N;
        while (i >= 0 &&j >= 0 && i <= M && j <= N) {
            if (m[i][j] == target) {
                return true;
            } else if (m[i][j] > target) {
                j--;
            } else if (m[i][j] < target) {
                i++;
            }
        }
        return false;
    }

}
