//链接：https://www.nowcoder.com/questionTerminal/3ee42c9155c340588729995561ace594
//        来源：牛客网
//
//        有N件物品和一个容量为V的背包。第i件物品的价值是C[i]，重量是W[i]。求解将哪些物品装入背包可使价值总和最大。
//
//
//        输入描述:
//        输入第一行数 N V (1 <=N <=500) (1<= V <= 10000)
//
//        输入 N行 两个数字 代表 C W (1 <= C <= 50000, 1 <= W <=10000)
//
//
//        输出描述:
//        输出最大价值
//        示例1
//        输入
//        5 10
//        8 6
//        10 4
//        4 2
//        5 4
//        5 3
//        输出
//        19
//        示例2
//        输入
//        1 1
//        10 2
//        输出
//        0
package nowcoder;

import java.util.Scanner;

public class NowCoder_Knapsack {

	public static int getMaxValue(int[] A, int[] V, int m) {
		if (A == null || V == null || A.length == 0 || V.length == 0) {
			return 0;
		}
		int N = A.length;
		int[][] dp = new int[N + 1][m + 1];
		for (int i = N - 1; i >= 0; i--) {
			for (int j = 0; j <= m; j++) {
				dp[i][j] = dp[i + 1][j];
				if (j - A[i] >= 0) {
					dp[i][j] = Math.max(dp[i][j], dp[i + 1][j - A[i]] + V[i]);
				}
			}
		}
		return dp[0][m];
	}

	public static int getMaxValue2(int[] w, int[] v, int bag) {
		if (w == null || v == null || w.length == 0 || v.length == 0) {
			return 0;
		}
		int N = w.length;
		int[][] dp = new int[N + 1][bag + 1];
		for (int i = 0; i <= N; i++) {
			for (int j = 0; j <= bag; j++) {
				dp[i][j] = -1;
			}
		}
		p2(w, v, N, 0, bag, dp);
		return dp[0][bag];
	}

	// 0..i-1位置已经做好了选择，从i以后开始做决策
	// rest 背包中还剩下的容量是多少
	public static int p2(int[] w, int[] v, int len, int i, int rest, int[][] dp) {
		if (dp[i][rest] != -1) {
			return dp[i][rest];
		}
		// rest空间不能为负数

		if (i == len) {
			dp[i][rest] = 0;
			return dp[i][rest];
		}
		int p1 = p(w, v, len, i + 1, rest);

		int p2 = p(w, v, len, i + 1, rest - w[i]);

		if (p2 != -1) { // p2 不为-1才能算做正常解
			p2 += v[i];
		}
		dp[i][rest] = Math.max(p1, p2);
		return dp[i][rest];
	}

	public static int getMaxValue3(int[] w, int[] v, int bag) {
		if (w == null || v == null || w.length == 0 || v.length == 0) {
			return 0;
		}
		int N = w.length;
		return p(w, v, N, 0, bag);
	}

	// 0..i-1位置已经做好了选择，从i以后开始做决策
	// rest 背包中还剩下的容量是多少
	public static int p(int[] w, int[] v, int len, int i, int rest) {
		// rest空间不能为负数
		if (rest < 0) {
			return -1;
		}
		if (i == len) {
			return 0;
		}
		int p1 = p(w, v, len, i + 1, rest);

		int p2 = p(w, v, len, i + 1, rest - w[i]);

		if (p2 != -1) { // p2 不为-1才能算做正常解
			p2 += v[i];
		}
		return Math.max(p1, p2);
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int bag = in.nextInt();
		int[] w = new int[N];
		int[] v = new int[N];
		for (int i = 0; i < N; i++) {
			v[i] = in.nextInt();
			w[i] = in.nextInt();
		}
		System.out.println(getMaxValue(w, v, bag));
		in.close();
	}

}
