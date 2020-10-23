package leetcode;

public class LeetCode_0044_WildcardMatching {

	// 这个方法会超时
	public static boolean isMatch1(String str, String pattern) {
		char[] s = str.toCharArray();
		char[] p = pattern.toCharArray();
		return process1(s, p, 0, 0);
	}

	public static boolean process1(char[] s, char[] p, int si, int pi) {
		if (si == s.length) {
			if (pi == p.length) {
				return true;
			} else {
				return p[pi] == '*' && process1(s, p, si, pi + 1);
			}
		}
		if (pi == p.length) {
			return si == s.length;
		}
		// pi没到头
		// si也没到头
		if (p[pi] == '*') {
			int i = si;
			while (i <= s.length) {
				if (process1(s, p, i, pi + 1)) {
					return true;
				}
				i++;
			}
			return false;
		} else {
			return (p[pi] == s[si] || p[pi] == '?') && process1(s, p, si + 1, pi + 1);
		}
	}

	// 优化1
	public static boolean isMatch2(String str, String pattern) {
		char[] s = str.toCharArray();
		char[] p = pattern.toCharArray();
		int M = s.length;
		int N = p.length;
		boolean[][] dp = new boolean[M + 1][N + 1];

		dp[M][N] = true;
		for (int i = N - 1; i >= 0; i--) {
			dp[M][i] = p[i] == '*' && dp[M][i + 1];
		}
		for (int i = M - 1; i >= 0; i--) {
			for (int j = N - 1; j >= 0; j--) {
				if (p[j] == '*') {
					int x = i;
					while (x <= M) {
						if (dp[x][j + 1]) {
							dp[i][j] = true;
							break;
						}
						x++;
					}
				} else {
					dp[i][j] = (p[j] == s[i] || p[j] == '?') && dp[i + 1][j + 1];
				}
			}
		}
		return dp[0][0];
	}

	// 最终优化
	public static boolean isMatch3(String str, String pattern) {
		char[] s = str.toCharArray();
		char[] p = pattern.toCharArray();
		int M = s.length;
		int N = p.length;
		boolean[][] dp = new boolean[M + 1][N + 1];
		dp[M][N] = true;
		for (int i = N - 1; i >= 0; i--) {
			dp[M][i] = p[i] == '*' && dp[M][i + 1];
		}
		for (int i = M - 1; i >= 0; i--) {
			for (int j = N - 1; j >= 0; j--) {
				if (p[j] != '*') {
					dp[i][j] = (p[j] == s[i] || p[j] == '?') && dp[i + 1][j + 1];
				} else {
					dp[i][j] = dp[i + 1][j] || dp[i][j + 1];
				}
			}
		}
		return dp[0][0];
	}

	public static void main(String[] args) {
		String a = "adceb";
		String b = "*a*b";
		System.out.println(isMatch1(a, b));
	}

}
