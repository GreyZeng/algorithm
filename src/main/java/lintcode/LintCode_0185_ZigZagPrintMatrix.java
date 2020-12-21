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
// FIXME
public class LintCode_0185_ZigZagPrintMatrix {
	public static int[] printZMatrix(int[][] matrix) {
		int M = matrix.length;
		int N = matrix[0].length;
		boolean dir = true; // false 和 true 分别控制从上往下还是从下往上
		int[] result = new int[M * N]; // 记录轨迹
		int fromI = 0;
		int fromJ = 0;
		int toI = 0;
		int toJ = 0;
		for (int i = 0; i < M + N; i++) {
			if (i == 0) {
				result[i] = matrix[i][i];
			} else if (i == M + N - 1) {
				result[i] = matrix[M][N];
			} else {
				fromJ = i < N ? fromJ + 1 : fromJ;
				fromJ = i >= N && i < M + N ? fromI + 1 : fromI;
				toI = i < M ? toI + 1 : toI;
				toJ = i >= M && i < M + N ? toJ + 1 : toJ;
				print(matrix, result, i, dir, fromI, fromJ, toI, toJ);
				dir = !dir;
			}
		}
		return result;
	}

	private static void print(int[][] matrix, int[] result, int index, boolean dir, int fromI, int fromJ, int toI,
			int toJ) {
		if (dir) {
			result[index++] = matrix[toI][toJ];
			while (fromI != toI) {
				result[++index] = matrix[fromI++][fromJ--];
			}
			
		} else {
			result[index++] = matrix[toI][toJ];
			while (fromJ != toJ) {
				result[++index] = matrix[toI--][toJ++];
			}
		} 
	}

	public static void main(String[] args) {
		int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 } };
		int[] result = printZMatrix(matrix);
		for (int i : result) {
			System.out.println(i + " ");
		}

	}

}
