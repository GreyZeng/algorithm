//描述
//        给你一个包含 m x n 个元素的矩阵 (m 行, n 列), 求该矩阵的之字型遍历。
//
//        样例
//        样例 1:
//        输入: [[1]]
//        输出:  [1]
//
//        样例 2:
//        输入:
//        [
//        [1, 2,  3,  4],
//        [5, 6,  7,  8],
//        [9,10, 11, 12]
//        ]
//
//        输出:  [1, 2, 5, 9, 6, 3, 4, 7, 10, 11, 8, 12]
package lintcode;

// 从开始位置，准备两个变量A和B，A往左边走，走到不能再走的时候，往下走
// B往下走，走到不能再往下的时候，往左边走，每次AB构成的连线进行打印（方向交替变化）
// https://www.lintcode.com/problem/matrix-zigzag-traversal/description
public class LintCode_0185_ZigZagPrintMatrix {
    public static int[] printZMatrix(int[][] matrix) {
        return null;
    }
}
