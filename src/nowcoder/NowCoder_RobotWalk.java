//链接：https://www.nowcoder.com/questionTerminal/54679e44604f44d48d1bcadb1fe6eb61
//来源：牛客网
//
//假设有排成一行的N个位置，记为1~N，开始时机器人在M位置，
// 机器人可以往左或者往右走，如果机器人在1位置，那么下一步机器人只能走到2位置，
// 如果机器人在N位置，那么下一步机器人只能走到N-1位置。
// 规定机器人只能走k步，最终能来到P位置的方法有多少种。
// 由于方案数可能比较大，所以答案需要对1e9+7取模。
//
//输入描述:
//输出包括一行四个正整数N（2<=N<=5000）、M(1<=M<=N)、K(1<=K<=5000)、P(1<=P<=N)。
//
//
//输出描述:
//输出一个整数，代表最终走到P的方法数对10^9+7取模后的值。
//示例1
//输入
//5 2 3 3
//输出
//3
//说明
//1).2->1,1->2,2->3
//
//2).2->3,3->2,2->3
//
//3).2->3,3->4,4->3
//示例2
//输入
//1000 1 1000 1
//输出
//591137401
//说明
//注意答案要取模
//
//备注:
//时间复杂度O(n*k)O(n∗k),空间复杂度O(N)O(N)。
package nowcoder;
 
import java.util.Scanner;

public class NowCoder_RobotWalk {
	// 暴力递归
	public static int walk(int N, int M, int K, int P) {
		return p(N, M, K, P);
	}

	public static int p(int N, int M, int rest, int P) {
		if (rest == 0) {
			return M == P ? 1 : 0;
		}
		if (M == 1) {
			return p(N, M + 1, rest - 1, P);
		}
		if (M == N) {
			return p(N, M - 1, rest - 1, P);
		}
		return p(N, M - 1, rest - 1, P) + p(N, M + 1, rest - 1, P);
	}

	// 缓存
	public static int walk2(int N, int M, int K, int P) {
		int[][] dp = new int[N + 1][K + 1];
		for (int i = 0; i < N + 1; i++) {
			for (int j = 0; j < K + 1; j++) {
				dp[i][j] = -1;
			}
		}
		return p2(N, M, K, P, dp);
	}

	public static int p2(int N, int M, int K, int P, int[][] dp) {
		if (dp[M][K] != -1) {
			return dp[M][K];
		}
		if (K == 0) {
			dp[M][K] = (M == P ? 1 : 0);
			return dp[M][K];
		}
		if (M == 1) {
			dp[M][K] = p2(N, M + 1, K - 1, P, dp);
			return dp[M][K];
		}
		if (M == N) {
			dp[M][K] = p2(N, M - 1, K - 1, P, dp);
			return dp[M][K];
		}
		dp[M][K] = p2(N, M - 1, K - 1, P, dp) + p2(N, M + 1, K - 1, P, dp);
		return dp[M][K];
	}

	public static int MOD = (int) 1e9 + 7;

	public static int walk3(int N, int M, int K, int P) {
		int[][] dp = new int[N + 1][K + 1];
		dp[P][0] = 1;
		for (int j = 1; j <= K; j++) {
			for (int i = 1; i <= N; i++) {
				if (i == 1) {
					dp[i][j] = dp[2][j - 1] % MOD;
				} else if (i == N) {
					dp[i][j] = dp[N - 1][j - 1] % MOD;
				} else {
					dp[i][j] = (dp[i + 1][j - 1] + dp[i - 1][j - 1]) % MOD;
				}
			}
		}
		return dp[M][K] % MOD;
	}

	// 空间压缩 
	public static int walk4(int N, int M, int K, int P) {
		int[] dp = new int[N + 1];
		dp[P] = 1;
		int t2 = 0;
		for (int i = 1; i <= K; i++) {
			for (int j = 1; j <= N; j++) {
				int t = dp[j];
				if (j == 1) {
					dp[j] = dp[2]% MOD;
				} else if (j == N) {
					dp[j] = t2% MOD;
				} else { 
					dp[j] = t2% MOD + dp[j + 1]% MOD;
				}
				t2 = t;
			}
		}
		return dp[M]% MOD;

	}

	public static void main(String[] args) {
//        System.out.println(walk(7, 4, 9, 5));
//        System.out.println(walk2(7, 4, 9, 5));
//        System.out.println(walk3(7, 4, 9, 5));
		// System.out.println(walk4(7, 4, 9, 5));
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int M = in.nextInt();
		int K = in.nextInt();
		int P = in.nextInt();
		System.out.println(walk4(N, M, K, P));
		in.close();
	}

}
