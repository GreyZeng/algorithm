package code;

// 最长递增【严格递增】子序列问题的O(N*logN)解法
// ends数组，ends[i] 找到的所有长度为i+1的递增子序列中最小结尾是什么
// dp[i]数组, 必须以i结尾的，最长递增子序列有多长
public class Code_0021_LIS {

	// O(N*logN)解法
	public static int[] lis(int[] arr) {
		// TODO
		return null;
	}

	// 暴力解(O(N^2))
	public static int[] lis2(int[] arr) {
		if (null == arr || arr.length == 0) {
			return null;
		}
		int N = arr.length;
		int[] dp = new int[N];

		for (int i = 0; i < N; i++) {
			dp[i] = 1;
			for (int j = 0; j < N; j++) {
				if (arr[j] < arr[i]) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
		}
		
		int maxIndex = 0;
		int maxLen = 0;
		for (int i = 0; i < N ;i ++) {
			if (dp[i] > maxLen) {
				maxIndex = i;
				maxLen = dp[i];
			}
		}

		int[] res = new int[maxLen];
		res[--maxLen] = arr[maxIndex];
		for (int i = maxIndex; i >=0; i--) {
			if (arr[i] < arr[maxIndex] && dp[i] == dp[maxIndex] - 1) {
				res[--maxLen] = arr[i];
				maxIndex = i;
			}
		} 
		
		return res;
	}
}
