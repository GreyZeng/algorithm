//描述
//有一个石子归并的游戏。最开始的时候，有n堆石子排成一列，目标是要将所有的石子合并成一堆。合并规则如下：
//
//每一次可以合并相邻位置的两堆石子
//每次合并的代价为所合并的两堆石子的重量之和
//求出最小的合并代价。
//
//样例
//样例 1:
//
//输入: [3, 4, 3]
//输出: 17
//样例 2:
//
//输入: [4, 1, 1, 4]
//输出: 18
//解释: 
//  1. 合并第二堆和第三堆 => [4, 2, 4], score = 2
//  2. 合并前两堆 => [6, 4]，score = 8
//  3. 合并剩余的两堆 => [10], score = 18
// https://www.lintcode.com/problem/476
package lintcode;

public class LintCode_0476_StoneGame {
	// 暴力递归 LintCode超时
	public static int stoneGame(int[] A) {
		if (null == A || A.length < 2) {
			return 0;
		}
		int n = A.length;
		int[] sum = new int[n + 1];
		sum[0] = 0;
		for (int i = 1; i < n + 1; i++) {
			sum[i] = sum[i - 1] + A[i - 1];
		}
		return p(sum, A, 0, A.length - 1);
	}

	public static int sum(int[] sum, int L, int R) {
		return sum[R + 1] - sum[L];
	}

	// i...j 合并最优解是
	public static int p(int[] sum, int[] A, int i, int j) {
		if (i == j) {
			return 0;
		}
		if (i + 1 == j ) {
			return A[i] + A[j];
		}
		// 最后一个位置单独一堆
		int min = p(sum, A, i, j - 1) + 0 + sum(sum, i, j);
		// 枚举最后一个位置怎么合并
		for (int m = 2; j - m >= i; m++) {
			min = Math.min(min, p(sum, A, i, j - m) + p(sum, A, j - m + 1, j) + sum(sum, i, j));
		}
		return min;
	}

	public static int stoneGame2(int[] A) {
		if (null == A || A.length < 2) {
			return 0;
		}
		int n = A.length;
		int[] sum = new int[n + 1];
		sum[0] = 0;
		for (int i = 1; i < n + 1; i++) {
			sum[i] = sum[i - 1] + A[i - 1];
		}
		int[][] dp = new int[n][n];
		// 根据暴力递归改，对角线都是0
		// 填倒数第二条对角线
		for (int i = 0; i < n - 1; i++) {
			dp[i][i + 1] = A[i] + A[i+1];
		}
		// 从倒数第三条对角线开始填
		for(int i = n - 2; i >= 0; i--) {
			// TODO
		}
		
		return dp[0][n-1];
	}
}
