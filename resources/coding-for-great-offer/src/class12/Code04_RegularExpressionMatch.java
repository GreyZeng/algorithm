package class12;

// 测试链接 : https://leetcode.com/problems/regular-expression-matching/
public class Code04_RegularExpressionMatch {

	// 暴力递归
	public static boolean isMatch1(String str, String pat) {
		char[] s = str.toCharArray();
		char[] p = pat.toCharArray();
		return process1(s, p, 0, 0);
	}

	// s[i....]能不能被p[j....]完全匹配出来
	public static boolean process1(char[] s, char[] p, int i, int j) {
		if (i == s.length) {
			// s没了
			if (j == p.length) {
				// 如果p也没了，返回true
				return true;
			} else {
				// 如果p[j+1]是*，那么p[j..j+1]可以消掉，然后看看p[j+2....]是不是都能消掉
				return j + 1 < p.length && p[j + 1] == '*' && process1(s, p, i, j + 2);
			}
		} else if (j == p.length) {
			// s有
			// p没了
			return false;
		} else {
			if (j + 1 == p.length || p[j + 1] != '*') {
				// s[i....]
				// p[j....]
				// 如果p[j+1]不是*，那么当前的字符必须能匹配：(s[i] == p[j] || p[j] == '?')
				// 同时，后续也必须匹配上：process1(s, p, i + 1, j + 1);
				return (s[i] == p[j] || p[j] == '.') && process1(s, p, i + 1, j + 1);
			} else {
				// s[i....]
				// p[j....]
				// 如果p[j+1]是*
				// 选择1: 当前p[j..j+1]是x*，就是不让它搞定s[i]，那么继续 : process1(s, p, i, j + 2)
				boolean p1 = process1(s, p, i, j + 2);
				// 选择2: 当前p[j..j+1]是x*，如果可以搞定s[i]，那么继续 : process1(s, p, i + 1, j)
				// 如果可以搞定s[i] : (s[i] == p[j] || p[j] == '.')
				// 继续匹配 : process1(s, p, i + 1, j)
				boolean p2 = (s[i] == p[j] || p[j] == '.') && process1(s, p, i + 1, j);
				// 两个选择，有一个可以搞定就返回true，都无法搞定返回false
				return p1 || p2;
			}
		}
	}

	// 记忆化搜索
	public static boolean isMatch2(String str, String pat) {
		char[] s = str.toCharArray();
		char[] p = pat.toCharArray();
		int n = s.length;
		int m = p.length;
		// dp[i][j] == 0，表示没算过
		// dp[i][j] == 1，表示没算过答案是true
		// dp[i][j] == 2，表示没算过答案是false
		int[][] dp = new int[n + 1][m + 1];
		return process2(s, p, 0, 0, dp);
	}

	public static boolean process2(char[] s, char[] p, int i, int j, int[][] dp) {
		if (dp[i][j] != 0) {
			return dp[i][j] == 1;
		}
		boolean ans;
		if (i == s.length) {
			if (j == p.length) {
				ans = true;
			} else {
				ans = j + 1 < p.length && p[j + 1] == '*' && process2(s, p, i, j + 2, dp);
			}
		} else if (j == p.length) {
			ans = false;
		} else {
			if (j + 1 == p.length || p[j + 1] != '*') {
				ans = (s[i] == p[j] || p[j] == '.') && process2(s, p, i + 1, j + 1, dp);
			} else {
				ans = process2(s, p, i, j + 2, dp) || ((s[i] == p[j] || p[j] == '.') && process2(s, p, i + 1, j, dp));
			}
		}
		dp[i][j] = ans ? 1 : 2;
		return ans;
	}

	// 动态规划
	public static boolean isMatch3(String str, String pat) {
		char[] s = str.toCharArray();
		char[] p = pat.toCharArray();
		int n = s.length;
		int m = p.length;
		boolean[][] dp = new boolean[n + 1][m + 1];
		dp[n][m] = true;
		for (int j = m - 2; j >= 0; j--) {
			dp[n][j] = p[j + 1] == '*' && dp[n][j + 2];
		}
		for (int i = n - 1; i >= 0; i--) {
			for (int j = m - 1; j >= 0; j--) {
				if (j + 1 == p.length || p[j + 1] != '*') {
					dp[i][j] = (s[i] == p[j] || p[j] == '.') && dp[i + 1][j + 1];
				} else {
					dp[i][j] = dp[i][j + 2] || ((s[i] == p[j] || p[j] == '.') && dp[i + 1][j]);
				}
			}
		}
		return dp[0][0];
	}

}
