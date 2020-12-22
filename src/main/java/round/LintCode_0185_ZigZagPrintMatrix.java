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
package round;

// 从开始位置，准备两个变量A和B，A往左边走，走到不能再走的时候，往下走
// B往下走，走到不能再往下的时候，往左边走，每次AB构成的连线进行打印（方向交替变化）
// https://www.lintcode.com/problem/matrix-zigzag-traversal/description
public class LintCode_0185_ZigZagPrintMatrix {
    public static int[] printZMatrix(int[][] matrix) {
        int M = matrix.length;
        int N = matrix[0].length;
        boolean dir = true; // false 和 true 分别控制从上往下还是从下往上
        int[] result = new int[M * N]; // 记录轨迹
        // A(fromI, fromJ) 往右边和下边走
        // B(toI, toJ) 往下边和右边走
        int fromI = 0;
        int fromJ = 0;
        int toI = 0;
        int toJ = 0;
        int pos = 0;
        while (fromI != M) {
            if (dir) {
                // 从下往上收集
                // B(toI, toJ) -> A(fromI, fromJ)
                int tmpI = toI;
                for (int tmpJ = toJ; tmpJ <= fromJ; tmpJ++) {
                    result[pos++] = matrix[tmpI--][tmpJ];
                }
            } else {
                // 从上往下收集
                // A(fromI, fromJ)  ->  B(toI, toJ)
                int tmpI = fromI;
                for (int tmpJ = fromJ; tmpJ >= toJ; tmpJ--) {
                    result[pos++] = matrix[tmpI++][tmpJ];
                }
            }
            dir = !dir;
            // 不能调换顺序
            fromI = fromJ == N - 1 ? fromI + 1 : fromI;
            fromJ = fromJ == N - 1 ? fromJ : fromJ + 1;
            toJ = toI == M - 1 ? toJ + 1 : toJ;
            toI = toI == M - 1 ? toI : toI + 1;
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        int[] result = printZMatrix(matrix);
        for (int i : result) {
            System.out.println(i + " ");
        }
    }
}
