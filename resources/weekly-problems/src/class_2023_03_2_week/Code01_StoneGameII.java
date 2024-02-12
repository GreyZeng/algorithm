package class_2023_03_2_week;

// 爱丽丝和鲍勃继续他们的石子游戏
// 许多堆石子 排成一行，每堆都有正整数颗石子 piles[i]
// 游戏以谁手中的石子最多来决出胜负。
// 爱丽丝和鲍勃轮流进行，爱丽丝先开始。最初，M = 1。
// 在每个玩家的回合中，该玩家可以拿走剩下的 前 X 堆的所有石子，其中 1 <= X <= 2M
// 然后，令 M = max(M, X)。
// 游戏一直持续到所有石子都被拿走。
// 假设爱丽丝和鲍勃都发挥出最佳水平
// 返回爱丽丝可以得到的最大数量的石头。
// 测试链接 : https://leetcode.cn/problems/stone-game-ii/
public class Code01_StoneGameII {

	// 暴力方法
	// 提交会超时，但是怎么尝试已经详细阐述明白
	// 提交时把stoneGameII1改名为stoneGameII
	public static int stoneGameII1(int[] piles) {
		return first1(piles, 0, 1);
	}

	// 当前先手的情况下
	// 可以在piles[i....]范围上拿数字
	// 返回最大的收益
	public static int first1(int[] piles, int index, int m) {
		// 没有数字了，收益肯定是0
		if (index == piles.length) {
			return 0;
		}
		int best = 0;
		int pre = 0;
		// 先手根据m，做所有的尝试，选最好的
		// 1 2 3 .. 2m
		// index....i  几个，j个
		// 1) index...index  1个    pre 
		// 2) index...index+1 2个   pre
		// 3) index...index+2 3个   pre 
		// m = 100   1 2 3 ~ 200 1~2m
		for (int i = index, j = 1; i < piles.length && j <= 2 * m; i++, j++) {
			pre += piles[i];
			// j个
			// index...i  (i+1....
			best = Math.max(best, pre + second1(piles, i + 1, Math.max(j, m)));
		}
		return best;
	}

	// 当前后手的情况下
	// 可以在piles[i....]范围上拿数字
	// 返回最大的收益
	public static int second1(int[] piles, int index, int m) {
		if (index == piles.length) {
			return 0;
		}
		int worse = Integer.MAX_VALUE;
		// 先手根据m，做所有的尝试，并且把最差的结果留给后手
		for (int i = index, j = 1; i < piles.length && j <= 2 * m; i++, j++) {
			worse = Math.min(worse, first1(piles, i + 1, Math.max(j, m)));
		}
		return worse;
	}

	// 暴力方法改成记忆化搜索
	// 可以直接通过
	// 提交时把stoneGameII2改名为stoneGameII
	public static int stoneGameII2(int[] piles) {
		int[][] f = new int[piles.length][piles.length + 1];
		int[][] s = new int[piles.length][piles.length + 1];
		for (int i = 0; i < piles.length; i++) {
			for (int j = 0; j <= piles.length; j++) {
				f[i][j] = -1;
				s[i][j] = -1;
			}
		}
		return first2(piles, 0, 1, f, s);
	}

	// 当前先手的情况下
	// 可以在piles[i....]范围上拿数字
	// 返回最大的收益
	public static int first2(int[] piles, int index, int m, int[][] f, int[][] s) {
		// 没有数字了，收益肯定是0
		if (index == piles.length) {
			return 0;
		}
		if (f[index][m] != -1) {
			return f[index][m];
		}
		int best = 0;
		int pre = 0;
		for (int i = index, j = 1; i < piles.length && j <= 2 * m; i++, j++) {
			pre += piles[i];
			best = Math.max(best, pre + second2(piles, i + 1, Math.min(piles.length, Math.max(j, m)), f, s));
		}
		f[index][m] = best;
		return best;
	}

	// 当前后手的情况下
	// 可以在piles[i....]范围上拿数字
	// 返回最大的收益
	public static int second2(int[] piles, int index, int m, int[][] f, int[][] s) {
		if (index == piles.length) {
			return 0;
		}
		if (s[index][m] != -1) {
			return s[index][m];
		}
		int worse = Integer.MAX_VALUE;
		for (int i = index, j = 1; i < piles.length && j <= 2 * m; i++, j++) {
			worse = Math.min(worse, first2(piles, i + 1, Math.min(piles.length, Math.max(j, m)), f, s));
		}
		s[index][m] = worse;
		return worse;
	}

	// 记忆化搜索改严格位置依赖的动态规划
	// 提交时把stoneGameII3改名为stoneGameII
	public static int stoneGameII3(int[] piles) {
		int n = piles.length;
		int[][] f = new int[n + 1][n + 1];
		int[][] s = new int[n + 1][n + 1];
		for (int index = n - 1; index >= 0; index--) {
			for (int m = 1; m <= n; m++) {
				int pre = 0;
				for (int i = index, j = 1; i < piles.length && j <= 2 * m; i++, j++) {
					pre += piles[i];
					f[index][m] = Math.max(f[index][m], pre + s[i + 1][Math.min(n, Math.max(j, m))]);
				}
				s[index][m] = Integer.MAX_VALUE;
				for (int i = index, j = 1; i < piles.length && j <= 2 * m; i++, j++) {
					s[index][m] = Math.min(s[index][m], f[i + 1][Math.min(n, Math.max(j, m))]);
				}
			}
		}
		return f[0][1];
	}

	// 严格位置依赖的动态规划，一张表的版本
	// 提交时把stoneGameII4改名为stoneGameII
	public int stoneGameII4(int[] piles) {
		int n = piles.length, sum = 0;
		// dp[i][m] : 表示piles[i....]范围上，在拿取范围是1~2*m的情况下，先手能获得的最大收益
		// 举个例子 :
		// 比如当前先手，可以在piles[7...]范围上，在拿取范围是1 ~ 2 * 4(m)的情况下
		// 先手想获得最大收益
		// 当先手拿走7...9的块(这是长度为3的块)，
		// 后续轮到对手在piles[10...]范围上，在拿取范围是1~2*m的情况下，对手也会获得的最大收益
		// 假设piles[7...]整体的累加和是sum
		// 整体的累加和 = 先手收益 + 后手收益，因为先手和后手一定刮分整个累加和
		// 所以，先手获得的最大值 = sum - 对手在先手时后续所有可能性中的最小值
		int[][] dp = new int[n][n + 1];
		for (int i = n - 1; i >= 0; i--) {
			// i == n - 1   sum = arr[n-1]
			// i == n - 2   sum += arr[n-2]
			// i == n - 3   sum += arr[n-3]
			sum += piles[i];
			for (int m = 1; m <= n; m++) {
				// dp[4][1] ...
				// dp[4][2] ...
				// dp[i][m] 
				if (i + 2 * m >= n) {
					// 如果先手当前的拿取范围，可以全包下数组所有的数
					// 那必然是全拿，不给对手任何机会
					dp[i][m] = sum;
				} else {
					// 如果先手当前的拿取范围，不能全包下数组所有的数
					// 那必然去是看看，对手在先手时后续所有可能性中的最小值
					// nextMin就是这个含义 : 对手在先手时后续所有可能性中的最小值
					int nextMin = Integer.MAX_VALUE;
					for (int x = 1; x <= 2 * m; x++) {
						nextMin = Math.min(nextMin, dp[i + x][Math.max(m, x)]);
					}
					// 先手获得的最大值 = sum - 对手在先手时后续所有可能性中的最小值
					dp[i][m] = sum - nextMin;
				}
			}
		}
		return dp[0][1];
	}

}
