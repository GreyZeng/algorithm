/*Given an unsorted array of integers, find the length of longest increasing subsequence.

Example:

Input: [10,9,2,5,3,7,101,18]
Output: 4 
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4. 
Note:

There may be more than one LIS combination, it is only necessary for you to return the length.
Your algorithm should run in O(n2) complexity.
Follow up: Could you improve it to O(n log n) time complexity?*/
package leetcode;

// TODO
public class LeetCode_0300_LongestIncreasingSubsequence {

	// 暴力解(O(N^2))
	public static int lengthOfLIS2(int[] arr) {
		if (null == arr || arr.length == 0) {
			return 0;
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
		
		return res.length;
	}

}
