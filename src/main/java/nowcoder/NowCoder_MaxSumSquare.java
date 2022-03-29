package nowcoder;

import java.util.Scanner;
// 子矩阵的最大累加和
// 测评链接：https://www.nowcoder.com/questionTerminal/cb82a97dcd0d48a7b1f4ee917e2c0409
// 压缩数组技巧 O(N^2 * M) --> 考虑一下N和M的长度
// 启发：数组的最大累加和: https://leetcode-cn.com/problems/maximum-subarray/
public class NowCoder_MaxSumSquare {
	public static int maxSum(int[][] matrix, int n, int m) {
		int max = maxSum(matrix[0]);
		for (int i = 0; i < n; i++) {
			max = Math.max(max, maxSum(matrix[i]));
			int[] t = new int[m];
			for (int j = 0; j < m; j++) {
				t[j] = matrix[i][j];
			}
			for (int j = i + 1; j < n; j++) {
				for (int k = 0; k < m; k++) {
					t[k] = t[k] + matrix[j][k];
				}
				max = Math.max(max, maxSum(t));
			}
		}
		return max;
	}

	public static int maxSum(int[] arr) {
		int pre = arr[0];
		int max = pre;
		int cur;
		for (int i = 1; i < arr.length; i++) {
			cur = arr[i] + Math.max(pre, 0);
			max = Math.max(cur, max);
			pre = cur;
		}
		return max;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int m = in.nextInt();
		int[][] matrix = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				matrix[i][j] = in.nextInt();
			}
		}
		System.out.println(maxSum(matrix, n, m));
		in.close();
	}

}
