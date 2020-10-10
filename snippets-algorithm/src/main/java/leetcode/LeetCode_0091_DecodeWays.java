package leetcode;

// A message containing letters from A-Z is being encoded to numbers using the following mapping:

// 'A' -> 1
// 'B' -> 2
// ...
// 'Z' -> 26
// Given a non-empty string containing only digits, determine the total number of ways to decode it.

// Example 1:

// Input: "12"
// Output: 2
// Explanation: It could be decoded as "AB" (1 2) or "L" (12).
// Example 2:

// Input: "226"
// Output: 3
// Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
public class LeetCode_0091_DecodeWays {

	// 从左往右的尝试
	public static int numDecodings(String s) {
		if (null == s || s.length() == 0) {
			return 0;
		}
		char[] strs = s.toCharArray();

		return process(strs, 0);
	}

	public static int process(char[] nums, int k) {
		if (k == nums.length) {
			return 1;
		}
		if (nums[k] == '0') {
			return 0;
		}
		if (nums[k] > '2') {
			return process(nums, k + 1);
		} else if (nums[k] < '2') {
			if (k != nums.length - 1) {
				return process(nums, k + 1) + process(nums, k + 2);
			} else {
				return process(nums, k + 1);
			}
		} else {
			// nums[k] == 2
			if (k != nums.length - 1 && nums[k + 1] <= '6') {
				return process(nums, k + 1) + process(nums, k + 2);
			} else {
				return process(nums, k + 1);
			}
		}
	}

	public static int numDecodings2(String s) {
		if (null == s || s.length() == 0) {
			return 0;
		}
		char[] strs = s.toCharArray();
		int N = strs.length;
		int[] dp = new int[N + 1];

		dp[N] = 1;
		for (int i = N - 1; i >= 0; i--) {
			if (strs[i] == '0') {
				dp[i] = 0;
			} else if (strs[i] > '2') {
				dp[i] = dp[i + 1];
			} else if (strs[i] < '2') {
				if (i != N - 1) {
					dp[i] = dp[i + 1] + dp[i + 2];
				} else {
					dp[i] = dp[i + 1];
				}
			} else {
				// nums[k] == 2
				if (i != N - 1 && strs[i + 1] <= '6') {
					dp[i] = dp[i + 1] + dp[i + 2];
				} else {
					dp[i] = dp[i + 1];
				}
			}
		}

		return dp[0];
		

	}

	public static void main(String[] args) {
		System.out.println(numDecodings("12"));

	}

}
