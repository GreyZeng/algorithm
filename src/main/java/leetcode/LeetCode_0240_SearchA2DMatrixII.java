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
// Constraints:
//
//m == matrix.length
//n == matrix[i].length
//1 <= n, m <= 300
//-10^9 <= matix[i][j] <= 10^9
//All the integers in each row are sorted in ascending order.
//All the integers in each column are sorted in ascending order.
//-10^9 <= target <= 10^9
public class LeetCode_0240_SearchA2DMatrixII {

    public static boolean searchMatrix(int[][] m, int target) {
        int M = m.length;
        int N = m[0].length;
        int i = 0;
        int j = N - 1;
        while (i <= M - 1 && j >= 0) {
            if (m[i][j] == target) {
                return true;
            } else if (m[i][j] > target) {
                j--;
            } else {
                i++;
            }
        }
        return false;
    }

}
