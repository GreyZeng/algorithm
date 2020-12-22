/*描述
        给定一个N×N的二维矩阵表示图像，90度顺时针旋转图像。

        样例
        样例1：

        输入:[[1,2],[3,4]]
        输出:[[3,1],[4,2]]
        样例 2:

        输入:[[1,2,3],[4,5,6],[7,8,9]]
        输出:[[7,4,1],[8,5,2],[9,6,3]]
        挑战
        能否在原地完成？*/
package lintcode;

// ref leetcode 48
// https://www.lintcode.com/problem/rotate-image/description
public class LintCode_0161_RotateMatrix {
    public static void rotate(int[][] matrix) {
        if (null == matrix || matrix.length == 0 || matrix[0].length == 0) {
            return;
        }
        int N = matrix.length;
        // 分四组
        int leftTopX = 0;
        int leftTopY = 0;
        int rightBottomX = N - 1;
        int rightBottomY = N - 1;

        while (leftTopX < rightBottomX) {
            rotate(matrix, leftTopX++, leftTopY++, rightBottomX--, rightBottomY--);
        }
    }

    private static void rotate(int[][] matrix, int leftTopX, int leftTopY, int rightBottomX, int rightBottomY) {
        int tmp;
        int group = rightBottomY - leftTopY;
        for (int i = 0; i < group; i++) {
            tmp = matrix[leftTopX][leftTopY + i];
            matrix[leftTopX][leftTopY + i] = matrix[rightBottomX - i][leftTopY];
            matrix[rightBottomX - i][leftTopY] = matrix[rightBottomX][rightBottomY - i];
            matrix[rightBottomX][rightBottomY - i] = matrix[leftTopX + i][rightBottomY];
            matrix[leftTopX + i][rightBottomY] = tmp;
        }
    }

    public static void main(String[] args) {
        int[][] m = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        rotate(m);
        System.out.println();
    }
}
