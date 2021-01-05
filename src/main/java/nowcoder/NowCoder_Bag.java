/*链接：https://www.nowcoder.com/questionTerminal/d94bb2fa461d42bcb4c0f2b94f5d4281
        来源：牛客网

        牛牛准备参加学校组织的春游, 出发前牛牛准备往背包里装入一些零食, 牛牛的背包容量为w。
        牛牛家里一共有n袋零食, 第i袋零食体积为v[i]。
        牛牛想知道在总体积不超过背包容量的情况下,他一共有多少种零食放法(总体积为0也算一种放法)。

        输入描述:
        输入包括两行
        第一行为两个正整数n和w(1 <= n <= 30, 1 <= w <= 2 * 10^9),表示零食的数量和背包的容量。
        第二行n个正整数v[i](0 <= v[i] <= 10^9),表示每袋零食的体积。


        输出描述:
        输出一个正整数, 表示牛牛一共有多少种零食放法。
        示例1
        输入
        3 10
        1 2 4
        输出
        8
        说明
        三种零食总体积小于10,于是每种零食有放入和不放入两种情况，一共有2*2*2 = 8种情况。*/
package nowcoder;

import java.util.Arrays;
import java.util.Scanner;

// TODO 牛客上报了java.lang.OutOfMemoryError: Java heap space
public class NowCoder_Bag {
	public static int bag2(int[] arr, int w) {
		Arrays.sort(arr);
		int N = arr.length;
		int[][] dp = new int[N + 1][w + 1];
		for (int j = 0; j <= w; j++) {
			dp[N][j] = 1;
		}
		for (int i = N - 1; i >= 0; i--) {
			for (int j = 0; j <= w; j++) {
				dp[i][j] = dp[i + 1][j];
				if (j - arr[i] >= 0) {
					dp[i][j] += dp[i + 1][j - arr[i]];
				} else {
					break;
				}
			}
		}
		return dp[0][w];

	}

	// 暴力方法
	public static int bag(int[] v, long w) {
		Arrays.sort(v);
		if (v[0] > w) {
			return 1;
		}

		return p(v, 0, w);
	}

	// index及其后面所有继续搞定
	public static int p(int[] arr, int index, long rest) {
		if (rest < 0) { // 没有容量了
			// -1 无方案的意思
			return -1;
		}
		// rest>=0,
		if (index == arr.length) { // 无零食可选
			return 1;
		}
		// rest >=0
		// 有零食index
		// index号零食，要 or 不要
		// index, rest
		// (index+1, rest)
		// (index+1, rest-arr[i])
		int next1 = p(arr, index + 1, rest); // 不要
		int next2 = p(arr, index + 1, rest - arr[index]); // 要
		return next1 + (next2 == -1 ? 0 : next2);
	}

	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		long w = in.nextLong();
		long[] v = new long[n];
		long sum = 0;
		for (int i = 0; i < n; i++) {
			v[i] = in.nextLong();
			sum += v[i];
		}
		if (sum <= w) {
			System.out.println((long) Math.pow(2, n));
		} else {
			System.out.println(process(v, n - 1, w));
		}

		in.close();
	}

	private static int process(long[] v, int i, long w) {
		if (i == 0) {
			if (v[i] <= w) {
				return 2;
			} else {
				return 1;
			}
		}
		if (w == 0) {
			return 1;
		}
		if (v[i] <= w) {
			return process(v, i - 1, w - v[i]) + process(v, i - 1, w);
		} else {
			return process(v, i - 1, w);
		}
	}
}
