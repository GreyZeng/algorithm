//描述
//        给定一个包含 m x n 个要素的矩阵，（m 行, n 列），按照螺旋顺序，返回该矩阵中的所有要素。
//
//        样例
//        样例 1:
//
//        输入:[[ 1, 2, 3 ], [ 4, 5, 6 ], [ 7, 8, 9 ]]
//        输出: [1,2,3,6,9,8,7,4,5]
//        样例 2
//
//        输入:[[ 6,4,1 ], [ 7,8,9 ]]
//        输出: [6,4,1,9,8,7]
package lintcode.medium;


import java.util.ArrayList;
import java.util.List;

// https://www.lintcode.com/problem/spiral-matrix/description
//tips：
//先打印外围圈圈,然后切换到内圈，用同样的方式打印内圈，依次循环
//注意最后有可能是一条直线
public class LintCode_0374_SpiralMatrix {
    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ans = new ArrayList<>();
        if (null == matrix || matrix.length == 0 || matrix[0].length == 0) {
            return ans;
        }
        int M = matrix.length;
        int N = matrix[0].length;
        // 左上坐标和右上坐标可以唯一确定一个矩形
        int leftTopX = 0;
        int leftTopY = 0;
        int rightBottomX = M - 1;
        int rightBottomY = N - 1;
        while (leftTopX <= rightBottomX && leftTopY <= rightBottomY) {
            spiral(matrix, leftTopX++, leftTopY++, rightBottomX--, rightBottomY--, ans);
        }
        return ans;
    }

    public static void spiral(int[][] matrix, int leftTopX, int leftTopY, int rightBottomX, int rightBottomY, List<Integer> ans) {
        if (leftTopX == rightBottomX) {
            // 一条横线
            for (int i = leftTopY; i <= rightBottomY; i++) {
                ans.add(matrix[leftTopX][i]);
            }
        } else if (leftTopY == rightBottomY) {
            // 一条竖线
            for (int i = leftTopX; i <= rightBottomX; i++) {
                ans.add(matrix[i][leftTopY]);
            }
        } else {
            // 上
            for (int i = leftTopY; i < rightBottomY; i++) {
                ans.add(matrix[leftTopX][i]);
            }
            // 右
            for (int i = leftTopX; i < rightBottomX; i++) {
                ans.add(matrix[i][rightBottomY]);
            }
            // 下
            for (int i = rightBottomY; i > leftTopY; i--) {
                ans.add(matrix[rightBottomX][i]);
            }
            // 左
            for (int i = rightBottomX; i > leftTopX; i--) {
                ans.add(matrix[i][leftTopY]);
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};

        System.out.println();
        List<Integer> res = spiralOrder(matrix);
        for (Integer i : res) {
            System.out.print(i + " ");
        }
    }


}
