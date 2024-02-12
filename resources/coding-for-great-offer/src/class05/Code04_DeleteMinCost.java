package class05;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// 直接看minCostX方法是最优解，时间复杂度O(N*M)
// 这是来自陆振星同学提供的最优解
// 比课上讲的解还要好！
// 而且是时间复杂度O(N*M)的方法
// 强烈推荐同学们看懂，有解释也不难看懂
// 链接 : https://www.mashibing.com/question/detail/68090
public class Code04_DeleteMinCost {

	// 题目：
	// 给定两个字符串s1和s2，问s2最少删除多少字符可以成为s1的子串？
	// 比如 s1 = "abcde"，s2 = "axbc"
	// 返回 1

	// 解法一
	// 求出str2所有的子序列，然后按照长度排序，长度大的排在前面。
	// 然后考察哪个子序列字符串和s1的某个子串相等(KMP)，答案就出来了。
	// 分析：
	// 因为题目原本的样本数据中，有特别说明s2的长度很小。所以这么做也没有太大问题，也几乎不会超时。
	// 但是如果某一次考试给定的s2长度远大于s1，这么做就不合适了。
	public static int minCost1(String s1, String s2) {
		List<String> s2Subs = new ArrayList<>();
		process(s2.toCharArray(), 0, "", s2Subs);
		s2Subs.sort(new LenComp());
		for (String str : s2Subs) {
			if (s1.indexOf(str) != -1) { // indexOf底层和KMP算法代价几乎一样，也可以用KMP代替
				return s2.length() - str.length();
			}
		}
		return s2.length();
	}

	public static void process(char[] str2, int index, String path, List<String> list) {
		if (index == str2.length) {
			list.add(path);
			return;
		}
		process(str2, index + 1, path, list);
		process(str2, index + 1, path + str2[index], list);
	}

	// x字符串只通过删除的方式，变到y字符串
	// 返回至少要删几个字符
	// 如果变不成，返回Integer.Max
	public static int onlyDelete(char[] x, char[] y) {
		if (x.length < y.length) {
			return Integer.MAX_VALUE;
		}
		int N = x.length;
		int M = y.length;
		int[][] dp = new int[N + 1][M + 1];
		for (int i = 0; i <= N; i++) {
			for (int j = 0; j <= M; j++) {
				dp[i][j] = Integer.MAX_VALUE;
			}
		}
		dp[0][0] = 0;
		// dp[i][j]表示前缀长度
		for (int i = 1; i <= N; i++) {
			dp[i][0] = i;
		}
		for (int xlen = 1; xlen <= N; xlen++) {
			for (int ylen = 1; ylen <= Math.min(M, xlen); ylen++) {
				if (dp[xlen - 1][ylen] != Integer.MAX_VALUE) {
					dp[xlen][ylen] = dp[xlen - 1][ylen] + 1;
				}
				if (x[xlen - 1] == y[ylen - 1] && dp[xlen - 1][ylen - 1] != Integer.MAX_VALUE) {
					dp[xlen][ylen] = Math.min(dp[xlen][ylen], dp[xlen - 1][ylen - 1]);
				}
			}
		}
		return dp[N][M];
	}

	public static class LenComp implements Comparator<String> {

		@Override
		public int compare(String o1, String o2) {
			return o2.length() - o1.length();
		}

	}

	// 解法二
	// 生成所有s1的子串
	// 然后考察每个子串和s2的编辑距离(假设编辑距离只有删除动作且删除一个字符的代价为1)
	// 如果s1的长度较小，s2长度较大，这个方法比较合适
	public static int minCost2(String s1, String s2) {
		if (s1.length() == 0 || s2.length() == 0) {
			return s2.length();
		}
		int ans = Integer.MAX_VALUE;
		char[] str2 = s2.toCharArray();
		for (int start = 0; start < s1.length(); start++) {
			for (int end = start + 1; end <= s1.length(); end++) {
				// str1[start....end]
				// substring -> [ 0,1 )
				ans = Math.min(ans, distance(str2, s1.substring(start, end).toCharArray()));
			}
		}
		return ans == Integer.MAX_VALUE ? s2.length() : ans;
	}

	// 求str2到s1sub的编辑距离
	// 假设编辑距离只有删除动作且删除一个字符的代价为1
	public static int distance(char[] str2, char[] s1sub) {
		int row = str2.length;
		int col = s1sub.length;
		int[][] dp = new int[row][col];
		// dp[i][j]的含义：
		// str2[0..i]仅通过删除行为变成s1sub[0..j]的最小代价
		// 可能性一：
		// str2[0..i]变的过程中，不保留最后一个字符(str2[i])，
		// 那么就是通过str2[0..i-1]变成s1sub[0..j]之后，再最后删掉str2[i]即可 -> dp[i][j] = dp[i-1][j] + 1
		// 可能性二：
		// str2[0..i]变的过程中，想保留最后一个字符(str2[i])，然后变成s1sub[0..j]，
		// 这要求str2[i] == s1sub[j]才有这种可能, 然后str2[0..i-1]变成s1sub[0..j-1]即可
		// 也就是str2[i] == s1sub[j] 的条件下，dp[i][j] = dp[i-1][j-1]
		dp[0][0] = str2[0] == s1sub[0] ? 0 : Integer.MAX_VALUE;
		for (int j = 1; j < col; j++) {
			dp[0][j] = Integer.MAX_VALUE;
		}
		for (int i = 1; i < row; i++) {
			dp[i][0] = (dp[i - 1][0] != Integer.MAX_VALUE || str2[i] == s1sub[0]) ? i : Integer.MAX_VALUE;
		}
		for (int i = 1; i < row; i++) {
			for (int j = 1; j < col; j++) {
				dp[i][j] = Integer.MAX_VALUE;
				if (dp[i - 1][j] != Integer.MAX_VALUE) {
					dp[i][j] = dp[i - 1][j] + 1;
				}
				if (str2[i] == s1sub[j] && dp[i - 1][j - 1] != Integer.MAX_VALUE) {
					dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1]);
				}

			}
		}
		return dp[row - 1][col - 1];
	}

	// 解法二的优化
	public static int minCost3(String s1, String s2) {
		if (s1.length() == 0 || s2.length() == 0) {
			return s2.length();
		}
		char[] str2 = s2.toCharArray();
		char[] str1 = s1.toCharArray();
		int M = str2.length;
		int N = str1.length;
		int[][] dp = new int[M][N];
		int ans = M;
		for (int start = 0; start < N; start++) { // 开始的列数
			dp[0][start] = str2[0] == str1[start] ? 0 : M;
			for (int row = 1; row < M; row++) {
				dp[row][start] = (str2[row] == str1[start] || dp[row - 1][start] != M) ? row : M;
			}
			ans = Math.min(ans, dp[M - 1][start]);
			// 以上已经把start列，填好
			// 以下要把dp[...][start+1....N-1]的信息填好
			// start...end end - start +2
			for (int end = start + 1; end < N && end - start < M; end++) {
				// 0... first-1 行 不用管
				int first = end - start;
				dp[first][end] = (str2[first] == str1[end] && dp[first - 1][end - 1] == 0) ? 0 : M;
				for (int row = first + 1; row < M; row++) {
					dp[row][end] = M;
					if (dp[row - 1][end] != M) {
						dp[row][end] = dp[row - 1][end] + 1;
					}
					if (dp[row - 1][end - 1] != M && str2[row] == str1[end]) {
						dp[row][end] = Math.min(dp[row][end], dp[row - 1][end - 1]);
					}
				}
				ans = Math.min(ans, dp[M - 1][end]);
			}
		}
		return ans;
	}

	// 来自学生的解，最优解
	// 比课上讲的解还要好！
	// 这是时间复杂度O(N*M)的方法
	// 强烈推荐同学们看懂，有解释也不难看懂
	// 感谢陆振星同学提供的方法
	// 链接 : https://www.mashibing.com/question/detail/68090
	public static int minCostX(String s1, String s2) {
		char[] c1 = s1.toCharArray();
		char[] c2 = s2.toCharArray();
		// dp[i][j] 的含义 :
		// s2中前缀i长度的字符串，至少删除多少个字符可以变成 s1中前缀j长度字符串的 后缀串
		int[][] dp = new int[c2.length + 1][c1.length + 1];
		// s2前缀0长度，删掉0个字符，可以变成s1前缀任意长度字符串的 后缀串
		// 所以dp[0][....] = 0，所以省略了
		for (int i = 1; i <= c2.length; i++) {
			// s2前缀i长度，需要都删掉，可以变成s1前缀0长度字符串的 后缀串
			dp[i][0] = i;
			for (int j = 1; j <= c1.length; j++) {
				if (c2[i - 1] == c1[j - 1]) {
					// 如果 c2[i-1] == c1[j-1]
					// 可能性1 要么 c2[i-1] 和 c1[j-1] 进行匹配
					// 问题回到 dp[i-1][j-1]
					// 可能性2 要么 c2[i-1] 和 c1[j-1] 不进行匹配
					// s2[0...i] 依然删除 s2[i] 问题回到 dp[i-1][j] + 1
					// dp[i][j] = Math.min(dp[i-1][j-1], dp[i-1][j] + 1);
					// 实际上
					// 只需要考虑可能性1
					// 例
					// abc 去匹配 abc.....c
					// 删掉 .....c 和 删掉 c..... 是一个意思
					dp[i][j] = dp[i - 1][j - 1];
				} else {
					// 如果 c2[i-1] != c1[j-1]
					// c2[i-1] 和 c1[j-1] 不进行匹配
					// s2[0...i] 删除 s2[i] 问题回到 dp[i-1][j] + 1
					dp[i][j] = dp[i - 1][j] + 1;
				}
			}
		}
		// 返回最后一行的最小值即可
		int ans = dp[c2.length][0];
		for (int j = 1; j <= c1.length; j++) {
			ans = Math.min(ans, dp[c2.length][j]);
		}
		return ans;
	}

	// 为了测试
	public static String generateRandomString(int l, int v) {
		int len = (int) (Math.random() * l);
		char[] str = new char[len];
		for (int i = 0; i < len; i++) {
			str[i] = (char) ('a' + (int) (Math.random() * v));
		}
		return String.valueOf(str);
	}

	// 为了测试
	public static void main(String[] args) {
		int str1Len = 200;
		int str2Len = 100;
		int v = 5;
		int testTime = 10000;
		System.out.println("测试开始");
		for (int i = 0; i < testTime; i++) {
			String str1 = generateRandomString(str1Len, v);
			String str2 = generateRandomString(str2Len, v);
			int ans3 = minCost3(str1, str2);
			// 同学提供的解法，最优解
			int ansX = minCostX(str1, str2);
			if (ans3 != ansX) {
				System.out.println("出错了!");
				System.out.println(str1);
				System.out.println(str2);
				System.out.println(ans3);
				System.out.println(ansX);
				break;
			}
		}
		System.out.println("测试结束");
	}

}
