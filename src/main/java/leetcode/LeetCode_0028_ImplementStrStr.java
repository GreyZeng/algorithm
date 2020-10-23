package leetcode;

// KMP算法
public class LeetCode_0028_ImplementStrStr {

	public static int strStr(String haystack, String needle) {
		// 基本过滤条件
		if (null == haystack || needle == null) {
			return -1;
		}

		char[] strs = haystack.toCharArray();
		char[] match = needle.toCharArray();
		int N = strs.length;
		int M = match.length;
		if (N < M) {
			return -1;
		}
		if (M == 0) {
			return 0;
		}
		int[] next = getNextArr(match, M);
		int x = 0;
		int y = 0;
		while ((N - x) >= (M - y) && x < N && y < M) {
			if (strs[x] == match[y]) {
				// 匹配上了，x和y同时下一个
				x++;
				y++;
			} else if (y == 0) {
				x++;
			} else {
				y = next[y];
			}
		}
		return y == M ? x - y : -1;
	}

	private static int[] getNextArr(char[] match, int M) {
		if (M == 1) {
			return new int[] { -1 };
		}
		if (M == 2) {
			return new int[] { -1, 0 };
		}
		int[] next = new int[M];
		next[0] = -1;
		next[1] = 0;
		int i = 2;
		int c = 1;
		while (i < M) {
			if (match[i - 1] == match[next[c]]) {
				next[i++] = next[c] + 1;
				c = i - 1;
			} else {
				c = next[c];
				if (c == 0) {
					next[i++] = 0;
					c = i - 1;
				}
			}
		}
		return next;
	}

	public static void main(String[] args) {
		String a = "ababcaababcaabc";
		String b = "ababcaabc";
		System.out.println(strStr(a, b));
	}

}
