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
package lintcode.easy;

// 从开始位置，准备两个变量A和B，A往左边走，走到不能再走的时候，往下走
// B往下走，走到不能再往下的时候，往左边走，每次AB构成的连线进行打印（方向交替变化）
// https://www.lintcode.com/problem/matrix-zigzag-traversal/description
public class LintCode_0185_ZigZagPrintMatrix {
    public static int[] printZMatrix(int[][] matrix) {
        if (null == matrix || matrix.length == 0 || matrix[0].length == 0) {
            return null;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int[] ans = new int[m * n];
        ans[0] = matrix[0][0];
        // 右边-->下边
        int a = 0, b = 0;
        // 下边-->右边
        int c = 0, d = 0;
        int index = 1;
        boolean topToDown = true;
        for (int k = 0; k < m + n; k++) {
            if (b < n - 1) {
                b++;
            } else if (b == n - 1) {
                a++;
            }
            if (c < m - 1) {
                c++;
            } else if (c == m - 1) {
                d++;
            }
            if (topToDown) {
                int j = b;
                for (int i = a; i <= c; i++) {
                    ans[index++] = matrix[i][j--];
                }
            } else {
                int j = d;
                for (int i = c; i >= a; i--) {
                    ans[index++] = matrix[i][j++];
                }
            }
            topToDown = !topToDown;
        }
        return ans;
    }

    public static void main(String[] args) {
        // 期待输出:  [1, 2, 5, 9, 6, 3, 4, 7, 10, 11, 8, 12]
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        int[] result = printZMatrix(matrix);
        for (int i : result) {
            System.out.print(i + " ");
        }
    }
}
