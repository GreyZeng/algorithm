package leetcode.medium;

//给定一个N*N的矩阵matrix，只有0和1两种值，返回边框全是1的最大正方形的边长长度。
//        例如:
//        01111
//        01001
//        01001
//        01111
//        01011
//        其中边框全是1的最大正方形的大小为4*4，所以返回4
//        tips:
//        N*N 正方形
//        有N^4长方形 随便点一个点O(N^2), 随便点另外一个点O(N^2) 所以是O(N^4)
//        有N^3正方形 随便点一个点O(N^2), 边长的范围[0,N]，所以是O(N^3)
//        长方形需要两个点确定
//        正方形一个点+边长确定
// 准备两个矩阵：r矩阵和d矩阵，其中
//        r[i][j] 右侧有多少个连续的1
//        d[i][j] 下方有多少个连续的1
//https://leetcode.com/problems/largest-1-bordered-square/
public class LeetCode_1139_Largest1BorderedSquare {
	public static int largest1BorderedSquare(int[][] matrix) {
		int m = matrix.length;
		int n = matrix[0].length;
		int[][] r = initRight(matrix, m, n);
		int[][] d = initDown(matrix, m, n);
		int ans = 0;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (matrix[i][j] != 0) {
					int len = Math.min(r[i][j], d[i][j]);
					while (len > 0) {
						int x = i + len - 1;
						int y = j + len - 1;
						if (r[x][j] >= len && d[i][y] >= len) {
							ans = Math.max(len * len, ans);
							break;
						}
						len--;
					}
				}
			}
		}
		return ans;
	}

	private static int[][] initDown(int[][] matrix, int m, int n) {
		int[][] d = new int[m][n];
		for (int j = n - 1; j >= 0; j--) {
			d[m - 1][j] = matrix[m - 1][j];
		}
		for (int i = m - 2; i >= 0; i--) {
			for (int j = n - 1; j >= 0; j--) {
				if (matrix[i][j] == 0) {
					d[i][j] = 0;
				} else {
					d[i][j] = d[i + 1][j] + 1;
				}
			}
		}
		return d;
	}

	private static int[][] initRight(int[][] matrix, int m, int n) {
		int[][] r = new int[m][n];
		for (int i = m - 1; i >= 0; i--) {
			r[i][n - 1] = matrix[i][n - 1];
		}
		for (int j = n - 2; j >= 0; j--) {
			for (int i = m - 1; i >= 0; i--) {
				if (matrix[i][j] == 0) {
					r[i][j] = 0;
				} else {
					r[i][j] = r[i][j + 1] + 1;
				}
			}
		}
		return r;
	}
}
