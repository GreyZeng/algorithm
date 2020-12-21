/*You have a number of envelopes with widths and heights given as a pair of integers (w, h). One envelope can fit into another if and only if both the width and height of one envelope is greater than the width and height of the other envelope.

What is the maximum number of envelopes can you Russian doll? (put one inside other)

Note:
Rotation is not allowed.

Example:

Input: [[5,4],[6,4],[6,7],[2,3]]
Output: 3 
Explanation: The maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).*/
package leetcode;

import java.util.Arrays;
import java.util.Comparator;

// 最长递增子序列
public class LeetCode_0354_RussianDollEnvelopes {
	public static class Envelop {
		public int l;
		public int w;

		public Envelop(int len, int width) {
			l = len;
			w = width;
		}
	}

	public static class MyComparator implements Comparator<Envelop> {

		@Override
		public int compare(Envelop e1, Envelop e2) {
			return e1.l != e2.l ? e1.l - e2.l : e2.w - e1.w;
		}

	}

	public int maxEnvelopes(int[][] envelopes) {
		if (null == envelopes || 0 == envelopes.length || 0 == envelopes[0].length) {
			return 0;
		}
		int N = envelopes.length;
		Envelop[] es = new Envelop[N];
		for (int i = 0; i < N; i++) {
			es[i] = new Envelop(envelopes[i][0], envelopes[i][1]);
		}
		Arrays.sort(es, new MyComparator());
		int[] res = new int[N];
		int i = 0;
		for (Envelop e : es) {
			res[i++] = e.w;
		}
		return lis(res, N);
	}

	private int lis(int[] res, int N) {
		int[] dp = new int[N];
		int[] ends = new int[N];
		dp[0] = 1;
		ends[0] = res[0];
		int max = 1;
		int l = 0;
		int r = 0;
		int right = 0;
		int m = 0;
		for (int i = 0; i < N; i++) {
			l = 0;
			r = right;
			while (l <= r) {
				m = (l + r) / 2;
				if (res[i] > ends[m]) {
					l = m + 1;
				} else {
					r = m - 1;
				}
			}
			right = Math.max(right, l);
			ends[l] = res[i];
			dp[i] = l + 1;
			max = Math.max(max, dp[i]);
		}
		return max;
	}
}
